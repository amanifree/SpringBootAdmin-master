/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.geekcattle.model.console.Admin;
import com.geekcattle.model.console.Distrib;
import com.geekcattle.model.console.Role;
import com.geekcattle.service.console.AdminRoleService;
import com.geekcattle.service.console.DistribService;
import com.geekcattle.service.console.RoleService;
import com.geekcattle.util.DateUtil;
import com.geekcattle.util.ReturnUtil;
import com.geekcattle.util.UuidUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.geekcattle.core.shiro.AdminShiroUtil.getUserInfo;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/distrib")
public class DistribController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DistribService distribService;

    @RequiresPermissions("distrib:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/distrib/index";
    }

    @RequiresPermissions("distrib:edit")
    @RequestMapping(value = "/form", method = {RequestMethod.GET})
    public String from(Distrib distrib, Model model) {
        String checkRoleId = "";
        if (!StringUtils.isEmpty(distrib.getUid())) {
            distrib = distribService.getById(distrib.getUid());
            if (!"null".equals(distrib)) {
                distrib.setUpdatedat(DateUtil.getCurrentTime());
            }
        }else {
        }
        model.addAttribute("distrib", distrib);
        return "/console/distrib/form";
    }


    @RequiresPermissions("distrib:edit")
    @RequestMapping(value = "/formlayer", method = {RequestMethod.GET})
    public String fromlayer(Distrib distrib, Model model) {
        String checkRoleId = "";
        if (!StringUtils.isEmpty(distrib.getUid())) {
            distrib = distribService.getById(distrib.getUid());
            if (!"null".equals(distrib)) {
                distrib.setUpdatedat(DateUtil.getCurrentTime());
            }
        }else {
        }
        model.addAttribute("distrib", distrib);
        return "/console/distrib/formlayer";
    }

