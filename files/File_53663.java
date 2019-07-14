package cn.exrick.controller;

import cn.exrick.bean.Pay;
import cn.exrick.bean.dto.DataTablesResult;
import cn.exrick.bean.dto.Result;
import cn.exrick.common.utils.*;
import cn.exrick.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Exrickx
 */
@Controller
@Api(tags = "开放接�?�",description = "支付�??赠管�?�")
public class PayController {

    private static final Logger log= LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayService payService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private EmailUtils emailUtils;

    @Value("${ip.expire}")
    private Long IP_EXPIRE;

    @Value("${my.token}")
    private String MY_TOKEN;

    @Value("${email.sender}")
    private String EMAIL_SENDER;

    @Value("${email.receiver}")
    private String EMAIL_RECEIVER;

    @Value("${token.admin.expire}")
    private Long ADMIN_EXPIRE;

    @Value("${token.fake.expire}")
    private Long FAKE_EXPIRE;

    @Value("${fake.pre}")
    private String FAKE_PRE;

    @Value("${server.url}")
    private String SERVER_URL;

    @RequestMapping(value = "/thanks/list",method = RequestMethod.GET)
    @ApiOperation(value = "获�?��??赠列表")
    @ResponseBody
    public DataTablesResult getThanksList(){

        DataTablesResult result=new DataTablesResult();
        List<Pay> list=new ArrayList<>();
        try {
            list=payService.getPayList(1);

        }catch (Exception e){
            result.setSuccess(false);
            result.setError("获�?��??赠列表失败");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/pay/list",method = RequestMethod.GET)
    @ApiOperation(value = "获�?�未支付数�?�")
    @ResponseBody
    public DataTablesResult getPayList(){

        DataTablesResult result=new DataTablesResult();
        List<Pay> list=new ArrayList<>();
        try {
            list=payService.getNotPayList();
        }catch (Exception e){
            result.setSuccess(false);
            result.setError("获�?�未支付数�?�失败");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/pay/check/list",method = RequestMethod.GET)
    @ApiOperation(value = "获�?�支付审核列表")
    @ResponseBody
    public DataTablesResult getCheckList(){

        DataTablesResult result=new DataTablesResult();
        List<Pay> list=new ArrayList<>();
        try {
            list=payService.getPayList(0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setError("获�?�支付审核列表失败");
            return result;
        }
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/pay/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "获�?�支付数�?�")
    @ResponseBody
    public Result<Object> getPayList(@PathVariable String id,
                                     @RequestParam(required = true) String token){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            return new ResultUtil<Object>().setErrorMsg("无效的Token或链接");
        }
        Pay pay=null;
        try {
            pay=payService.getPay(getPayId(id));
        }catch (Exception e){
            return new ResultUtil<Object>().setErrorMsg("获�?�支付数�?�失败");
        }
        return new ResultUtil<Object>().setData(pay);
    }

    @RequestMapping(value = "/pay/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加支付订�?�")
    @ResponseBody
    public Result<Object> addPay(@ModelAttribute Pay pay, HttpServletRequest request){

        if(StringUtils.isBlank(pay.getNickName())||StringUtils.isBlank(String.valueOf(pay.getMoney()))
                ||StringUtils.isBlank(pay.getEmail())||!EmailUtils.checkEmail(pay.getEmail())){
            return new ResultUtil<Object>().setErrorMsg("请填写完整信�?�和正确的通知邮箱");
        }
        //防炸库验�?
        String ip= IpInfoUtils.getIpAddr(request);
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip="127.0.0.1";
        }
        String temp=redisUtils.get(ip);
        if(StringUtils.isNotBlank(temp)){
            return new ResultUtil<Object>().setErrorMsg("您�??交的太频�?啦，作者的学生�?务器�?炸啦�?请2分钟�?��?试");
        }
        try {
            payService.addPay(pay);
            pay.setTime(StringUtils.getTimeStamp(new Date()));
        }catch (Exception e){
            return new ResultUtil<Object>().setErrorMsg("添加�??赠支付订�?�失败");
        }
        //记录缓存
        redisUtils.set(ip,"added",IP_EXPIRE, TimeUnit.MINUTES);

        //给管�?�员�?��?审核邮件
        String tokenAdmin= UUID.randomUUID().toString();
        redisUtils.set(pay.getId(),tokenAdmin,ADMIN_EXPIRE,TimeUnit.DAYS);
        pay=getAdminUrl(pay,pay.getId(),tokenAdmin,MY_TOKEN);
        emailUtils.sendTemplateMail(EMAIL_SENDER,EMAIL_RECEIVER,"�?XPay个人收款支付系统】待审核处�?�","email-admin",pay);

        //给�?�管�?�员�?��?审核邮件
        if(StringUtils.isNotBlank(pay.getTestEmail())&&EmailUtils.checkEmail(pay.getTestEmail())){
            Pay pay2=payService.getPay(pay.getId());
            String tokenFake=UUID.randomUUID().toString();
            redisUtils.set(FAKE_PRE+pay.getId(),tokenFake,FAKE_EXPIRE,TimeUnit.HOURS);
            pay2=getAdminUrl(pay2,FAKE_PRE+pay.getId(),tokenFake,MY_TOKEN);
            emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getTestEmail(),"�?XPay个人收款支付系统】待审核处�?�","email-fake",pay2);
        }
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/pay/edit",method = RequestMethod.POST)
    @ApiOperation(value = "编辑支付订�?�")
    @ResponseBody
    public Result<Object> editPay(@ModelAttribute Pay pay,
                                  @RequestParam(required = true) String id,
                                  @RequestParam(required = true) String token){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            return new ResultUtil<Object>().setErrorMsg("无效的Token或链接");
        }
        try {
            pay.setId(getPayId(pay.getId()));
            Pay p=payService.getPay(getPayId(pay.getId()));
            pay.setState(p.getState());
            if(!pay.getId().contains(FAKE_PRE)){
                pay.setCreateTime(StringUtils.getDate(pay.getTime()));
            }else{
                //�?�管�?�
                pay.setMoney(p.getMoney());
                pay.setPayType(p.getPayType());
            }
            payService.updatePay(pay);
        }catch (Exception e){
            return new ResultUtil<Object>().setErrorMsg("编辑支付订�?�失败");
        }
        if(id.contains(FAKE_PRE)){
            redisUtils.set(id,"",1L,TimeUnit.SECONDS);
        }
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/pay/pass",method = RequestMethod.GET)
    @ApiOperation(value = "审核通过支付订�?�")
    public String addPay(@RequestParam(required = true) String id,
                         @RequestParam(required = true) String token,
                         @RequestParam(required = true) String myToken,
                         Model model){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            model.addAttribute("errorMsg","无效的Token或链接");
            return "/500";
        }
        if(!myToken.equals(MY_TOKEN)){
            model.addAttribute("errorMsg","您未通过二次验�?，当我傻�?�");
            return "/500";
        }
        try {
            payService.changePayState(getPayId(id),1);
            //通知回调
            Pay pay=payService.getPay(getPayId(id));
            if(StringUtils.isNotBlank(pay.getEmail())&&EmailUtils.checkEmail(pay.getEmail())){
                emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"�?XPay个人收款支付系统】支付�?功通知","pay-success",pay);
            }
        }catch (Exception e){
            model.addAttribute("errorMsg","处�?�数�?�出错");
            return "/500";
        }
        return "redirect:/success";
    }

    @RequestMapping(value = "/pay/passNotShow",method = RequestMethod.GET)
    @ApiOperation(value = "审核通过但�?显示加入�??赠表")
    public String passNotShowPay(@RequestParam(required = true) String id,
                                 @RequestParam(required = true) String token,
                                 Model model){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            model.addAttribute("errorMsg","无效的Token或链接");
            return "/500";
        }
        try {
            payService.changePayState(getPayId(id),3);
            //通知回调
            Pay pay=payService.getPay(getPayId(id));
            if(StringUtils.isNotBlank(pay.getEmail())&&EmailUtils.checkEmail(pay.getEmail())){
                emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"�?XPay个人收款支付系统】支付�?功通知","pay-notshow",pay);
            }
        }catch (Exception e){
            model.addAttribute("errorMsg","处�?�数�?�出错");
            return "/500";
        }
        if(id.contains(FAKE_PRE)){
            redisUtils.set(id,"",1L,TimeUnit.SECONDS);
        }
        return "redirect:/success";
    }


    @RequestMapping(value = "/pay/back",method = RequestMethod.GET)
    @ApiOperation(value = "审核驳回支付订�?�")
    public String backPay(@RequestParam(required = true) String id,
                          @RequestParam(required = true) String token,
                          @RequestParam(required = true) String myToken,
                          Model model){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            model.addAttribute("errorMsg","无效的Token或链接");
            return "/500";
        }
        if(!myToken.equals(MY_TOKEN)){
            model.addAttribute("errorMsg","您未通过二次验�?，当我傻�?�");
            return "/500";
        }
        try {
            payService.changePayState(getPayId(id),2);
            //通知回调
            Pay pay=payService.getPay(getPayId(id));
            if(StringUtils.isNotBlank(pay.getEmail())&&EmailUtils.checkEmail(pay.getEmail())){
                emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"�?XPay个人收款支付系统】支付失败通知","pay-fail",pay);
            }
        }catch (Exception e){
            model.addAttribute("errorMsg","处�?�数�?�出错");
            return "/500";
        }
        if(id.contains(FAKE_PRE)){
            redisUtils.set(id,"",1L,TimeUnit.SECONDS);
        }
        return "redirect:/success";
    }

    @RequestMapping(value = "/pay/del",method = RequestMethod.GET)
    @ApiOperation(value = "删除支付订�?�")
    @ResponseBody
    public Result<Object> delPay(@RequestParam(required = true) String id,
                         @RequestParam(required = true) String token){

        String temp=redisUtils.get(id);
        if(!token.equals(temp)){
            return new ResultUtil<Object>().setErrorMsg("无效的Token或链接");
        }
        try {
            //通知回调
            Pay pay=payService.getPay(getPayId(id));
            if(StringUtils.isNotBlank(pay.getEmail())&&EmailUtils.checkEmail(pay.getEmail())){
                emailUtils.sendTemplateMail(EMAIL_SENDER,pay.getEmail(),"�?XPay个人收款支付系统】支付失败通知","pay-fail",pay);
            }
            payService.delPay(getPayId(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResultUtil<Object>().setErrorMsg("删除支付订�?�失败");
        }
        if(id.contains(FAKE_PRE)){
            redisUtils.set(id,"",1L,TimeUnit.SECONDS);
        }
        return new ResultUtil<Object>().setData(null);
    }

    /**
     * 拼接管�?�员链接
     */
    public Pay getAdminUrl(Pay pay,String id,String token,String myToken){

        String pass=SERVER_URL+"/pay/pass?id="+id+"&token="+token+"&myToken="+myToken;
        pay.setPassUrl(pass);

        String back=SERVER_URL+"/pay/back?id="+id+"&token="+token+"&myToken="+myToken;
        pay.setBackUrl(back);

        String passNotShow=SERVER_URL+"/pay/passNotShow?id="+id+"&token="+token;
        pay.setPassNotShowUrl(passNotShow);

        String edit=SERVER_URL+"/pay-edit?id="+id+"&token="+token;
        pay.setEditUrl(edit);

        String del=SERVER_URL+"/pay-del?id="+id+"&token="+token;
        pay.setDelUrl(del);
        return pay;
    }

    /**
     * 获得�?�管�?�ID
     * @param id
     * @return
     */
    public String getPayId(String id){
        if(id.contains(FAKE_PRE)){
            String realId=id.substring(id.indexOf("-",0)+1,id.length());
            return realId;
        }
        return id;
    }
}
