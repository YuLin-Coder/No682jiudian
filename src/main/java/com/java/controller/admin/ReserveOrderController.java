package com.java.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.po.ReserveOrder;
import com.java.po.RoomType;
import com.java.service.AccountService;
import com.java.service.ReserveOrderService;
import com.java.service.RoomTypeService;
import com.java.util.Page;

/**
 * 预定订单管理后台控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping("/admin/book_order")
@Controller
public class ReserveOrderController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private ReserveOrderService reserveOrderService;

	/**
	 * 预定订单管理列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.addObject("roomTypeList", roomTypeService.findAll());
		model.addObject("accountList", accountService.findAll());
		model.setViewName("book_order/list");
		return model;
	}

	/**
	 * 预定订单信息添加操作
	 * 
	 * @param reserveOrder
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(ReserveOrder reserveOrder) {
		Map<String, String> ret = new HashMap<String, String>();
		if (reserveOrder == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预定订单信息!");
			return ret;
		}
		if (reserveOrder.getAccountId() == null) {
			ret.put("type", "error");
			ret.put("msg", "客户不能为空!");
			return ret;
		}
		if (reserveOrder.getRoomTypeId() == null) {
			ret.put("type", "error");
			ret.put("msg", "房型不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getName())) {
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人名称不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getMobile())) {
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人手机号不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getIdCard())) {
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getArriveDate())) {
			ret.put("type", "error");
			ret.put("msg", "到达时间不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getLeaveDate())) {
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空!");
			return ret;
		}
		reserveOrder.setCreateTime(new Date());
		if (reserveOrderService.add(reserveOrder) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		RoomType roomType = roomTypeService.find(reserveOrder.getRoomTypeId());
		// 预定成功后去修改该房型的预定数
		if (roomType != null) {
			roomType.setBookNum(roomType.getBookNum() + 1);
			roomType.setAvilableNum(roomType.getAvilableNum() - 1);
			roomTypeService.updateNum(roomType);
			// 如果可用的房间数为0，则设置该房型状态已满
			if (roomType.getAvilableNum() == 0) {
				roomType.setStatus(0);
				roomTypeService.edit(roomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}

	/**
	 * 预定订单信息编辑操作
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(ReserveOrder reserveOrder) {
		Map<String, String> ret = new HashMap<String, String>();
		if (reserveOrder == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的预定订单信息!");
			return ret;
		}
		if (reserveOrder.getAccountId() == null) {
			ret.put("type", "error");
			ret.put("msg", "客户不能为空!");
			return ret;
		}
		if (reserveOrder.getRoomTypeId() == null) {
			ret.put("type", "error");
			ret.put("msg", "房型不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getName())) {
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人名称不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getMobile())) {
			ret.put("type", "error");
			ret.put("msg", "预定订单联系人手机号不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getIdCard())) {
			ret.put("type", "error");
			ret.put("msg", "联系人身份证号不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getArriveDate())) {
			ret.put("type", "error");
			ret.put("msg", "到达时间不能为空!");
			return ret;
		}
		if (StringUtils.isEmpty(reserveOrder.getLeaveDate())) {
			ret.put("type", "error");
			ret.put("msg", "离店时间不能为空!");
			return ret;
		}
		ReserveOrder existReserveOrder = reserveOrderService.find(reserveOrder.getId());
		if (existReserveOrder == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择正确的数据进行编辑!");
			return ret;
		}
		if (reserveOrderService.edit(reserveOrder) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "编辑失败，请联系管理员!");
			return ret;
		}
		// 判断房型是否发生变化
		if (existReserveOrder.getRoomTypeId().longValue() != reserveOrder.getRoomTypeId().longValue()) {
			// 房型发生了变化
			// 首先恢复原来房型的预定数及可用数
			RoomType oldRoomType = roomTypeService.find(existReserveOrder.getRoomTypeId());
			oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
			oldRoomType.setBookNum(oldRoomType.getBookNum() - 1);
			roomTypeService.updateNum(oldRoomType);
			if (oldRoomType.getStatus() == 0) {
				// 旧的房间原来是满房，现在不满房的话，恢复状态
				if (oldRoomType.getAvilableNum() > 0) {
					// 设置成状态可用
					oldRoomType.setStatus(1);
					roomTypeService.edit(oldRoomType);
				}
			}
			// 修改新的房型的可用数和预定数
			RoomType newRoomType = roomTypeService.find(reserveOrder.getRoomTypeId());
			newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
			newRoomType.setBookNum(newRoomType.getBookNum() + 1);
			roomTypeService.updateNum(newRoomType);
			if (newRoomType.getAvilableNum() <= 0) {
				// 没有可用房间数
				newRoomType.setStatus(0);// 设置成满房
				roomTypeService.edit(newRoomType);
			}
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功!");
		return ret;
	}

	/**
	 * 分页查询预定订单信息
	 * 
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "accountId", defaultValue = "") Long accountId,
			@RequestParam(name = "roomTypeId", defaultValue = "") Long roomTypeId,
			@RequestParam(name = "idCard", defaultValue = "") String idCard,
			@RequestParam(name = "mobile", defaultValue = "") String mobile,
			@RequestParam(name = "status", required = false) Integer status, Page page) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("status", status);
		queryMap.put("accountId", accountId);
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("idCard", idCard);
		queryMap.put("mobile", mobile);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", reserveOrderService.findList(queryMap));
		ret.put("total", reserveOrderService.getTotal(queryMap));
		return ret;
	}


	/**
	 * 客户信息删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updatestatus")
	@ResponseBody
	public void updatestatus(Long id) {
		reserveOrderService.updatestatus(id);
	}

}
