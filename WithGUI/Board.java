package bonusassignment;

import java.util.Random;

public class Board {
	public static int guesses;
	private Code secret_code;
	public Code[] board = new Code[Mastermind.numGuess];
	
	public Board(){
		guesses = 0;
		secret_code = new Code(generateSecretCode());
		for(int  i=0; i<Mastermind.numGuess;i++){
			board[i]= new Code();
		}
	
	}
	
	public static String generateSecretCode(){
		String validColors = "BGOPRY";
		String result = "";
		for(int i = 0; i < Mastermind.numPegs; i++){
			int randIndex = randInt(0, 5);
			String character = validColors.substring(randIndex, randIndex + 1);
			result = result + character;
		}
		return result;
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public int getNumGuesses(){
		return guesses;
	}
	
	public String getSecretCode(){
		return secret_code.getCode();
	}
	
	public void setNextGuess(String input){
		try{
			board[guesses].setCode(input);
			incrementGuesses();
		} catch(IllegalCodeException e){
			throw new IllegalCodeException();
		} 
		
	}
	
	public void setGuessResult(int index,String input){
		
			board[index].setResult(input);	
		
	}
	
	public Code getGuess(int index){
		if((index <= guesses) && (index >= 0)){
			return board[index];
		} else{
			return null;
		}
	}
	
	public void incrementGuesses(){
		guesses++;
	}
	
	public void getHistory(){
		if(Board.guesses == 0){
			System.out.println("-----No current Game History------");
		}
		else{
		System.out.println("------Game History---------");
		for(int i=0; i < Board.guesses; i++){
			int guessNumber = i+1;
			String guessValue = getGuess(i).getCode();
			String guessResult = getGuess(i).getResult();
			
			System.out.println("Guess " + guessNumber + ":" + guessValue + " ---> Result: " + guessResult);
		}
		System.out.println("------ End of History---------");
		}
	}

	
	public ResultPegs checkLastGuess(){
		ResultPegs result = new ResultPegs();
		Code last_guess = getGuess(guesses - 1);
		
		char[] guess = last_guess.code.toLowerCase().toCharArray();
		char[] secret = secret_code.code.toLowerCase().toCharArray();
		
		
		int bcount =0;
		int wcount =0;
		
		
		for(int i =0; i < Mastermind.numPegs; i++){
			if(guess[i] == secret[i]){
				guess[i] = secret[i] = '.';
				bcount ++;
			}
		}
	
		
		if(bcount != Mastermind.numPegs){
			for(int i =0; i < Mastermind.numPegs; i++){
				if(guess[i] != '.'){
					for(int j =0; j < Mastermind.numPegs; j++){
						if(guess[i] == secret[j]){
							wcount++;
							guess[i] = secret[j] = '.';
							break;
						}
					}
					
				}
			}
		}
			result.setBlackPegs(bcount);
			result.setWhitePegs(wcount);
		
		return result;
	}
	
}
