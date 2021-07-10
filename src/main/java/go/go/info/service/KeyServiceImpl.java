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
		
		String msg = "Please try again in a few minutes.";	// ������ �޽���
		
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
	    			int cIdx = 1; 	// ���� �ڸ���
	    			int cCnt = 1;	// �⺻ �ڸ���
	    			
	    			String tmpStr = userRepository.findlastKeyNo();
	    			
	    			// ��ϵ� key�� �ִ°��
	    			if(tmpStr != null) {
	    				
	    				String str[] = tmpStr.split("-");

	    				// ��¥�� ���� ��� count�� �÷���
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
		
		// ���� unique�� ������ �̼����Ͽ� unique �� ������ �������� ����.
    	kVo = userRepository.findPolicyKeyNo(kVo.getUserName());
    	
    	kVo.setKeyNo(lpad(kVo.getKeyNo(), kVo.getMinLen(), "0"));
		
		return kVo.getKeyNo();
	}
	
	public String getClaimKey(KeyVO kVo) {
		
		// ���� unique�� ������ �̼����Ͽ� unique �� ������ �������� ����.
        kVo.setKeyNo(userRepository.findClaimKeyNo(kVo.getUserName()));
		
		return kVo.getKeyNo();
	}
	
	// ���� ��¥ 
    private static String getDate(String pattern) {
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		java.text.SimpleDateFormat chgformat = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);
		
		return chgformat.format(date);
		
	}
	
    // �ڸ��� ä���
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
		
		// ��� ��
		String rst = "";
		
		int padLen = pad.length(); // ���� ä���
		int blkLen = len - strLen; // ���� ����
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
