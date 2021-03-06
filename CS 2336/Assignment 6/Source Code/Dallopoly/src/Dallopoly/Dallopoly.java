/*
 Copyright (c) 2014, Daniel Eisterhold
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 
 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 
 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package Dallopoly;

import java.util.ArrayList;

public class Dallopoly {
	//Variables to hold data about a spinner, a board
	private Spinner theSpinner;
	private Board theBoard;
	private ArrayList<Player> players;
	private String output;
	
	public Dallopoly() {
		//Initialize the string that holds the output
		output = new String();
		
		//Initialize the ArrayList
		players = new ArrayList<Player>();
		
		//Initialize the Board
		this.theBoard = new Board();
		
		//Initialize the Spinner
		this.theSpinner = new Spinner();
	}
	
	public void addPlayer(String playerName) {
		//Add the player to the ArrayList and output info about the player
		players.add(new Player(playerName, theBoard.getStartSquare()));
		output += players.get(players.size()-1).toString();
	}
	
	public String playGame() {
		//set a flag that will change when the game ends
		boolean gameOver = false;
		
    	//Create an outer loop that exits when a player wins
    	while(gameOver == false) {
    		//Loop through the ArrayList of players and
    		//send each player the "takeTurn" message.
    		//After the player has moved, compare the player’s new square
    		//to the Board’s lastSquare. If the player is on the Board’s
    		//last square, declare that player the winner and exit the
    		//while loop.
    		
    		//Add an extra newline
    		output += '\n';
    		for(int player = 0; player < players.size(); player++) {
    			//Have the current player take a turn
    			output += players.get(player).takeTurn(this.theSpinner, this.theBoard);
    			
    			//Check if the player is on the last square
    			if(players.get(player).getCurrentSquare().equals(theBoard.getLastSquare())) {
    	    		gameOver = true;
    	    		//Output who landed on the last square
    	    		output += ("GAME OVER!!! " + players.get(player).getName() + " Landed on " + players.get(player).getCurrentSquare().getLabel() + " Square\n");
    	    		
    	    		//Exit the for loop
    	    		break;
    	    	}
    		}
    	}//end while
    	
    	//Figure out who has the most money and output who that is
    	int maxMoney = players.get(0).getMoney();
    	Player playerWithMostMoney = players.get(0);
    	boolean isTie = false;
    	
    	//Loop through the players to see has the most money
    	for(int player = 1; player < players.size(); player++) {
    		if(players.get(player).getMoney() > maxMoney) {
    			maxMoney = players.get(player).getMoney();
    			playerWithMostMoney = players.get(player);
    		}
    		else if(players.get(player).getMoney() == maxMoney) {
    			isTie = true;
    		}
    	}
    	
		if(isTie) {
			//Output who the winner is
			output += "TIE\n";
		}
		else {
			//Output who the winner is
			output += ("THE WINNER IS " + playerWithMostMoney.toString());
		}
    	return output;
    }//end playGame
}
