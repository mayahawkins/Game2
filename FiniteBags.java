// javac File.java && java Class
// where File.java is the name of the file
// where Class is the name of the class with "main" in it

// javac -cp javalib.jar;tester.jar File.java && java -cp javalib.jar;tester.jar Class

import java.util.Random;

interface Sequenced {
    public Sequence seq();
}

interface Sequence<I> extends Sequenced {
    public I here();
    public boolean notEmpty();
    public Sequence next();
}


public interface FiniteBags<I extends Comparable>/* extends Sequence<I>*/{
	public int cardinality();
	public boolean member (I item);
	public int counter(I item);	
	public FiniteBags empty();
	public boolean isEmptyHuh();
	public FiniteBags add(I item);
	public FiniteBags add(I item, int num);
	public FiniteBags remove(I item);
	public FiniteBags remove(I item, int num);
	public FiniteBags removeAll(I item);
	public FiniteBags union(FiniteBags baggy);
	public FiniteBags inter(FiniteBags baggy);
	public FiniteBags differ(FiniteBags baggy);
	public boolean equal(FiniteBags baggy);
	public boolean subset(FiniteBags baggy);
	public FiniteBags blacken();
}


class Things_MT<I extends Comparable> implements FiniteBags<I>{
	boolean isBlack;

	public Things_MT() {
		this.isBlack = true;
	}

	public boolean isBlackHuh() {
		return isBlack;
	}

	public int counter(I item) {
		return 0;
	}

	public int cardinality() {
		return 0;
	}
	public boolean member (I item) {
		return false;
	}

	public FiniteBags empty(){
		return new Things_MT();
	}

	public boolean isEmptyHuh() {
		return true;
	}

	public FiniteBags add(I item) {
		return add(item, 1).blacken();
	}

	public FiniteBags add(I item, int num){
		return new Things_ST(new Things_MT(), item, new Things_MT(), num, false);
	}

	public FiniteBags remove(I item){
		return this;
	}

	public FiniteBags remove(I item, int num){
		return this;
	}

	public FiniteBags removeAll(I item){
		return this;
	}

	public FiniteBags union(FiniteBags baggy){
		return baggy;
	}	

	public FiniteBags inter(FiniteBags baggy){
		return this;
	}

	public FiniteBags differ(FiniteBags baggy){
		return baggy;
	}

