package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.mapper.RoomTypeMapper;
import com.java.po.Account;
import com.java.po.RoomType;
import com.java.service.AccountService;
import com.java.service.RoomTypeService;
import com.java.util.EmailUtil;
import com.java.util.MD5Util;
import com.java.util.PageUtil;

/**
 * 前台首页控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping("/home")
@Controller
public class HomeController {

	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private RoomTypeMapper roomTypeMapper;

	/**
	 * 前台首页
	 * 
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model, @RequestParam(name = "name", defaultValue = "") String name) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
//		queryMap.put("offset", 0);
//		queryMap.put("pageSize", 999);
		model.addObject("roomTypeList", roomTypeService.findList(queryMap));
		model.setViewName("home/index/index");
		model.addObject("kw", name);
		return model;
	}

	/**
	 * 前台首页
	 * 
	 * @param model
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView list2(ModelAndView model, @RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "offset", defaultValue = "0") String offset) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 4);
		int record = roomTypeMapper.record();
		PageUtil<RoomType> pageUtill = new PageUtil<RoomType>(Integer.parseInt(offset), 4, record);
		model.setViewName("home/index/index2");
		if (Integer.parseInt(offset) != 0) {
			queryMap.put("offset", Integer.parseInt(offset));
//			model.setViewName("home/index/table");
		}
		model.addObject("roomTypeList", roomTypeService.findList(queryMap));
		if (Integer.parseInt(offset) > pageUtill.getEndIndex()) {
			queryMap.put("offset", pageUtill.getEndIndex());
			model.addObject("endIndex", "最大页数为：" + pageUtill.getEndIndex());
		}
		model.addObject("pageUtil", pageUtill);
		model.addObject("kw", name);
		return model;
	}

	//改动
	@RequestMapping(value = "/ll", method = RequestMethod.POST)
	public ModelAndView list3(ModelAndView model, HttpServletRequest request,
							  @RequestParam(name = "offset", defaultValue = "0") String offset) {
		String name=request.getParameter("as");

		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 4);
		int record = roomTypeMapper.record();
		model.addObject("roomTypeList",roomTypeService.findList(queryMap));
		int record1=roomTypeMapper.getTotal(queryMap);
		PageUtil<RoomType> pageUtill = new PageUtil<RoomType>(Integer.parseInt(offset), 4, record1);
		model.setViewName("home/index/index2");
		if (Integer.parseInt(offset) != 0) {
			queryMap.put("offset", Integer.parseInt(offset));
//			model.setViewName("home/index/table");
		}
		model.addObject("record",record1);
		if (Integer.parseInt(offset) > pageUtill.getEndIndex()) {
			queryMap.put("offset", pageUtill.getEndIndex());
			model.addObject("endIndex", "最大页数为：" + pageUtill.getEndIndex());
		}
		model.addObject("pageUtil", pageUtill);
		model.addObject("kw", name);
		return model;
	}

	/**
	 * 登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("home/index/login");
		return model;
	}

	/**
	 * 登录页面2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public ModelAndView login2(ModelAndView model) {
		model.setViewName("home/index/login2");
		return model;
	}

	/**
	 * 注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public ModelAndView reg(ModelAndView model) {
		model.setViewName("home/index/reg");
		return model;
	}

	/**
	 * 登录信息提交
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(Account account, String vcode, HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (account == null) {
			retMap.put("type", "error");
			retMap.put("msg", "请填写正确的用户信息！");
			return retMap;
		}
		if (StringUtils.isEmpty(account.getName())) {
			retMap.put("type", "error");
			retMap.put("msg", "用户名不能为空！");
			return retMap;
		}
		if (StringUtils.isEmpty(account.getPassword())) {
			retMap.put("type", "error");
			retMap.put("msg", "密码不能为空！");
			return retMap;
		}
		if (StringUtils.isEmpty(vcode)) {
			retMap.put("type", "error");
			retMap.put("msg", "验证码不能为空！");
			return retMap;
		}
		Object attribute = request.getSession().getAttribute("accountLoginCpacha");
		if (attribute == null) {
			retMap.put("type", "error");
			retMap.put("msg", "验证码过期，请刷新！");
			return retMap;
		}
		if (!vcode.equalsIgnoreCase(attribute.toString())) {
			retMap.put("type", "error");
			retMap.put("msg", "验证码错误！");
			return retMap;
		}
		Account findByName = accountService.findByName(account.getName());
		if (findByName == null) {
			retMap.put("type", "error");
			retMap.put("msg", "用户名不存在！");
			return retMap;
		}
		if (!MD5Util.md5Password(account.getPassword()).equals(findByName.getPassword())) {
			retMap.put("type", "error");
			retMap.put("msg", "密码错误！");
			return retMap;
		}
		if (findByName.getStatus() == -1) {
			retMap.put("type", "error");
			retMap.put("msg", "该用户已被禁用，请联系管理员！");
			return retMap;
		}
		request.getSession().setAttribute("account", findByName);
		request.getSession().setAttribute("accountLoginCpacha", null);
		retMap.put("type", "success");
		retMap.put("msg", "登录成功！");
		return retMap;
	}

	/**
	 * 登录信息提交
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/czpad", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resetpassword(
			@RequestParam(name = "NameAndMobile", defaultValue = "") String NameAndMobile,
			@RequestParam(name = "Email", defaultValue = "") String Email, HttpServletRequest request) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//验证是否存在‘#’并且只有一个
		if (NameAndMobile.indexOf('#') > -1 && (NameAndMobile.indexOf('#') == NameAndMobile.lastIndexOf('#'))) {
			//取各段值
			String[] buff = NameAndMobile.split("#");
			queryMap.put("name", buff[0]);
			queryMap.put("mobile", buff[1]);
			List<Account> findList = accountService.findList(queryMap);
			if (Email.length() == 0) {
				if (findList.size() < 1) {
					queryMap.put("type", "error");
					queryMap.put("msg", "用户不存在，请重新填写！");
					return queryMap;
				}
			} else {
				//正则表达邮箱格式
				String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
				Pattern p = Pattern.compile(regEx1);
				Matcher m = p.matcher(Email);
				if (!m.matches()) {
					queryMap.put("type", "error");
					queryMap.put("msg", "邮箱输入格式有误，请重新填写！");
					return queryMap;
				}
			}

		} else {
			queryMap.put("type", "error");
			queryMap.put("msg", "输入格式有误，请重新填写！");
			return queryMap;
		}
		//随机产生一个6位混合密码
		if (Email.length() != 0) {
			String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 6; i++) {
				int number = random.nextInt(62);
				sb.append(str.charAt(number));
			}
			String[] buff = NameAndMobile.split("#");
			Account account = accountService.findByName(buff[0]);
			account.setPassword(MD5Util.md5Password(sb.toString()));
			if (accountService.edit(account) <= 0) {
				queryMap.put("type", "error");
				queryMap.put("msg", "修改失败，请联系管理员！");
				return queryMap;
			}
			//发送邮箱
			EmailUtil se = new EmailUtil();
			se.doSendHtmlEmail("酒店管理系统重置密码", "您的密码重置为：" + sb.toString() + "为账号安全尽快改密码。", Email);
			queryMap.put("type", "success");
			queryMap.put("msg", "重置成功，请通过邮箱查收！");
		}
		return queryMap;

	}

	/**
	 * 注册信息提交
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> regAct(Account account) {
		Map<String, String> retMap = new HashMap<String, String>();
		if (account == null) {
			retMap.put("type", "error");
			retMap.put("msg", "请填写正确的用户信息！");
			return retMap;
		}
		if (StringUtils.isEmpty(account.getName())) {
			retMap.put("type", "error");
			retMap.put("msg", "用户名不能为空！");
			return retMap;
		}


		if (StringUtils.isEmpty(account.getPassword())) {
			retMap.put("type", "error");
			retMap.put("msg", "密码不能为空！");
			return retMap;
		}
		if (StringUtils.isEmpty(account.getMobile())) {
			retMap.put("type", "error");
			retMap.put("msg", "手机号不能为空！");
			return retMap;
		}
		if (account.getMobile().length() != 13 && Long.parseLong(account.getMobile()) < 999999999) {
			retMap.put("type", "error");
			retMap.put("msg", "手机号格式错误，请输入13位数字字符");
			return retMap;
		}
		if (isExist(account.getName())) {
			retMap.put("type", "error");
			retMap.put("msg", "该用户名已经存在！");
			return retMap;
		}
		account.setRealName(" ");
		account.setIdCard(" ");
		account.setAddress(" ");
		if (accountService.add(account) <= 0) {
			retMap.put("type", "error");
			retMap.put("msg", "注册失败，请联系管理员！");
			return retMap;
		}
		retMap.put("type", "success");
		retMap.put("msg", "注册成功！");
		return retMap;
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("account", null);
		return "redirect:login2";
	}

	private boolean isExist(String name) {
		Account account = accountService.findByName(name);
		if (account == null)
			return false;
		return true;
	}
}
