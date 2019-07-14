@ApiOperation(value="??") @RequestMapping(value="/login",method=RequestMethod.POST) @ResponseBody public Object login(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
  String username=request.getParameter("username");
  String password=request.getParameter("password");
  String rememberMe=request.getParameter("rememberMe");
  if (StringUtils.isBlank(username)) {
    return new UpmsResult(UpmsResultConstant.EMPTY_USERNAME,"???????");
  }
  if (StringUtils.isBlank(password)) {
    return new UpmsResult(UpmsResultConstant.EMPTY_PASSWORD,"???????");
  }
  Subject subject=SecurityUtils.getSubject();
  Session session=subject.getSession();
  String sessionId=session.getId().toString();
  String hasCode=RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + sessionId);
  if (StringUtils.isBlank(hasCode)) {
    UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
    try {
      if (BooleanUtils.toBoolean(rememberMe)) {
        usernamePasswordToken.setRememberMe(true);
      }
 else {
        usernamePasswordToken.setRememberMe(false);
      }
      subject.login(usernamePasswordToken);
    }
 catch (    UnknownAccountException e) {
      return new UpmsResult(UpmsResultConstant.INVALID_USERNAME,"??????");
    }
catch (    IncorrectCredentialsException e) {
      return new UpmsResult(UpmsResultConstant.INVALID_PASSWORD,"?????");
    }
catch (    LockedAccountException e) {
      return new UpmsResult(UpmsResultConstant.INVALID_ACCOUNT,"??????");
    }
    upmsSessionDao.updateStatus(sessionId,UpmsSession.OnlineStatus.on_line);
    RedisUtil.lpush(ZHENG_UPMS_SERVER_SESSION_IDS,sessionId.toString());
    String code=UUID.randomUUID().toString();
    RedisUtil.set(ZHENG_UPMS_SERVER_SESSION_ID + "_" + sessionId,code,(int)subject.getSession().getTimeout() / 1000);
    RedisUtil.set(ZHENG_UPMS_SERVER_CODE + "_" + code,code,(int)subject.getSession().getTimeout() / 1000);
  }
  String backurl=request.getParameter("backurl");
  if (StringUtils.isBlank(backurl)) {
    UpmsSystem upmsSystem=upmsSystemService.selectUpmsSystemByName(PropertiesFileUtil.getInstance().get("app.name"));
    backurl=null == upmsSystem ? "/" : upmsSystem.getBasepath();
    return new UpmsResult(UpmsResultConstant.SUCCESS,backurl);
  }
 else {
    return new UpmsResult(UpmsResultConstant.SUCCESS,backurl);
  }
}
