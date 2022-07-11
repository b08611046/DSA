import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
public class RandomWord {
	public static void main(String[] args) {
		int num=0;
		String ans="";
		while(!StdIn.isEmpty()){
			String tmp=StdIn.readString();
			num++;
			if (StdRandom.bernoulli((double) 1 /num )){
				ans=tmp;
			}
		}
		StdOut.print(ans);
	}
}