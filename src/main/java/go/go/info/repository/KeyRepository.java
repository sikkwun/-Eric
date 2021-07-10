package go.go.info.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import go.go.info.model.KeyVO;

@Repository
public class KeyRepository {
	private static final String MAPPER_NAME_SPACE = "mapper.keyMapper.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    public void policyRegister(KeyVO kVo) {
    	if("mysql".equals(kVo.getGenerator()) || "mariadb".equals(kVo.getGenerator())) {
    		sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "insertPolicyInfo", kVo);
    	} else {
    		sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "insertGenPolicyInfo", kVo);
    	}
    }
    
    public String findGenlastKeyNo() {
    	return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE+ "findGenlastKeyNo");
    }
    
    public String findlastKeyNo() {
    	return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE+ "findlastKeyNo");
    }
    
    public void claimRegister(KeyVO kVo) {
    	sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "insertClaimInfo", kVo);
    }
    
    public KeyVO findPolicyKeyNo(String userName) {
    	return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE+ "findPolicyKeyNo", userName);
    }
    
    public String findClaimKeyNo(String userName) {
    	return sqlSessionTemplate.selectOne(MAPPER_NAME_SPACE+ "findClaimKeyNo", userName);
    }
    
}
