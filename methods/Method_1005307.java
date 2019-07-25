/** 
 * ????
 * @return
 */
@RequestMapping(params="userinfo") public String userinfo(HttpServletRequest request){
  TSUser user=ResourceUtil.getSessionUser();
  request.setAttribute("user",user);
  return "system/user/userinfo";
}
