

package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		ListNode newNode;
		String[] tex = sourceText.split("[ ]+");
		for(int i = 0; i< tex.length-1; i++){
			if((newNode = findWord(tex[i])) == null){
				newNode = new ListNode(tex[i]);
				wordList.add(newNode);
			}
			newNode.addNextWord(tex[i+1]);
		}
		starter = tex[0];
		if((newNode = findWord(tex[tex.length-1])) == null){
			newNode = new ListNode(tex[tex.length-1]);
			wordList.add(newNode);
		}
		newNode.addNextWord(tex[0]);
	}
	private ListNode findWord(String wordToFind){
		for(ListNode node : wordList){
			if(node.getWord().equals(wordToFind)){
				return node;
			}
		}
		return null;
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String bri = "";
		StringBuilder result = new StringBuilder();
		
		if(wordList.size()!= 0 && numWords != 0){
			result.append(starter);
			int count = 0;
			ListNode newNode = wordList.get(count);
			bri = newNode.getRandomNextWord(rnGenerator);
			result.append(" ");
			result.append(bri);
			while(count < numWords-2){
				for(ListNode myNode: wordList){
					if(myNode.getWord().equals(bri)){
						bri = myNode.getRandomNextWord(rnGenerator);
						break;
					}
				}
				result.append(" ");
				result.append(bri);
				count++;
			}
		}
		
		return result.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		starter = "";
		wordList.removeAll(wordList);
		ListNode newNode;
		String[] tex = sourceText.split("[ ]+");
		for(int i = 0; i< tex.length-1; i++){
			if((newNode = findWord(tex[i])) == null){
				newNode = new ListNode(tex[i]);
				wordList.add(newNode);
			}
			newNode.addNextWord(tex[i+1]);
		}
		starter = tex[0];
		if((newNode = findWord(tex[tex.length-1])) == null){
			newNode = new ListNode(tex[tex.length-1]);
			wordList.add(newNode);
		}
		newNode.addNextWord(tex[0]);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
//		gen.retrain(textString);
//		System.out.println(gen);
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int ran = generator.nextInt(nextWords.size());
		if(!this.word.equals("") && nextWords.size() != 0){
			return nextWords.get(ran);
		}
	    return "";
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


