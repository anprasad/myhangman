package com.company.hangman.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.company.hangman.model.WordMgr;
import com.company.hangman.view.ScreenMgr;

/**
 * @author      Amit Prasad  email: amit_n_p@sympatico.ca
 * @version     1.0
 * @since       2015-11-15
 */
public class MyHangmanGame {

	private final int NUMBER_LIVES_START = 9;
	
	private List<Character> pickedLetters;
	
	private boolean winner;

	private ScreenMgr screenMgr;

	private WordMgr wordMgr;
	
	private ResourceBundle messages;

	private String secretWord;

	private String hiddenWord;

	private int livesLeft;

	private boolean isGameOver;

	public MyHangmanGame(ScreenMgr screenMgr, WordMgr wordMgr, ResourceBundle messages) {
		super();
		this.screenMgr = screenMgr;
		this.wordMgr = wordMgr;
		this.messages = messages;
	}

	public void playGame() throws IOException {

		//initialize variables
		pickedLetters = new ArrayList<Character>();
		livesLeft = NUMBER_LIVES_START;
		
		secretWord = wordMgr.getSecretWord();
		winner = false;

		screenMgr.showStartScreen();

		hiddenWord = wordMgr.makeAllLettersHidden(secretWord);
		String displayWord = wordMgr.addSpaceBetweenLetters(hiddenWord.split(""));
		screenMgr.showHangman(displayWord, pickedLetters, livesLeft);

		isGameOver = false;
		while(!isGameOver){


			Character userPickedLetter = screenMgr.getUserPickedLetter();

			boolean isAlreadyChosen = wordMgr.isLetterAlreadyChosen(userPickedLetter, pickedLetters);

			if(!isAlreadyChosen){

				pickedLetters.add(userPickedLetter);

				boolean isInSecretWord = wordMgr.isLetterInSecretWord(userPickedLetter, secretWord);

				if(!isInSecretWord){
					livesLeft--;
				}
			}
			else{
				livesLeft--;
			}

			showGameScreen(secretWord, pickedLetters, livesLeft);

			isGameOver = isGameFinished(secretWord, pickedLetters, livesLeft);

			if(isGameOver){

				if(winner){
					System.out.println(messages.getString("youWin"));
				}
				else{
					System.out.println(messages.getString("youLose"));
					System.out.println(messages.getString("secretWordWas")+secretWord.toUpperCase());
				}
				System.out.println(messages.getString("gameOver"));
				break;
			}
		}
	}

	private void showGameScreen(String secretWord, List<Character> pickedLetters, int livesLeft) throws IOException {
		String displayWord;
		hiddenWord = wordMgr.getHiddenWord(secretWord, pickedLetters);
		displayWord = wordMgr.addSpaceBetweenLetters(hiddenWord.split(""));
		screenMgr.clearScreen();
		screenMgr.showHangman(displayWord, pickedLetters, livesLeft);
	}

	private boolean isGameFinished(String secretWord, List<Character> pickedLetters, int livesLeft) {

		String hiddenWord = wordMgr.getHiddenWord(secretWord, pickedLetters);

		//if user completed guessing the entire word, then set user as winner
		if(hiddenWord.toLowerCase().equals(secretWord.toLowerCase())){
			winner = true;
			return true;
		}
		else if(livesLeft > 0){
			return false;
		}
		else{
			return true;

		}
	}











}
