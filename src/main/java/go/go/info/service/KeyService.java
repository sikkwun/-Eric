package go.go.info.service;

import go.go.info.model.*;

public interface KeyService {
	
	String register(KeyVO kVo);
	String getPolicyKey(KeyVO kVo);
	String getClaimKey(KeyVO kVo);
	
}
