package com.company.hangman.main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.company.hangman.controller.MyHangmanGame;
import com.company.hangman.model.WordMgr;
import com.company.hangman.view.ScreenMgr;

/**
 * @author      Amit Prasad  email: amit_n_p@sympatico.ca
 * @version     0.0.1
 * @since       2015-11-15
 */
public class MyHangmanMain {

	public static void main(String[] args) {

		//supported languages and countries
		//		Locale caenLocale = new Locale("en","CA");
		//		Locale usenLocale = new Locale("en","US");
		//		Locale cafrLocale = new Locale("fr","CA");
		//		Locale frfrLocale = new Locale("fr","FR");
		String language;
		String country;

		if(args.length > 0){
			language = new String(args[0]);
			country = new String(args[1]);
		}
		else{
			language = "en";
			country = "US";
		}
		Locale currentLocale = new Locale(language, country);

		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

		ResourceBundle wordsFile = ResourceBundle.getBundle("words", currentLocale);

		ScreenMgr screenMgr = new ScreenMgr(messages);
		WordMgr wordMgr = new WordMgr(messages, wordsFile);

		MyHangmanGame hangmanGame = new MyHangmanGame(screenMgr, wordMgr, messages);
		try {
			hangmanGame.playGame();
		} catch (IOException e) {
			System.out.println(messages.getString("errorPlaying"));
			e.printStackTrace();
			System.exit(0);
		}
	}

}
