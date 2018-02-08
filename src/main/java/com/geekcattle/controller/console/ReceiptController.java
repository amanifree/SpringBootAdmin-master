/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.geekcattle.core.shiro.AdminShiroUtil;
import com.geekcattle.model.console.Receipt;
import com.geekcattle.model.console.Admin;
import com.geekcattle.model.console.AdminRole;
import com.geekcattle.model.console.Role;
import com.geekcattle.service.console.AdminRoleService;
import com.geekcattle.service.console.AdminService;
import com.geekcattle.service.console.ReceiptService;
import com.geekcattle.service.console.RoleService;
import com.geekcattle.util.DateUtil;
import com.geekcattle.util.PasswordUtil;
import com.geekcattle.util.ReturnUtil;
import com.geekcattle.util.UuidUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
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
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.geekcattle.core.shiro.AdminShiroUtil.getUserInfo;
import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/receipt")
public class ReceiptController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("receipt:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/receipt/index";
    }

    @RequiresPermissions("receipt:edit")
    @RequestMapping(value = "/form", method = {RequestMethod.GET})
    public String from(Receipt receipt, Model model) {
        String checkRoleId = "";
        if (!StringUtils.isEmpty(receipt.getUid())) {
            receipt = receiptService.getById(receipt.getUid());
            if (!"null".equals(receipt)) {
                receipt.setUpdatedat(DateUtil.getCurrentTime());
            }
        }else {
        }
        model.addAttribute("receipt", receipt);
        return "/console/receipt/form";
    }

/*    private List<Receipt> getReceiptList() {
        ModelMap map = new ModelMap();
        List<Receipt> receiptList = receiptService.getFromAll();
        return receiptList;
    }*/

    @RequiresPermissions("receipt:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Receipt receipt) {
        ModelMap map = new ModelMap();
        List<Receipt> Lists = receiptService.getPageList(receipt);
        map.put("pageInfo", new PageInfo<Receipt>(Lists));
        map.put("queryParam", receipt);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("receipt:index")
    @RequestMapping(value = "/listrole", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap listRole(Receipt receipt) {
        HashMap<String,Object> params = new HashMap<>();
        ModelMap map = new ModelMap();
        List<Integer> list = new ArrayList<>();
        Admin admin = getUserInfo();
        List<Role> rolelist = roleService.selectRoleListByAdminId(admin.getUid());

        for (Role role : rolelist) {
            if (role.getRoleName().equals("收货数据录入")){
                list.add(1);
            }
            if (role.getRoleName().equals("配送接货")){
                list.add(1);
            }
            if (role.getRoleName().equals("采购仓储验退")){
                list.add(2);
            }
            if (role.getRoleName().equals("采购仓储验核")){
                list.add(3);
            }
            if (role.getRoleName().equals("采购数据验退")){
                list.add(4);
            }
            if (role.getRoleName().equals("仓储上架")){
                list.add(5);
                list.add(6);
                //list.add(7);
            }
        }

        params.put("list",list);
        List<Receipt> Lists = receiptService.getPageListByRole(params);
        map.put("pageInfo", new PageInfo<Receipt>(Lists));
        map.put("queryParam", receipt);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @Transactional
    @RequiresPermissions("receipt:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody

    public ModelMap save(@Valid Receipt receipt, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }
        try {
            if (StringUtils.isEmpty(receipt.getUid())) {
                receipt.setUid(UuidUtil.getUUID());
                receipt.setCreatedat(DateUtil.getCurrentTime());
                receipt.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(receipt);
                receipt.setState(1);
                receiptService.insert(receipt);
            } else {
                receipt.setUpdatedat(DateUtil.getCurrentTime());
                timeEmptyToNull(receipt);
                receipt.setState(receipt.getState()+1);
                receiptService.save(receipt);
            }
            return ReturnUtil.Success("操作成功", null, "/console/receipt/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }


/*    public ModelMap save(@Valid Receipt receipt, BindingResult result) {
        try {
            if (result.hasErrors()) {
                for (ObjectError er : result.getAllErrors())
                    return ReturnUtil.Error(er.getDefaultMessage(), null, null);
            }
            if (StringUtils.isEmpty(receipt.getUid())) {
                Example example = new Example(Admin.class);
                example.createCriteria().andCondition("username = ", receipt.getUsername());
                Integer userCount = receiptService.getCount(example);
                if (userCount > 0) {
                    return ReturnUtil.Error("用户名已存在", null, null);
                }
                if (StringUtils.isEmpty(receipt.getPassword())) {
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

/*    @RequiresPermissions("receipt:editpwd")
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

    @RequiresPermissions("receipt:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if (ids != null) {
                if (StringUtils.isNotBlank(ids.toString())) {
                    for (String id : ids) {
                        //adminRoleService.deleteAdminId(id);
                        receiptService.deleteById(id);
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

    public Receipt timeEmptyToNull(Receipt receipt){
        if(receipt.getDeliveryTime().isEmpty()){
            receipt.setDeliveryTime(null);
        };
        if(receipt.getAccepBackBegin().isEmpty()){
            receipt.setAccepBackBegin(null);
        };
        if(receipt.getAccepBackEnd().isEmpty()){
            receipt.setAccepBackEnd(null);
        };
        if(receipt.getAccepCheckBegin().isEmpty()){
            receipt.setAccepCheckBegin(null);
        };
        if(receipt.getAccepCheckEnd().isEmpty()){
            receipt.setAccepCheckEnd(null);
        };
        if(receipt.getAccepReBegin().isEmpty()){
            receipt.setAccepReBegin(null);
        };
        if(receipt.getAccepReEnd().isEmpty()){
            receipt.setAccepReEnd(null);
        };
        if(receipt.getTallyBegin().isEmpty()){
            receipt.setTallyBegin(null);
        };
        if(receipt.getTallyEnd().isEmpty()){
            receipt.setTallyEnd(null);
        };
        if(receipt.getReceiptTime().isEmpty()){
            receipt.setReceiptTime(null);
        };

        return receipt;
    };

}
