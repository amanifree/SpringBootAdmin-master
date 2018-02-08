/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.geekcattle.model.console.Admin;
import com.geekcattle.model.console.Adjust;
import com.geekcattle.model.console.Role;
import com.geekcattle.service.console.AdminRoleService;
import com.geekcattle.service.console.AdjustService;
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
@RequestMapping("/console/adjust")
public class AdjustController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdjustService adjustService;

    @RequiresPermissions("adjust:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/adjust/index";
    }

    @RequiresPermissions("adjust:edit")
    @RequestMapping(value = "/form", method = {RequestMethod.GET})
    public String from(Adjust adjust, Model model) {
        String checkRoleId = "";
        if (!StringUtils.isEmpty(adjust.getUid())) {
            adjust = adjustService.getById(adjust.getUid());
            if (!"null".equals(adjust)) {
                adjust.setUpdatedat(DateUtil.getCurrentTime());
            }
        }else {
        }
        model.addAttribute("adjust", adjust);
        return "/console/adjust/form";
    }

/*    private List<Adjust> getAdjustList() {
        ModelMap map = new ModelMap();
        List<Adjust> AdjustList = adjustService.getFromAll();
        return AdjustList;
    }*/

    @RequiresPermissions("adjust:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Adjust adjust) {
        ModelMap map = new ModelMap();
        List<Adjust> Lists = adjustService.getPageList(adjust);
        map.put("pageInfo", new PageInfo<Adjust>(Lists));
        map.put("queryParam", adjust);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("adjust:index")
    @RequestMapping(value = "/listrole", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap listRole(Adjust adjust) {
        HashMap<String,Object> params = new HashMap<>();
        ModelMap map = new ModelMap();
        List<Integer> list = new ArrayList<>();
        Admin admin = getUserInfo();
        List<Role> rolelist = roleService.selectRoleListByAdminId(admin.getUid());

        for (Role role : rolelist) {
            if (role.getRoleName().equals("调货制单")){
                list.add(1);
            }
            if (role.getRoleName().equals("调货接货")){
                list.add(1);
            }
            if (role.getRoleName().equals("调货数据")){
                list.add(2);
            }
            if (role.getRoleName().equals("调货验货")){
                list.add(3);
            }
            if (role.getRoleName().equals("调货配送")){
                list.add(4);
                list.add(5);
                //list.add(5);
            }
        }

        params.put("list",list);
        params.put("adjust",adjust);
        List<Adjust> Lists = adjustService.getPageListByRole(params);
        map.put("pageInfo", new PageInfo<Adjust>(Lists));
        map.put("queryParam", adjust);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @Transactional
    @RequiresPermissions("adjust:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody

    public ModelMap save(@Valid Adjust adjust, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }
        try {
            if (StringUtils.isEmpty(adjust.getUid())) {
                adjust.setUid(UuidUtil.getUUID());
                adjust.setCreatedat(DateUtil.getCurrentTime());
                adjust.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(adjust);
                adjust.setState(1);
                adjustService.insert(adjust);
            } else {
                adjust.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(adjust);
                adjust.setState(adjust.getState()+1);
                adjustService.save(adjust);
            }
            return ReturnUtil.Success("操作成功", null, "/console/adjust/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }


/*    public ModelMap save(@Valid Adjust Adjust, BindingResult result) {
        try {
            if (result.hasErrors()) {
                for (ObjectError er : result.getAllErrors())
                    return ReturnUtil.Error(er.getDefaultMessage(), null, null);
            }
            if (StringUtils.isEmpty(Adjust.getUid())) {
                Example example = new Example(Admin.class);
                example.createCriteria().andCondition("username = ", Adjust.getUsername());
                Integer userCount = adjustService.getCount(example);
                if (userCount > 0) {
                    return ReturnUtil.Error("用户名已存在", null, null);
                }
                if (StringUtils.isEmpty(Adjust.getPassword())) {
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

/*    @RequiresPermissions("Adjust:editpwd")
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

    @RequiresPermissions("adjust:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if (ids != null) {
                if (StringUtils.isNotBlank(ids.toString())) {
                    for (String id : ids) {
                        //adminRoleService.deleteAdminId(id);
                        adjustService.deleteById(id);
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

    public Adjust timeEmptyToNull(Adjust adjust){
        if(adjust.getShipmentTime().isEmpty()){
            adjust.setShipmentTime(null);
        };
        if(adjust.getAdjustBegin().isEmpty()){
            adjust.setAdjustBegin(null);
        };
        if(adjust.getAdjustEnd().isEmpty()){
            adjust.setAdjustEnd(null);
        };
        if(adjust.getReceiptBegin().isEmpty()){
            adjust.setReceiptBegin(null);
        };
        if(adjust.getReceiptEnd().isEmpty()){
            adjust.setReceiptEnd(null);
        };
        if(adjust.getPurchaDataBegin().isEmpty()){
            adjust.setPurchaDataBegin(null);
        };
        if(adjust.getPurchaDataEnd().isEmpty()){
            adjust.setPurchaDataEnd(null);
        };
        if(adjust.getCheckBegin().isEmpty()){
            adjust.setCheckBegin(null);
        };
        if(adjust.getCheckEnd().isEmpty()){
            adjust.setCheckEnd(null);
        };
        if(adjust.getDistribBegin().isEmpty()){
            adjust.setDistribBegin(null);
        };
        if(adjust.getDistribEnd().isEmpty()){
            adjust.setDistribEnd(null);
        };
        if(adjust.getSendoutTime().isEmpty()){
            adjust.setSendoutTime(null);
        };

        return adjust;
    };

}
