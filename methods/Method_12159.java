@RequestMapping("/login") @ResponseBody public ResultGeekQ<Logininfo> dologin(HttpServletResponse response,HttpServletRequest request,String username,String password){
  ResultGeekQ<Logininfo> result=ResultGeekQ.build();
  ResultGeekQ<Logininfo> login=this.iLogininfoService.login(username,password,Constants.USERTYPE_NORMAL,request.getRemoteAddr());
  result.setData(login.getData());
  if (!ResultGeekQ.isSuccess(login)) {
    result.withError(login.getCode(),login.getMessage());
  }
  return result;
}
