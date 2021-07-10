package go.go.info.model;

public class KeyVO {
	private String key;
	private String type = "number";
	private String generator = "mysql";
	
	// 테이블 정보
	private String keyNo;
	private String userName;
	private String desc;
	private int minLen = 10;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		
		if("claim-number".equals(this.getKey())) {
			keyNo = "C" + keyNo;
		}
		
		this.keyNo = keyNo;
	}
	public String getDesc() {
		if(desc == null || "".equals(desc)) {
			if("claim-name".equals(this.getKey())) {
				desc = "register customer inquiry";
			} else {
				desc = "register Pno";
			}
				
		}
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public int getMinLen() {
		return minLen;
	}
	public void setMinLen(int minLen) {
		this.minLen = minLen;
	}
	public String getUserName() {
		if(userName == null || "".equals(userName)) {
			userName = "wrong User";
		}
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
