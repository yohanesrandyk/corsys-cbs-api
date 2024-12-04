package id.co.corsys.cbs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import id.co.corsys.cbs.security.CorsysPasswordEncoder;

@Repository
public class LoginService extends DatabaseService {
	public HashMap<String, Object> login(HashMap<String, Object> request) throws Exception {
		String password = CorsysPasswordEncoder.encode(request.get("userid") + "", request.get("password") + "",
//				"MAa022"
				"CbA046mSys881");
		List<Map<String, Object>> result = getJdbcTemplate(request.get("database") + "")
				.queryForList("SELECT * FROM MUSER WHERE USERID = ?", new Object[] { request.get("userid") });
		if (result.isEmpty()) {
			throw new Exception("01;USER TIDAK DITEMUKAN");
		}
		if (!result.get(0).get("PASSWORD").toString().matches(password)) {
			throw new Exception("02;PASSWORD TIDAK SESUAI");
		}
		return new HashMap<String, Object>() {
			{
				put("token", password);
			}
		};
	}
}
