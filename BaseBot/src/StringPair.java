import java.util.Arrays;

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
	
	public boolean isAMatch(String one, String two){
		String[] s1 = {this.first, this.second};
		String[] s2 = {one, two};
		Arrays.sort(s1);
		Arrays.sort(s2);
		
		return ( Arrays.equals(s1, s2) );
	}
	
	public boolean isAMatch(StringPair sp){
		String[] s1 = {this.first, this.second};
		String[] s2 = {sp.first, sp.second};
		Arrays.sort(s1);
		Arrays.sort(s2);
		
		return ( Arrays.equals(s1, s2) );
	}
}
