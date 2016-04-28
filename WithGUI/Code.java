package bonusassignment;

import java.awt.Color;

public class Code {
	protected String code;
	protected String result;
	
	public Code(){
		this.code = "";
	}
	
	public Code(String input){
		if(isValidCode(input)){
			this.code = input;
		} else{
			throw new IllegalCodeException();
		}
	}
	
	// Case insensitive equals.
	public boolean equals(Code other){
		if(getCode().equalsIgnoreCase(other.getCode())){
			return true;
		} else{
			return false;
		}
	}
	
	public static boolean isValidCode(String input){
		// BGOPRY are the valid colors.
		if(input.length() != Mastermind.numPegs){
			return false;
		}
		for(int i = 0; i < input.length(); i++){
			String character = input.substring(i, i + 1);
			if("BbGgOoPpRrYy".indexOf(character) < 0){
				return false;
			}
		}
		return true;
	}
	
	public int[] getColorCount(){
		int[] result = new int[6];
		for(int i = 0; i < result.length; i++){
			result[i] = 0;
		}
		for(int i = 0; i < this.getCode().length(); i++){
			String character = this.getCode().substring(i, i + 1);
			switch(character){
				case "B": case "b": result[0]++;
					break;
				case "G": case "g": result[1]++;
					break;
				case "O": case "o": result[2]++;
					break;
				case "P": case "p": result[3]++;
					break;
				case "R": case "r": result[4]++;
					break;
				case "Y": case "y": result[5]++;
					break;
				default:
					break;
			}
		}
		return result;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public void setCode(String input){
		if(isValidCode(input)){
			this.code = input;
		} else{
			throw new IllegalCodeException();
		}
	}
	
	public void setResult(String input){
		this.result = input;
	}
	
	public String getResult(){
		return this.result;
	}
}
