package bonusassignment;

public class Code {
	protected String code;
	
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
	
	public static boolean isValidCode(String input){
		// BGOPRY are the valid colors.
		if(input.length() != 4){
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
}
