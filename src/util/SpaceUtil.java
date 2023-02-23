package util;

public class SpaceUtil {

	public static void main(String[] args) {
		
		System.out.print("'");
		System.out.print(SpaceUtil.format("안녕하세요", 30, true));
		System.out.print("'");
		System.out.println();
		System.out.print("'");
		System.out.print(SpaceUtil.format("안녕하세요", 30, false));
		System.out.print("'");
		System.out.println();
		System.out.print("'");
		System.out.printf("%30s","안녕하세요");
		System.out.print("'");
		}

	
	public static String format(Object o, int length) {
		int spaceCount = length;
		String sO =o.toString();
		String result = sO;
		for(int i = 0; i < sO.length(); i++) {
			if(sO.charAt(i) >= 'ㄱ' && sO.charAt(i) <= '힣'){
				spaceCount -=2;
			}else {
				spaceCount --;
				
			}
		}
		for(int i =0; i< spaceCount; i++) {
			result += " ";
		}
	return result;
	}
	
	public static String format(Object o, int length, boolean right) {
		int spaceCount = length;
		String sO =o.toString();
		String result = "";
		for(int i = 0; i < sO.length(); i++) {
			if(sO.charAt(i) >= 'ㄱ' && sO.charAt(i) <= '힣'){
				spaceCount -=2;
			}else {
				spaceCount --;
			}
		}
		for(int i =0; i< spaceCount; i++) {
			result += " ";
		}
		if(right) {
			result += sO;
		}else {
			result = sO +result;
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
}
