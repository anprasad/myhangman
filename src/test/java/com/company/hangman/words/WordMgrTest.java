package com.company.hangman.words;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.company.hangman.model.WordMgr;

/**
 * @author      Amit Prasad
 * @version     0.0.1
 * @since       2015-11-15
 */
public class WordMgrTest {
	
	private boolean testField;
	private WordMgr words;
	private ResourceBundle messages;
	private ResourceBundle wordsFile;
	private Locale currentLocale;
	
	@Before
	public void setup(){
//		Locale aLocale = new Locale("en","US");
//		Locale caLocale = new Locale("fr","CA");
//		Locale frLocale = new Locale("fr","FR");
		
		currentLocale = new Locale("en","CA");
		
		messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
		wordsFile = ResourceBundle.getBundle("words", currentLocale);
		words = new WordMgr(messages,wordsFile);
		testField = false;
	}

	@Test
	public void testgetHiddenWord() {
		String secretWord = "MONTREAL";
		List<Character> pickList = new ArrayList<Character>();
		pickList.add('M');
		pickList.add('T');
		pickList.add('A');
		
		String hiddenWord = words.getHiddenWord(secretWord, pickList);
		System.out.println("The hidden word is: "+hiddenWord);
		Assert.assertEquals("Asserting hidden word", "M__T__A_", hiddenWord);
	}
	
	@Test
	public void testNotEnoughSecretWordsInFile() throws IOException{

		testField = false;
		Locale caLocale = new Locale("en","CA");
		ResourceBundle wordsFile = ResourceBundle.getBundle("empty", caLocale);
		WordMgr wordsEmpty = new WordMgr(messages,wordsFile);
		Stream.of(wordsEmpty.getSecretWord()).forEach(w -> {
			if(w.equals("????")){
				System.out.println("There are not enough words in file");
				testField = true;
			}
		});

		Assert.assertTrue(testField == true);
	}
	
	@Test
	public void testGetWordsFromFile() throws IOException{
		List<String> uniqueWords = words.getUniqueWordsFromFile();
		Assert.assertTrue(uniqueWords.size() > 0);
	}
	
	@Test
	public void testIsLetterInSecretWord(){
		
		testField = words.isLetterInSecretWord('s', "test");
		Assert.assertTrue(testField);
		
		testField = words.isLetterInSecretWord('z', "test");
		Assert.assertFalse(testField);
	}
	
	@Test
	public void testIsLetterAlreadyChosen(){
		
		Character[] pickedArray = {'R','S','T','L','N','E'}; 
		List<Character> pickedLetters = Arrays.asList(pickedArray);
		
		testField = words.isLetterAlreadyChosen('t', pickedLetters);
		Assert.assertTrue(testField);
		
		testField = words.isLetterAlreadyChosen('z', pickedLetters);
		Assert.assertFalse(testField);
	}
	
	@Test
	public void testMakeAllLettersHidden(){
		
		String result = words.makeAllLettersHidden("hello");
		Assert.assertEquals("Check all letters are hidden for word 'hello'", "_____",result);
	}

}