	public boolean equal(FiniteBags baggy){
		if(baggy.isEmptyHuh()){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean subset(FiniteBags baggy){
		return true;
	}

	public FiniteBags blacken(){
		return new Things_MT();
	}

//	Sequence seq() {return this;}
//	public boolean notEmpty() {return false;}
//	public I here() {return -1;}
//	public Sequence next() {return this;}
}


class Things_ST<I extends Comparable> implements FiniteBags<I>/*, Sequence*/{

	FiniteBags lefty;
	I here;
	FiniteBags righty;
	int amount;
	boolean isBlack;

	public Things_ST(FiniteBags lefty, I here, FiniteBags righty, int amount, boolean isBlack) {
		this.lefty = lefty;
		this.here = here;
		this.righty = righty;
		this.amount = amount;
		this.isBlack = isBlack;
	}

	public int cardinality(){
		return this.righty.cardinality() + this.lefty.cardinality() + this.amount;
	}

	public boolean member(I item){
		if(item.compareTo(this.here) == 0){
		return true;
		}
		else if(item.compareTo(this.here) > 0) {
			return this.righty.member(item);
		}
		else {
			return this.lefty.member(item);
		}
	}

	public int counter(I item){
		if(this.member(item) == false){
			return 0;
		}
		else if(item.compareTo(this.here) == 0){
			return this.amount;
		}
		else if(item.compareTo(this.here) < 0) {
			return this.righty.counter(item);
		}
		else {
			return this.lefty.counter(item);
		}
	}

	public FiniteBags empty(){
		return new Things_MT();
	}

	public boolean isEmptyHuh(){
		return false;
	}

	public FiniteBags add(I item){
		return this.add(item, 1);
	}

	public FiniteBags add(I item, int num){
		if (item.compareTo(this.here) == 0) {
			return new Things_ST(this.lefty, this.here, this.righty, (this.amount + num), this.isBlack);
		}
		else if (item.compareTo(this.here) < 0) {
		return new Things_ST(this.lefty.add(item, num), this.here, this.righty, this.amount, this.isBlack);
		}
		else {
			return new Things_ST(this.lefty, this.here, this.righty.add(item, num), this.amount, this.isBlack);
		}
	}

	public FiniteBags remove(I item, int num){
		if(item.compareTo(this.here) == 0){
			if(num >= this.amount){
				return this.lefty.union(this.righty);
			}
			else {
				return new Things_ST(this.lefty, this.here, this.righty, (this.amount - num), this.isBlack);
			}
		}
		else if(item.compareTo(this.here) < 0) {
			return new Things_ST(this.lefty.remove(item, num), this.here, this.righty, this.amount, this.isBlack);
		}
		else {
			return new Things_ST(this.lefty, this.here, this.righty.remove(item, num), this.amount, this.isBlack);
		}
	}
	public FiniteBags remove(I item){
		return this.remove(item, 1);
	}

	public FiniteBags removeAll(I item){
		int num = this.amount;
		return this.remove(item, num);
	}

	public FiniteBags union(FiniteBags baggy){
		return this.lefty.union(this.righty).union(baggy).add(this.here);
	}

	public FiniteBags inter(FiniteBags baggy){
		if(baggy.member(this.here)) {
			return new Things_ST(this.lefty.inter(baggy), this.here, this.righty.inter(baggy), this.amount, this.isBlack);
		}
		else {
			return this.lefty.union(this.righty).inter(baggy);
		}
	}

	public FiniteBags differ(FiniteBags t){
		return this.lefty.union(righty).differ(t.remove(this.here));
	}

	public boolean equal(FiniteBags baggy){
		if(baggy.member(this.here)) {
			return this.lefty.union(this.righty).equal(baggy.remove(this.here));
		}
		else {
			return false;
		}
	}

	public boolean subset(FiniteBags baggy){
		return (baggy.member(this.here) && this.lefty.subset(baggy) && this.righty.subset(baggy));
	}

	public FiniteBags blacken(){
		return new Things_ST(this.lefty, this.here, this.righty, this.amount, true);
	}

	public boolean isBlackHuh(){
		return this.isBlack;
	}

/*	public Sequence seq() {return this;}
	public boolean notEmpty() {return true;}
	public I here() {return this.here;}
	public Sequence next() {
		return new Things_Cat(this.lefty.seq(), this.righty.seq());
	}
*/
}

/*class Things_Cat implements Sequence{
	Sequence left;
	Sequence right;

	Things_Cat(Sequence l, Sequence r) {
		System.out.println("Alloc");
		this.left = l;
		this.right = r;
	}

	public boolean notEmpty() {
		return this.left.notEmpty() || this.right.notEmpty();
	}

	public I here() {
		if(this.left.notEmoty()) {
			return this.left.here();
		}
		else {
			return this.right.here;
		}
	}

	public Sequence next(){
		if(this.left.notEmpty()){
			return new Things_Cat(this.left.next(), this.right);
		}
		else{
			return this.right.next();
		}
	}
}

public interface Randomness<I extends Comparable> {
	public I createRand();
}

public class randString implements Randomness<String> {
	public static final String CHAR_LIST
		="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		public static final int RAND_STRING_LENGTH = genRandInt();

		public static int genRandINT(){
			Random rand = new Random();
			int randomNum = rand.nextINT((25 - 1) + 1) + 1;
			return randomNum;
		}
		public String createRand(){
			StringBuffer randString = new StringBuffer();
			for(int i = 0, i < RAND_STRING_LENGTH; i++){
				int num = genRandInt();
				char ch = CHAR_LIST.charAt(num);
				randString.append(ch);
			}
			return randString;
		}
}

public class randomInt implements Randomness<Integer>{

	public Integer createRand(){
		Random rand = new Random();
		int max = 100;
		int min = 0;
		return rand.nextInt((max - min) + 1) + min;
	}
}


class Testers<I extends Comparable>{

	static int count;
	static FiniteBags mt = new Things_MT();
	Randomness<I> rand;

	static int addRemCounter = 0;
	static int inDiffEq = 0;
	static int unionAndCard = 0;
	static int mtChecky = 0;
	static int removeAllTesty = 0;

public FiniteBags emptTree(){
	return new Things_MT();
}

public FiniteBags<I> randomTree(int i){
	if (i == 0){
		return emptTree();
	}
	else{
		return randomTree(i - 1).add(rand.createRand(), Utility.randomInt(1, 30));
	}

}

public void mtChecker(int count){
	for(int i = 0; i < 200; i++){
		if(this.amount == 0){
			FiniteBags mt = randomTree(count);
			int nummy = mt.cardinality();
			if(!mt.isEmptyHuh() || nummy != 0){
				throw RuntimeException("The empty tree is not empty!");
			}
		}
		else{
			FiniteBags randy = randomTree(count);
			if((randy.isEmptyHuh() && count!= 0) || randy.cardinality == 0){
				throw RuntimeException("A non-empty tree was seen as empty");
			}
		}
		mtChecky++;
	}
}

	public static void addRemCounterTesty(){
		for(int i = 0; i < 200; 1++){
			FiniteBags baggy = randomTree(Utility.randomInt(1, 100));
			I item = randomInt(1, 50);
			int num = randomInt(1, 15);

			if(baggy.counter(item) != baggy.add(item, num).remove(item, num).counter(item)){
				throw RuntimeException("addRemCounter did not work with " + baggy + " and " + item + " and " + num);
			}
			addRemCounter++;
		}
	}

	public static void inDiffEqTesty(){
		for(int i = 0; i < 200; i++){
			FiniteBags u = randomTree(Utility.randomInt(1, 50));
			FiniteBags v = randomTree(Utility.randomInt(1, 50));
			if(!u.inter(v).union(u.differ(v)).equal(u)){
				throw RuntimeException("Two finitebags in inDiffEqTesty did not make u when the inter and differ was found.");
				inDiffEq++;
			}
		}
	public static void unionCardTesty(){
		for(int i = 0; i < 200; i++){
			FiniteBags u = randomTree(Utility.randomInt(1, 50));
			FiniteBags v = randomTree(Utility.randomInt(1, 50));
			if(u.union(t).cardinality() != u.cardinality() + v.cardinality()){
				throw RuntimeException(u.cardinality() " + " + v.cardinality() + " did not equal " + u.union(v).cardinality());
			}
		unionAndCard++;
		}
	}

	public static void removeAllTest(){
		for(int i = 0; i < 200; i++){
			int num = Utility.randInt(0, 15);
			I rando = rand.createRand();
			int randNum = Utility.randomInt(3, 15);
			FiniteBags u = randTree(num); 
			FiniteBags v = u.add(rando, randNum);
			if(v.removeAll(rando).counter(rando) != 0){
				throw RuntimeException("Not all of " + rando + " was removed from the tree");
			}
			removeAllTesty++;
		}
	}
}
*/
