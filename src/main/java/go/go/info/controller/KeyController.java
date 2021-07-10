package go.go.info.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import go.go.info.model.KeyVO;
import go.go.info.service.KeyService;

@RestController
public class KeyController {
	
	@Autowired
	private KeyService keyService;
	
    @GetMapping
	public String keyAPIMain() {
        return "MAIN";
    }
	
	@GetMapping(value = "/api/key/policy-number/{userName}")
	public String getPolicyKe(@PathVariable String userName) {
		KeyVO kVo = new KeyVO();
		kVo.setUserName(userName);
		return keyService.getPolicyKey(kVo);
	}
	
	@GetMapping(value = "/api/key/claim-number/{userName}")
	public String getClaimKey(@PathVariable String userName) {
		KeyVO kVo = new KeyVO();
		kVo.setUserName(userName);
		return keyService.getClaimKey(kVo);
	}
	
	@PostMapping(value = "/api/key/register")
	public ResponseEntity<String> registerKey(HttpServletRequest req) {
		
		KeyVO kVo = new KeyVO();

		kVo.setKey(req.getParameter("key"));
		kVo.setDesc(req.getParameter("description"));
		kVo.setType(req.getParameter("type"));
		kVo.setGenerator(req.getParameter("generator"));
		kVo.setUserName(req.getParameter("userName"));
		kVo.setMinLen(Integer.parseInt(req.getParameter("min-length")));
		
		String msg = keyService.register(kVo);
		
		return new ResponseEntity(msg, HttpStatus.OK);
		
	}
    
}
