package com.ssafy.culture.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.culture.dto.User;
import com.ssafy.culture.service.JwtService;
import com.ssafy.culture.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	private UserService userService;
	private JwtService jwtService;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	public UserController(UserService userService, JwtService jwtService) {
		super();
		this.userService = userService;
		this.jwtService = jwtService;
	}

	// 회원가입
	@PostMapping
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			int result = userService.addUser(user);
			if (result > 0)
				resultMap.put("message", SUCCESS);
			status = HttpStatus.CREATED;
		} catch (SQLException e) {
			resultMap.put("msg", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		System.out.println("login 실행!!");
		System.out.println(user);
		Map<String, Object> resultMap = new HashMap<>();
		User loginUser = null;
		HttpStatus status = null;
		try {
			loginUser = userService.login(user);
			System.out.println(loginUser.toString());
			// 로그인이 성공했다면!!
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userId", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userId", loginUser.getUserid());// key, data
				userService.saveRefreshToken(loginUser.getUserid(), refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (SQLException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 마이페이지
	@GetMapping("/info/{userId}")
	public ResponseEntity<?> getInfo(@PathVariable("userId") long userId, HttpServletRequest request) {

		System.out.println(userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			try {
				User userInfo = userService.getUser(userId);
				resultMap.put("userInfo", userInfo);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/findid")
	public ResponseEntity<String> getId(@RequestBody Map<String, String> userinfo){
		
		String result_username = null;
		HttpStatus status = null;
		
		try {
			result_username = userService.findId(userinfo);
			status = HttpStatus.ACCEPTED;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(result_username,status);
	}
	
	// 비밀번호 찾기 ( 임시 비밀번호 메일로 전송 ! )
	@PostMapping("/findpwd")
	public ResponseEntity<Map<String, Object>> getPassword(@RequestBody Map<String, String> request) {
		User getUser = null;
		HttpStatus status = null;
		System.out.println("input 값 확인 >> " + request.toString());
		Map<String, Object> resultMap = new HashMap<>();

		try {
			getUser = userService.findPassword(request);
			if (getUser != null) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS); // 사용할 수 있는 아이디 입니다.

				// 아이디와 이메일, 확인 완료 해당 이메일로 임시비밀번호 보내기!!
				userService.sendEmail(getUser);
				// 이메일을 성공적으로 보냇다면!!
				System.out.println("잘보냈습니다!");

			} else {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", FAIL); // 존재잘못된 이메일 또는 아이디 입니다.
			}
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 아이디 중복체크 기능(시간 관계상 화면 구현까지는 가지 못했습니다.)
	@PostMapping("/idcheck")
	public ResponseEntity<Map<String, Object>> idcheck(@RequestBody User user) {
		User getUser = null;
		HttpStatus status = null;
		Map<String, Object> resultMap = new HashMap<>();

		try {
			getUser = userService.getByUserName(user.getUsername());
			if (getUser != null) {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", FAIL); // 이미 가입한 사람이 존재합니다.
			} else {
				status = HttpStatus.ACCEPTED;
				resultMap.put("message", SUCCESS); // 사용할 수 있는 아이디 입니다.
			}
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그아웃
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String userId, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			userService.deleRefreshToken(userId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// 회원탈퇴
	@DeleteMapping("/{userId}")
	public ResponseEntity<Integer> deleteUser(@PathVariable long userId) {
		int result = 0;
		HttpStatus status = null;
		try {
			result = userService.removeUser(userId);
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Integer>(result, status);
	}

	// 회원정보 수정
	@PutMapping
	public ResponseEntity<Integer> modifyUser(@RequestBody User user) {
		int result = 0;
		HttpStatus status = null;
		try {
			result = userService.modifyUser(user);
			System.out.println(result);
			status = HttpStatus.OK;
		} catch (SQLException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Integer>(result, status);
	}

	// refresh토큰 초기화!
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User getUser, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(getUser.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userId", getUser.getUserid());
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
