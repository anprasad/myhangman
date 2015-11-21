package com.company.hangman.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author      Amit Prasad  email: amit_n_p@sympatico.ca
 * @version     1.0
 * @since       2015-11-15
 */
public class WordMgr{
	
	private ResourceBundle messages;
	private ResourceBundle wordsFile;
	
	public WordMgr(ResourceBundle messages, ResourceBundle wordsFile){
		this.messages = messages;
		this.wordsFile = wordsFile;
	}

	public String getSecretWord() throws IOException {

		Integer randInt = getRandomInteger();
		List<String> allWords = getUniqueWordsFromFile();


		if(allWords.size() > 0 && allWords.size() >= 100){
			return allWords.get(randInt);
		}
		else{
			System.out.println(messages.getString("notEnoughWords"));
			return "????";
		}

	}

	public Integer getRandomInteger(){

		int[] randomBetween0And99 = new Random().ints(100, 1, 100).toArray();

		/*		Random random = new Random();
		IntStream intStream = random.ints(0, 100);

		List<Integer> randomBetween0And99 = intStream
				.limit(100)
				.boxed()
				.collect(Collectors.toList());*/

		return randomBetween0And99[0];
	}

	public List<String> getUniqueWordsFromFile() throws IOException{
		
		Path currentDir = Paths.get(".");
		System.out.println("absolute path: "+currentDir.toAbsolutePath());

		//try (final Stream<String> lines = Files.lines(Paths.get(new Scanner( this.getClass().getClassLoader().getResource(fileName).getFile()).nextLine()  ) )
		//try (final Stream<String> lines = Files.lines(Paths.get(new Scanner(fileName).nextLine()  ) )
		try (final Stream<String> lines = wordsFile.keySet().stream()
				.map(line -> line.split("[\\s]+"))
				.flatMap(Arrays::stream)
				.distinct()
				.sorted()) {
			final List<String> uniqueWords = lines.collect(Collectors.toList());

			return uniqueWords;
		}
	}

	public String getHiddenWord(String secretWord, List<Character> pickedLetters) {
		
		List<Character> pickedChars = pickedLetters.stream().map(x -> Character.toLowerCase(x)).collect(Collectors.toList());

		String hiddenWord = secretWord.toLowerCase().chars()
				.mapToObj(i ->(char)i)
				.map(x ->
				{
					if(pickedChars.contains(x)){
						return x;
					}
					else{
						return '_';
					}
				})
				.map(i -> i.toString())
				.collect(Collectors.joining(""));

		return hiddenWord.toUpperCase();
	}

	public String addSpaceBetweenLetters(String[] hiddenWord) {
		return String.join(" ", hiddenWord);
	}

	public String makeAllLettersHidden(String secretWord) {
	
		return secretWord.chars()
				.mapToObj(i ->(char)i)
				.map(m -> m = '_')
				.map(i -> i.toString())
				.collect(Collectors.joining(""));
	}

	public boolean isLetterInSecretWord(Character userSelection, String secretWord) {

		return secretWord.toLowerCase().contains(userSelection.toString().toLowerCase());

	}

	public boolean isLetterAlreadyChosen(Character chosenLetter, List<Character> pickedLetters) {

				return pickedLetters
				.stream()
				.map(m -> Character.toLowerCase(m))
				.filter(f -> f.equals(Character.toLowerCase(chosenLetter)))
				.findFirst().isPresent();
	}
}
