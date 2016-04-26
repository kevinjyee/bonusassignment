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
	
	public void printResult(){
		System.out.println("Result: " + black_pegs + " black pegs and "  + white_pegs + " white pegs.");
	}
}
