@ApiOperation(value="??") @RequestMapping(value="/login",method=RequestMethod.GET) public String login(HttpServletRequest request){
  Subject subject=SecurityUtils.getSubject();
  Session session=subject.getSession();
  String serverSessionId=session.getId().toString();
  String code=RedisUtil.get(ZHENG_UPMS_SERVER_SESSION_ID + "_" + serverSessionId);
  if (StringUtils.isNotBlank(code)) {
    String backurl=request.getParameter("backurl");
    String username=(String)subject.getPrincipal();
    if (StringUtils.isBlank(backurl)) {
      backurl="/";
    }
 else {
      if (backurl.contains("?")) {
        backurl+="&upms_code=" + code + "&upms_username=" + username;
      }
 else {
        backurl+="?upms_code=" + code + "&upms_username=" + username;
      }
    }
    LOGGER.debug("??????????code???{}",backurl);
    return "redirect:" + backurl;
  }
  return "/sso/login.jsp";
}
