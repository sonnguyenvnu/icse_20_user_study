package com.github.vole.mps.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.vole.common.utils.R;
import com.github.vole.mps.model.entity.MemberRole;
import com.github.vole.mps.service.MemberRoleService;
import com.github.vole.portal.common.controller.AbstractController;
import com.github.vole.mps.model.entity.Member;
import com.github.vole.mps.service.MemberService;
import com.github.vole.mps.service.RoleService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/mps/member")
public class MemberController extends AbstractController<MemberService,Member> {
    private static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    @Resource
//    private FastDFSClient fastDFSClient;
    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberRoleService memberRoleService;
//    @Autowired
//    private FdfsPropertiesConfig fdfsPropertiesConfig;

    /**
     * 执行新增
     */
    @RequestMapping("/doAddAction")
    @ResponseBody
    public R<Boolean> doAdd(Member member, @RequestParam(value="roleId[]",required=false) String[] roleIds){
        memberService.insertMember(member,roleIds);
        return R.rest(true);
    }


    /**
     * 执行新增
     */
    @RequestMapping("/doEditAction")
    @ResponseBody
    public R<Boolean> doEdit(@RequestParam(value="id",required=false)String id,Member member, @RequestParam(value="roleId[]",required=false) String[] roleIds){
        Long memberId = Long.valueOf(id.replace(",",""));
        member.setMemberId(memberId);
        memberService.updateMember(member,roleIds);
        return R.rest(true);
    }


    /**
     * 删除
     */
    @Override
    public R<Boolean> delete(String id) {
        String input = id.replace(",","");
        return new R<>(baseService.removeById(Long.valueOf(input)));
    }

    /**
     * 批�?删除角色
     */
    @Override
    public R<Boolean> deleteBatch(@RequestParam("id[]") List<String> ids){
        return R.rest(baseService.removeByIds(ids));
    }



//    /**
//     * 上传用户头�?
//     * (多机部署有问题，建议使用独立的文件�?务器)
//     *
//     * @param file 资�?
//     * @return filename map
//     */
//    @PostMapping("/upload")
//    public R<String> upload(@RequestParam("file") MultipartFile file) {
//        String fileExt = FilenameUtils.getName(file.getOriginalFilename());
//        Map<String, String> resultMap = new HashMap<>(1);
//        try {
//            String path = fastDFSClient.uploadFile(file.getBytes(), fileExt);
//            R<String> result = new R<String>(path);
//            return result;
//            //resultMap.put("filename", fdfsPropertiesConfig.getFileHost() + storePath.getFullPath());
//        } catch (IOException e) {
//            logger.error("文件上传异常", e);
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected String getTemplatePath() {
        return "ftl/mps/member/";
    }

    @Override
    protected QueryWrapper<Member> getSearchQW(String search, Model model) {
        QueryWrapper<Member> qw = new QueryWrapper<Member>();
        if(StringUtils.isNotBlank(search)){
            qw.like("membername",search);
            model.addAttribute("search",search);
        }
        return qw;
    }


    /**
     * 验�?会员�??是�?�已存在
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public R<Boolean> checkName(String membername){
        List<Member> list = memberService.list(new QueryWrapper<Member>().eq("membername", membername));
        R r = new R<Boolean>(true);
        if(list.size() > 0){
            r.setData(false);
            r.setCode(R.FAIL);
            r.setMsg("会员已存在");
        }
        return r;
    }


    /**
     * 验�?会员�??是�?�已存在
     */
    @RequestMapping("/checkPhone")
    @ResponseBody
    public R<Boolean> checkPhone(String phone){
        List<Member> list = memberService.list(new QueryWrapper<Member>().eq("phone", phone));
        R r = new R<Boolean>(true);
        if(list.size() > 0){
            r.setData(false);
            r.setCode(R.FAIL);
            r.setMsg("电�?已存在");
        }
        return r;
    }

    @Override
    protected void addModelData(Model model) {
        model.addAttribute("roleList", roleService.list(null));
        Object T = model.asMap().get("entity");
        if(T !=null){
            Member m  = (Member)T;
            QueryWrapper<MemberRole> ew = new QueryWrapper<MemberRole>();
            ew.eq("member_id ", m.getMemberId());
            List<MemberRole> myRoles = memberRoleService.list(ew);
            List<String> myRolds = Lists.transform(myRoles, input -> input.getRoleId().toString());
            model.addAttribute("myRolds",myRolds);
        }
    }
}
