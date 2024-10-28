package com.java.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.java.util.DaysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.po.ReserveOrder;
import com.java.po.RoomType;
import com.java.po.admin.Checkin;
import com.java.po.admin.Room;
import com.java.service.ReserveOrderService;
import com.java.service.RoomTypeService;
import com.java.service.admin.CheckinService;
import com.java.service.admin.RoomService;
import com.java.util.Page;

/**
 * 入住管理后台控制器
 *
 * @author Administrator
 */
@RequestMapping("/admin/checkin")
@Controller
public class CheckinController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private ReserveOrderService reserveOrderService;
    @Autowired
    private CheckinService checkinService;

    /**
     * 入住管理列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.addObject("roomTypeList", roomTypeService.findAll());
        model.addObject("roomList", roomService.findAll());
        model.setViewName("checkin/list");
        return model;
    }

    /**
     * 入住信息添加操作
     *
     * @param checkin
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Checkin checkin,
                                   @RequestParam(name = "reserveOrderId", required = false) Long reserveOrderId
    ) {
        //验证信息是否合法
        Map<String, String> ret = new HashMap<String, String>();
        if (checkin == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的入住信息!");
            return ret;
        }
        if (checkin.getRoomId() == null) {
            ret.put("type", "error");
            ret.put("msg", "房间不能为空!");
            return ret;
        }
        if (checkin.getRoomTypeId() == null) {
            ret.put("type", "error");
            ret.put("msg", "房型不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getName())) {
            ret.put("type", "error");
            ret.put("msg", "入住联系人名称不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getMobile())) {
            ret.put("type", "error");
            ret.put("msg", "入住联系人手机号不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getIdCard())) {
            ret.put("type", "error");
            ret.put("msg", "联系人身份证号不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getArriveDate())) {
            ret.put("type", "error");
            ret.put("msg", "到达时间不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getLeaveDate())) {
            ret.put("type", "error");
            ret.put("msg", "离店时间不能为空!");
            return ret;
        }
        checkin.setCreateTime(new Date());
        if (checkinService.add(checkin) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败，请联系管理员!");
            return ret;
        }
        //获取房间信息
        RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());

        if (reserveOrderId != null) {
            //从预定来的入住单(入住既可以是直接入住也可以是已经预定的人来入住)
            ReserveOrder reserveOrder = reserveOrderService.find(reserveOrderId);
            reserveOrder.setStatus(1);
            reserveOrderService.edit(reserveOrder);
            //roomType.setBookNum(roomType.getBookNum() - 1);//预定数减1
        } else {
            roomType.setAvilableNum(roomType.getAvilableNum() - 1);
        }
        //入住成功后去修改该房型的预定数
        if (roomType != null) {
            roomType.setLivedNum(roomType.getLivedNum() + 1);//入住数加1
            roomTypeService.updateNum(roomType);
            //如果可用的房间数为0，则设置该房型状态已满
            if (roomType.getAvilableNum() == 0) {
                roomType.setStatus(0);
                roomTypeService.edit(roomType);
            }
        }
        Room room = roomService.find(checkin.getRoomId());
        if (room != null) {
            //要把房间状态设置为已入住
            room.setStatus(1);
            roomService.edit(room);
        } else {
            ret.put("type", "error");
            ret.put("msg", "该房间已被占用!");
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 入住信息编辑操作
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Checkin checkin,
                                    @RequestParam(name = "reserveOrderId", required = false) Long reserveOrderId
    ) {
        Map<String, String> ret = new HashMap<String, String>();
        if (checkin == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的入住信息!");
            return ret;
        }
        if (checkin.getRoomId() == null) {
            ret.put("type", "error");
            ret.put("msg", "房间不能为空!");
            return ret;
        }
        if (checkin.getRoomTypeId() == null) {
            ret.put("type", "error");
            ret.put("msg", "房型不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getName())) {
            ret.put("type", "error");
            ret.put("msg", "入住联系人名称不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getMobile())) {
            ret.put("type", "error");
            ret.put("msg", "入住联系人手机号不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getIdCard())) {
            ret.put("type", "error");
            ret.put("msg", "联系人身份证号不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getArriveDate())) {
            ret.put("type", "error");
            ret.put("msg", "到达时间不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(checkin.getLeaveDate())) {
            ret.put("type", "error");
            ret.put("msg", "离店时间不能为空!");
            return ret;
        }
        Checkin existCheckin = checkinService.find(checkin.getId());
        if (existCheckin == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择正确的入住信息进行编辑!");
            return ret;
        }
        if (checkinService.edit(checkin) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "编辑失败，请联系管理员!");
            return ret;
        }
        //编辑成功之后：1：判断房型是否发生变化，2：判断房间是否发生变化，3：判断是否是从预定订单来的信息
        //首先判断是否是从预定来的入住信息
        RoomType oldRoomType = roomTypeService.find(existCheckin.getRoomTypeId());
        RoomType newRoomType = roomTypeService.find(checkin.getRoomTypeId());

        //房型入住数不收预定订单的影响
        if (oldRoomType.getId().longValue() != newRoomType.getId().longValue()) {
            //说明房型发生了变化，原来的房型入住数恢复，新的房型入住数增加
            oldRoomType.setLivedNum(oldRoomType.getLivedNum() - 1);
            newRoomType.setLivedNum(newRoomType.getLivedNum() + 1);
            if (reserveOrderId == null) {
                oldRoomType.setAvilableNum(oldRoomType.getAvilableNum() + 1);
                newRoomType.setAvilableNum(newRoomType.getAvilableNum() - 1);
            }
        }
        roomTypeService.updateNum(newRoomType);
        roomTypeService.updateNum(oldRoomType);
        //判断房间是否发生变化
        if (checkin.getRoomId().longValue() != existCheckin.getRoomId().longValue()) {
            //表示房间发生了变化
            Room oldRoom = roomService.find(existCheckin.getRoomId());
            Room newRoom = roomService.find(checkin.getRoomId());
            oldRoom.setStatus(0);//原来的房间可入住
            newRoom.setStatus(1);//现在的房间已入住
            roomService.edit(newRoom);
            roomService.edit(oldRoom);
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 分页查询入住信息
     *
     * @param name
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "roomId", defaultValue = "") Long roomId,
            @RequestParam(name = "roomTypeId", defaultValue = "") Long roomTypeId,
            @RequestParam(name = "idCard", defaultValue = "") String idCard,
            @RequestParam(name = "mobile", defaultValue = "") String mobile,
            @RequestParam(name = "status", required = false) Integer status,
            Page page
    ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", name);
        queryMap.put("status", status);
        queryMap.put("roomId", roomId);
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("idCard", idCard);
        queryMap.put("mobile", mobile);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", checkinService.findList(queryMap));
        ret.put("total", checkinService.getTotal(queryMap));
        return ret;
    }

    /**
     * 退房操作
     *
     * @param checkId
     * @return
     */
    @Transactional
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> checkout(Long checkId
    ) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        if (checkId == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择数据!");
            return ret;
        }
        Checkin checkin = checkinService.find(checkId);
        if (checkin == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择正确的数据!");
            return ret;
        }
        checkin.setStatus(1);
        if (checkinService.edit(checkin) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "退房失败，请联系管理员!");
            return ret;
        }
        //首先操作房间状态
        Room room = roomService.find(checkin.getRoomId());
        if (room != null) {
            room.setStatus(2);
            roomService.edit(room);
        }
        //其次修改房型可用数、入住数、状态
        RoomType roomType = roomTypeService.find(checkin.getRoomTypeId());
        if (roomType != null) {
            roomType.setAvilableNum(roomType.getAvilableNum() + 1);
            if (roomType.getAvilableNum() > roomType.getRoomNum()) {
                roomType.setAvilableNum(roomType.getRoomNum());
            }
            roomType.setLivedNum(roomType.getLivedNum() - 1);
            if (roomType.getStatus() == 0) {
                roomType.setStatus(1);
            }
            if (checkin.getReserveOrderId() != null) {
                roomType.setBookNum(roomType.getBookNum() - 1);
            }
            roomTypeService.updateNum(roomType);
            roomTypeService.edit(roomType);
        }
        //判断是否来自预定
        if (checkin.getBookOrderId() != null) {
            ReserveOrder reserveOrder = reserveOrderService.find(checkin.getBookOrderId());
            reserveOrder.setStatus(2);
            reserveOrderService.edit(reserveOrder);
        }
        Date before = null;
        Date after = null;
        if (null != checkin.getArriveDate()) {
            before = DateUtil.parse(checkin.getArriveDate(), "yyyy-MM-dd");
        }
        if (null != checkin.getLeaveDate()) {
            after = DateUtil.parse(checkin.getLeaveDate(), "yyyy-MM-dd");
        }
        Date date = new Date();
        if (null != before && null != after) {
            if (!date.before(before)) {
                if (date.before(after)) {
                    Long res = DaysUtil.Calculatedays(before, date, checkin.getCheckinPrice());
                    ret.put("type", "success");
                    ret.put("msg", "退房成功!共应支付" + res + "元!");
                    return ret;
                } else if (date.equals(after)) {
                    Long res = DaysUtil.Calculatedays(before, after, checkin.getCheckinPrice());
                    ret.put("type", "success");
                    ret.put("msg", "退房成功!共应支付" + res + "元!");
                    return ret;
                } else {
                    Long res = DaysUtil.Calculatedays(before, date, checkin.getCheckinPrice());
                    ret.put("type", "success");
                    ret.put("msg", "退房成功!共应支付" + res + "元!");
                    return ret;
                }
            }else {
                ret.put("type", "success");
                ret.put("msg", "退房成功!共应支付0元!");
                return ret;

            }

        } else {
            ret.put("type", "error");
            ret.put("msg", "进店时间未填写!");
            return ret;
        }
    }

    /**
     * 根据房间类型获取房间
     *
     * @param roomTypeId
     * @return
     */
    @RequestMapping(value = "/load_room_list", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> load_room_list(Long roomTypeId) {
        List<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("status", 0);
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 999);
        List<Room> roomList = roomService.findList(queryMap);
        for (Room room : roomList) {
            Map<String, Object> option = new HashMap<String, Object>();
            option.put("value", room.getId());
            option.put("text", room.getSn());
            retList.add(option);
        }
        return retList;
    }
}
