package com.ukefu.webim.web.handler.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.UserRepository;
import com.ukefu.webim.service.repository.UserRoleRepository;
import com.ukefu.webim.util.RestResult;
import com.ukefu.webim.util.RestResultType;
import com.ukefu.webim.web.handler.Handler;
import com.ukefu.webim.web.model.User;

@RestController
@RequestMapping("/api/user")
public class ApiUserController extends Handler{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRes ;

	/**
	 * 返回用户列表，支持分页，分页参数为 p=1&ps=50，默认分页尺寸为 20条每页
	 * @param request
	 * @param username	搜索用户名，精确搜索
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET)
	@Menu(type = "apps" , subtype = "user" , access = true)
    public ResponseEntity<RestResult> list(HttpServletRequest request , @Valid String id ,@Valid String username) {
		Page<User> userList = null ;
		if(!StringUtils.isBlank(id)){
			userList = userRepository.findByIdAndOrgi(id, super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))) ;
		}else{
			if(!StringUtils.isBlank(username)){
				userList = userRepository.findByDatastatusAndOrgiAndUsernameLike(false, super.getOrgi(request), username , new PageRequest(super.getP(request), super.getPs(request))) ;
			}else{
				userList = userRepository.findByDatastatusAndOrgi(false, super.getOrgi(request), new PageRequest(super.getP(request), super.getPs(request))) ;
			}
		}
        return new ResponseEntity<>(new RestResult(RestResultType.OK, userList), HttpStatus.OK);
    }
	
	/**
	 * 新增或修改用户用户 ，在修改用户信息的时候，如果用户 密码未改变，请设置为 NULL
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@Menu(type = "apps" , subtype = "user" , access = true)
    public ResponseEntity<RestResult> put(HttpServletRequest request , @RequestParam User user) {
    	if(user != null && !StringUtils.isBlank(user.getUsername())){
    		if(!StringUtils.isBlank(user.getPassword())){
	    		user.setPassword(UKTools.md5(user.getPassword()));
	    		userRepository.save(user) ;
    		}else if(!StringUtils.isBlank(user.getId())){
    			User old = userRepository.findByIdAndOrgi(user.getId(), super.getOrgi(request)) ;
    			user.setPassword(old.getPassword());
    			userRepository.save(user) ;
    		}
    	}
        return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
    }
	
	/**
	 * 删除用户，只提供 按照用户ID删除 ， 并且，不能删除系统管理员
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@Menu(type = "apps" , subtype = "user" , access = true)
    public ResponseEntity<RestResult> delete(HttpServletRequest request , @RequestParam String id) {
		RestResult result = new RestResult(RestResultType.OK) ; 
    	User user = null ;
    	if(!StringUtils.isBlank(id)){
    		user = userRepository.findByIdAndOrgi(id, super.getOrgi(request)) ;
    		if(!user.getUsertype().equals("0")){	//系统管理员， 不允许 使用 接口删除
    			userRepository.delete(user);
    		}else{
    			result.setStatus(RestResultType.DELETE);
    		}
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}