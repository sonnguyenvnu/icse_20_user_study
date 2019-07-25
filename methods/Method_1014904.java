/** 
 * ??
 */
@RequestMapping(value="/login",method=RequestMethod.POST) @ResponseBody public Map<String,Object> login(String username,String password,String captcha) throws IOException {
  String kaptcha=(String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
  if (!USER_ACCOUNT.equals(username)) {
    return R.error("?????");
  }
  if (!USER_ACCOUNT.equals(password)) {
    return R.error("?????");
  }
  session.setAttribute("USER_ACCOUNT",USER_ACCOUNT);
  return R.ok().put("token",USER_ACCOUNT);
}
