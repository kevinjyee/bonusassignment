package bonusassignment;

public class ResultPegs {
	private int white_pegs;
	private int black_pegs;
	
	ResultPegs(){
		white_pegs = 0;
		black_pegs = 0;
	}
	
	public int getWhitePegs(){
		return white_pegs;
	}
	
	public int getBlackPegs(){
		return black_pegs;
	}
	
	public void setWhitePegs(int num_pegs){
		white_pegs = num_pegs;
	}
	
	public void setBlackPegs(int num_pegs){
		black_pegs = num_pegs;
	}
	
	public String getResult(){
		return  black_pegs + " black pegs and "  + white_pegs + " white pegs.";
	}
	
	public static ResultPegs checkGuess(Code guess, Code secretCode){
		ResultPegs result = new ResultPegs();
		int[] secretCodeColorCount = secretCode.getColorCount();
		int[] guessColorCount = guess.getColorCount();
		
		int bcount = 0;
		int wcount = 0;
		
		for(int i = 0; i < 6; i++){
			if((secretCodeColorCount[i] > 0) && (guessColorCount[i] > 0)){
				wcount = wcount + Math.min(secretCodeColorCount[i], guessColorCount[i]);
			}
		}
		
		String gs = guess.getCode();
		String sc = secretCode.getCode();
		for(int i = 0; i < guess.getCode().length(); i++){
			if(gs.substring(i, i + 1).equalsIgnoreCase(sc.substring(i,  i + 1))){
				bcount++;
			}
		}
		wcount = wcount - bcount;
		result.setWhitePegs(wcount);
		result.setBlackPegs(bcount);
		
		return result;
	}
}
