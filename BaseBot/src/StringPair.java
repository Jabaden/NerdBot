
public class StringPair {
	private String first;
	private String second;
	
	public StringPair(){
		first = null;
		second = null;
	}
	
	public StringPair(String f, String s){
		this.first = f;
		this.second = s;
	}
	
	public String getFirst(){
		return this.first;
	}
	
	public String getSecond(){
		return this.second;
	}
	
	public void setFirst(String f){
		this.first = f;
	}
	
	public void setSecond(String s){
		this.second = s;
	}
	
	public void makePair(String f, String s){
		this.first = f;
		this.second = s;
	}
}
