import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import go.go.info.KeySysMain;
import go.go.info.model.KeyVO;
import go.go.info.service.KeyService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KeySysMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KeySysTest {
	
	@Autowired
	private KeyService keyService;
	
	@Test
	public void testGetClaimKey() {
		KeyVO kVo = new KeyVO();
		kVo.setUserName("kim");		// 고객 정보기준으로 조회. 고객정보가 중복된 경우 MAX처리.
		String str = keyService.getClaimKey(kVo);
		
		System.out.println("kim Key : " + str);
		
		if(str == null || "".equals(str)) {
			assert(false);
		}
		
		kVo = new KeyVO();
		kVo.setUserName("Xxxxxxxxxxxxxxxxx");
		
		str = keyService.getClaimKey(kVo);
		
		System.out.println("Xxxxxxxxxxxxxxxxx Key : " + str);
		
		if(str != null && !"".equals(str)) {
			assert(false);
		}
		
	}
	
	@Test
	public void testGetPolicyKey() {
		KeyVO kVo = new KeyVO();
		kVo.setUserName("lee1");		// 고객 정보기준으로 조회. 고객정보가 중복된 경우 MAX처리.
		String str = keyService.getPolicyKey(kVo);
		
		System.out.println("lee1 Key : " + str);
		
		if(str == null || "".equals(str)) {
			assert(false);
		}
		
		kVo = new KeyVO();
		kVo.setUserName("Xxxxxxxxxxxxxxxxx");
		
		str = keyService.getClaimKey(kVo);
		
		System.out.println("Xxxxxxxxxxxxxxxxx Key : " + str);
		
		if(str != null && !"".equals(str)) {
			assert(false);
		}
		
	}
	
	@Test
	public void testRegisterKey() {
		
		KeyVO kVo = new KeyVO();
		kVo.setKey("policy-number");
		kVo.setDesc("test111");
		kVo.setGenerator("oracle");
		kVo.setUserName("AAAA");
		kVo.setMinLen(15);
		
		String msg = keyService.register(kVo);
		
		System.out.println("msg : " + msg);
		
		if(!msg.equals("Success")) {
			assert(false);
		}
		
		testMinLenKey(kVo);
		
		kVo.setKey("policy-number");
		kVo.setDesc("test222");
		kVo.setGenerator("mysql");
		kVo.setUserName("BBBB");
		kVo.setMinLen(40);
		
		msg = keyService.register(kVo);
			
		System.out.println("msg : " + msg);
		
		if(!msg.equals("Success")) {
			assert(false);
		}
		
		testMinLenKey(kVo);
		
		kVo.setKey("claim-number");
		kVo.setDesc("test333");
		kVo.setUserName("CCCCC");
		
		msg = keyService.register(kVo);
		
		System.out.println("msg : " + msg);
		
		if(!msg.equals("Success")) {
			assert(false);
		}
		
		kVo.setKey("ddddddddddd");
		kVo.setDesc("test333");
		kVo.setUserName("CCCCC");
		
		msg = keyService.register(kVo);
		
		System.out.println("msg : " + msg);
		
		// KEY 미등록 확인
		if(msg.equals("Success")) {
			assert(false);
		}

	}
	
	public void testMinLenKey(KeyVO kVo) {
		
		String str = keyService.getPolicyKey(kVo);
		System.out.println("len : " + kVo.getMinLen());
		System.out.println("str : " + str);
		
		if(kVo.getMinLen() != str.length()) {
			assert(false); // key자리수가 저장된 자리수를 초과 하는 경우 test시에는 에러 발생할수 있음
		}
	}
  
}
