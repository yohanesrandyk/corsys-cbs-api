package id.co.corsys.cbs.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.corsys.cbs.dto.BaseResponse;
import id.co.corsys.cbs.services.DatabaseService;
import id.co.corsys.cbs.services.LoginService;

@RestController
public class MainController {

	@Autowired
	private LoginService login;

	@PostMapping("login")
	public BaseResponse login(@RequestBody HashMap<String, Object> request, HttpServletRequest servlet) {
		BaseResponse response = new BaseResponse();
		try {
			response.setData(login.login(request));
		} catch (Exception ex) {
			response.setStatus(nvl(ex.getLocalizedMessage().split(";")[0], "99"));
			response.setMessage(nvl(ex.getLocalizedMessage().split(";")[1], ex.getLocalizedMessage()));
		}
		return response;
	}

	public String nvl(String a, String b) {
		if (a != null) {
			return a;
		}
		return b;

	}
}