/*    private List<Distrib> getDistribList() {
        ModelMap map = new ModelMap();
        List<Distrib> DistribList = distribService.getFromAll();
        return DistribList;
    }*/

    @RequiresPermissions("distrib:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Distrib distrib) {
        ModelMap map = new ModelMap();
        List<Distrib> Lists = distribService.getPageList(distrib);
        map.put("pageInfo", new PageInfo<Distrib>(Lists));
        map.put("queryParam", distrib);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("distrib:index")
    @RequestMapping(value = "/listrole", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap listRole(Distrib distrib) {
        HashMap<String,Object> params = new HashMap<>();
        ModelMap map = new ModelMap();
        List<Integer> list = new ArrayList<>();
        Admin admin = getUserInfo();
        List<Role> rolelist = roleService.selectRoleListByAdminId(admin.getUid());

        for (Role role : rolelist) {
            if (role.getRoleName().equals("配送单据录入")){
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(10);
                list.add(11);
            }
            /*if (role.getRoleName().equals("数据部数据")){
                list.add(1);
            }
            if (role.getRoleName().equals("仓储配货复核")){
                list.add(2);
            }
            if (role.getRoleName().equals("配送物流数据")){
                list.add(3);
                list.add(4);
                //list.add(5);
            }*/
        }

        params.put("list",list);
        List<Distrib> Lists = distribService.getPageListByRole(params);
        map.put("pageInfo", new PageInfo<Distrib>(Lists));
        map.put("queryParam", distrib);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @Transactional
    @RequiresPermissions("distrib:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid Distrib distrib, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }
        try {
            if (StringUtils.isEmpty(distrib.getUid())) {
                distrib.setUid(UuidUtil.getUUID());
                distrib.setCreatedat(DateUtil.getCurrentTime());
                distrib.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(distrib);
                distrib.setState(1);
                distribService.insert(distrib);
            } else {
                distrib.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(distrib);
                distrib.setState(distrib.getState()+1);
                distribService.save(distrib);
            }
            return ReturnUtil.Success("操作成功", null, "/console/distrib/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }


/*    public ModelMap save(@Valid Distrib Distrib, BindingResult result) {
        try {
            if (result.hasErrors()) {
                for (ObjectError er : result.getAllErrors())
                    return ReturnUtil.Error(er.getDefaultMessage(), null, null);
            }
            if (StringUtils.isEmpty(Distrib.getUid())) {
                Example example = new Example(Admin.class);
                example.createCriteria().andCondition("username = ", Distrib.getUsername());
                Integer userCount = distribService.getCount(example);
                if (userCount > 0) {
                    return ReturnUtil.Error("用户名已存在", null, null);
                }
                if (StringUtils.isEmpty(Distrib.getPassword())) {
                    return ReturnUtil.Error("密码不能为空", null, null);
                }
                String Id = UuidUtil.getUUID();
                admin.setUid(Id);
                String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
                admin.setSalt(salt);
                String password = PasswordUtil.createAdminPwd(admin.getPassword(), admin.getCredentialsSalt());
                admin.setPassword(password);
                admin.setIsSystem(0);
                admin.setCreatedAt(DateUtil.getCurrentTime());
                admin.setUpdatedAt(DateUtil.getCurrentTime());
                adminService.insert(admin);
            } else {
                Admin updateAdmin = adminService.getById(admin.getUid());
                if (!"null".equals(updateAdmin)) {
                    admin.setSalt(updateAdmin.getSalt());
                    if (!StringUtils.isEmpty(admin.getPassword())) {
                        String password = PasswordUtil.createAdminPwd(admin.getPassword(), updateAdmin.getCredentialsSalt());
                        admin.setPassword(password);
                    } else {
                        admin.setPassword(updateAdmin.getPassword());
                    }
                    admin.setUpdatedAt(DateUtil.getCurrentTime());
                    adminService.save(admin);
                } else {
                    return ReturnUtil.Error("操作失败", null, null);
                }
            }
            if (admin.getRoleId() != null) {
                adminRoleService.deleteAdminId(admin.getUid());
                for (String roleid : admin.getRoleId()) {
                    AdminRole adminRole = new AdminRole();
                    adminRole.setAdminId(admin.getUid());
                    adminRole.setRoleId(roleid);
                    adminRoleService.insert(adminRole);
                }
            }else{
                adminRoleService.deleteAdminId(admin.getUid());
            }
            return ReturnUtil.Success("操作成功", null, "/console/admin/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }*/

/*    @RequiresPermissions("Distrib:editpwd")
    @RequestMapping(value = "/savepwd", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap editPwd(String uid, String password) {
        try {
            if (StringUtils.isNotEmpty(uid) && StringUtils.isNotEmpty(password)) {
                Admin admin = adminService.getById(uid);
                if (!"null".equals(admin)) {
                    String newPassword = PasswordUtil.createAdminPwd(password, admin.getSalt());
                    Admin pwdAdmin = new Admin();
                    pwdAdmin.setPassword(newPassword);
                    Example example = new Example(Admin.class);
                    example.createCriteria().andCondition("uid", uid);
                    adminService.updateExample(pwdAdmin, example);
                    return ReturnUtil.Success("操作成功", null, null);
                } else {
                    return ReturnUtil.Error("对像不存在，修改失败", null, null);
                }
            } else {
                return ReturnUtil.Error("参数错误，修改失败", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("修改失败", null, null);
        }
    }*/

    @RequiresPermissions("distrib:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if (ids != null) {
                if (StringUtils.isNotBlank(ids.toString())) {
                    for (String id : ids) {
                        //adminRoleService.deleteAdminId(id);
                        distribService.deleteById(id);
                    }
                }
                return ReturnUtil.Success("删除成功", null, null);
            } else {
                return ReturnUtil.Error("删除失败", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败", null, null);
        }
    }

    public Distrib timeEmptyToNull(Distrib distrib){
        if(!distrib.getShipmentTime().equals(null) && distrib.getShipmentTime().isEmpty()){
            distrib.setShipmentTime(null);
        };
        if(!distrib.getOrderBegin().equals(null) && distrib.getOrderBegin().isEmpty()){
            distrib.setOrderBegin(null);
        };
        if(!distrib.getOrderEnd().equals(null) && distrib.getOrderEnd().isEmpty()){
            distrib.setOrderEnd(null);
        };
        //if( "".equals(distrib.getPickBegin())){
        if(!distrib.getPickBegin().equals(null) && distrib.getPickBegin().isEmpty()){
            distrib.setPickBegin(null);
        };
        if(!distrib.getPickEnd().equals(null) && distrib.getPickEnd().isEmpty()){
            distrib.setPickEnd(null);
        };
        if(!distrib.getDataBegin().equals(null) && distrib.getDataBegin().isEmpty()){
            distrib.setDataBegin(null);
        };
        if(!distrib.getDataEnd().equals(null) && distrib.getDataEnd().isEmpty()){
            distrib.setDataEnd(null);
        };
        if(!distrib.getDistribCheckBegin().equals(null) && distrib.getDistribCheckBegin().isEmpty()){
            distrib.setDistribCheckBegin(null);
        };
        if(!distrib.getDistribCheckEnd().equals(null) && distrib.getDistribCheckEnd().isEmpty()){
            distrib.setDistribCheckEnd(null);
        };
        if(!distrib.getDistribPackBegin().equals(null) && distrib.getDistribPackBegin().isEmpty()){
            distrib.setDistribPackBegin(null);
        };
        if(!distrib.getDistribPackEnd().equals(null) && distrib.getDistribPackEnd().isEmpty()){
            distrib.setDistribPackEnd(null);
        };
        if(!distrib.getDistribBegin().equals(null) && distrib.getDistribBegin().isEmpty()){
            distrib.setDistribBegin(null);
        };
        if(!distrib.getDistribEnd().equals(null) && distrib.getDistribEnd().isEmpty()){
            distrib.setDistribEnd(null);
        };
        if(!distrib.getSendoutTime().equals(null) && distrib.getSendoutTime().isEmpty()){
            distrib.setSendoutTime(null);
        };
        if(!distrib.getArrivalTime().equals(null) && distrib.getArrivalTime().isEmpty()){
            distrib.setArrivalTime(null);
        };
        if(!distrib.getCreatedat().equals(null) && distrib.getCreatedat().isEmpty()){
            distrib.setCreatedat(null);
        };

        return distrib;
    };

}
