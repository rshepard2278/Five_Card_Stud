/**
 * @author Richard Shepard
 * @version Apr 23, 2015
 */
package com.rshepard.fivecard.game;

import com.rshepard.fivecard.controller.Controller;

public class StartFiveCardStud {


	public StartFiveCardStud() {

	}

	public static void main(String[] args) {
		Controller gameController = new Controller();
		gameController.play();	
	}
			
}
