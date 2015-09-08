package assignment;

import java.util.ArrayList;

/**
 * @author ReN
 *
 */
public class WebInfo {
	private String[] HighDensityWord;
	private ArrayList<String> TitleKeyword;
	private String UrlWord;
	
	public String[] getHighDensityWord() {
		return HighDensityWord;
	}
	public void setHighDensityWord(String[] highDensityWord) {
		this.HighDensityWord = highDensityWord;
	}
	public ArrayList<String> getTitleKeyword() {
		return TitleKeyword;
	}
	public void setTitleKeyword(ArrayList<String> titleKeyword) {
		this.TitleKeyword = titleKeyword;
	}
	public String getUrlWord() {
		return UrlWord;
	}
	public void setUrlWord(String urlWord) {
		this.UrlWord = urlWord;
	}
	
	
	public  String getFinalResult() {
		// return the final results from high density words, URL keyword and title keywords. '
		System.out.println("Get URL Keywords...");
		StringBuffer result = new StringBuffer(); 
		if(UrlWord!=null){
			result.append(UrlWord+",  ");
		}
		for(int i=0;i<TitleKeyword.size();i++){
			String temp = result.toString();
			if(!temp.toLowerCase().contains(TitleKeyword.get(i).toLowerCase())){
				result.append(TitleKeyword.get(i) + ",  ");
			}
			
		}
		int NumOfHighDensityWord = 5;
		for(int i=0;i<NumOfHighDensityWord;i++){
			String temp = result.toString();
			if(HighDensityWord[i]!=null&&!temp.toLowerCase().contains(HighDensityWord[i].toLowerCase())){
				if(i<NumOfHighDensityWord-1){
					result.append(HighDensityWord[i] + ",  ");
				}else {
					result.append(HighDensityWord[i] + ".");
				}			
			}
		}
		return result.toString();
	}
	
	
}
