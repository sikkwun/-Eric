package go.go.info.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import go.go.info.model.KeyVO;
import go.go.info.repository.KeyRepository;

@Service("KeyService")
public class KeyServiceImpl  implements KeyService {
	
	@Autowired
	KeyRepository userRepository;
	
	public String register(KeyVO kVo) {
		
		String msg = "Please try again in a few minutes.";	// 오류시 메시지
		
		if(kVo.getKey() != null){
    		
    		try {
	    		if("policy-number".equals(kVo.getKey())){
	    			
	    			if("mysql".equals(kVo.getGenerator()) || "mariadb".equals(kVo.getGenerator())) {
	    				userRepository.policyRegister(kVo);
	    			} else {
	    				
	    				String str = userRepository.findGenlastKeyNo();
	    				
	    				if(str != null && !"".equals(str)){
	    					kVo.setKeyNo((Integer.parseInt(str) + 1) + "");
	    				}
	    				userRepository.policyRegister(kVo);
	    				
	    			}
	    			
	    			msg = "Success";
	    		}
	    		else if("claim-number".equals(kVo.getKey())) {
	    			
	    			String cKey = "";
	    			int cIdx = 1; 	// 예비 자리수
	    			int cCnt = 1;	// 기본 자리수
	    			
	    			String tmpStr = userRepository.findlastKeyNo();
	    			
	    			// 등록된 key가 있는경우
	    			if(tmpStr != null) {
	    				
	    				String str[] = tmpStr.split("-");

	    				// 날짜가 같은 경우 count를 올려줌
	    				if((str[1]+str[2]).equals(getDate("yyyyMMdd"))) {
	    					
	    					cIdx = Integer.parseInt(str[0].substring(1));
	    					cCnt = Integer.parseInt(str[3]) + 1;
	    					
	    					if(cCnt >= 10000) {
	    						cIdx += 1;
	    						cCnt = 1;				
	    					} 
	    					
	    					cKey  = "" + lpad(Integer.toString(cIdx), 3, "0") + "-" + str[1] + "-" + str[2] + "-" + lpad(Integer.toString(cCnt), 4, "0");  
	    		
	    				} else {
	    					cKey  = "" + lpad(Integer.toString(cIdx), 3, "0") + getDate("-yyyy-MMdd-") + lpad(Integer.toString(cCnt), 4, "0");  
	    				}

	    			} else {
	    				cKey  = "" + lpad(Integer.toString(cIdx), 3, "0") + getDate("-yyyy-MMdd-") + lpad(Integer.toString(cCnt), 4, "0");  
	    			}
	    			
	    			kVo.setKeyNo(cKey);
	    			
	    			userRepository.claimRegister(kVo);
	    			msg = "Success";
	    		} else {
	    			return msg;
	    		}
    		} catch(Exception e){
    			e.getMessage();
    			return msg;
    		} 
    	} else {
    		return msg;
    	}
		
		return msg;
		
	}
	
	public String getPolicyKey(KeyVO kVo) {
		
		// 고객의 unique한 정보를 미설정하여 unique 한 정보는 가져오지 않음.
    	kVo = userRepository.findPolicyKeyNo(kVo.getUserName());
    	
    	kVo.setKeyNo(lpad(kVo.getKeyNo(), kVo.getMinLen(), "0"));
		
		return kVo.getKeyNo();
	}
	
	public String getClaimKey(KeyVO kVo) {
		
		// 고객의 unique한 정보를 미설정하여 unique 한 정보는 가져오지 않음.
        kVo.setKeyNo(userRepository.findClaimKeyNo(kVo.getUserName()));
		
		return kVo.getKeyNo();
	}
	
	// 현재 날짜 
    private static String getDate(String pattern) {
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		java.text.SimpleDateFormat chgformat = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);
		
		return chgformat.format(date);
		
	}
	
    // 자리수 채우기
	private String lpad(String src, int len, String pad) {
		
		if(src == null)
			src = "";
		if(pad == null)
			pad = "";
		
		int strLen = src.length();
		
		if(strLen > len) {
			return src.substring(0, len);
		} else if(strLen == len) {
			return src;
		}
		
		// 결과 값
		String rst = "";
		
		int padLen = pad.length(); // 왼편 채우기
		int blkLen = len - strLen; // 빈값의 길이
		int cnt = blkLen / padLen;
		
		while(cnt > 0) {
			rst = rst + pad;
			cnt--;
		}
		
		int mod = blkLen % padLen;
		
		if(mod > 0)
			rst = rst + pad.substring(0, mod);
		
		rst = rst + src;
		
		return rst;
		
	}
	
}
