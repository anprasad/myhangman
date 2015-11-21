package com.company.hangman.view;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author      Amit Prasad  email: amit_n_p@sympatico.ca
 * @version     1.0
 * @since       2015-11-15
 */
public class ScreenMgr {

	private Scanner input;
	private ResourceBundle messages;
	
	public ScreenMgr(ResourceBundle messages){
		this.input = new Scanner(System.in);
		this.messages = messages;
	}

	public void clearScreen() throws IOException{
		try {
			  if( System.getProperty( "os.name" ).startsWith( "Window" ) ) {
			     Runtime.getRuntime().exec("cls");
			  } else {
			     Runtime.getRuntime().exec("clear");
			  }
			} catch (IOException e) {
			  for(int i = 0; i < 1000; i++) {
			    System.out.println();
			  }
			}
	}

	public void showStartScreen() {

		System.out.println(messages.getString("myGame"));
		String userInput = "";
		do{
			System.out.println(messages.getString("startGame"));
			userInput = input.next();
			if(userInput.toLowerCase().equals("0")){
				System.out.println(messages.getString("goToexit"));
				input.close();
				System.exit(0);
			}
		}
		while(!userInput.toLowerCase().equals("1"));
	}

	public void showHangman(String hiddenWord, List<Character> pickedLetters, int livesLeft) throws IOException {

		if(livesLeft==8)
		{
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==7)
		{
			System.out.println("-------");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==6)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==5)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==4)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|      |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==3)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|    __|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==2)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|    __|__");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==1)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|    __|__");
			System.out.println("|     /");
			System.out.println("|     |");
			System.out.println("|");
			System.out.println("---");
		}
		else if(livesLeft==0)
		{
			System.out.println("-------");
			System.out.println("|      |");
			System.out.println("|      0");
			System.out.println("|    __|__");
			System.out.println("|     / \\");
			System.out.println("|     | |");
			System.out.println("|");
			System.out.println("---");
		}
		else{
			//do nothing
		}

		System.out.println(messages.getString("hiddenWord")+hiddenWord);
		System.out.print(messages.getString("lettersPicked"));
		pickedLetters.forEach(System.out::print);
		System.out.println();
		System.out.println(messages.getString("numLivesLeft")+livesLeft);

	}

	public Character getUserPickedLetter() {

		String userPickedLetter = "";
		boolean validLetter = false;

		while(!validLetter){

			System.out.println(messages.getString("chooseLetter"));
			userPickedLetter = input.next();			
			validLetter = userPickedLetter.chars().allMatch(x -> Character.isLetter(x));

			if(!validLetter) System.out.println(messages.getString("chooseLetterAgain"));
		}

		return userPickedLetter.toUpperCase().toCharArray()[0];

	}

}
