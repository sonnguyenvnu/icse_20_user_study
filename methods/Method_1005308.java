/** 
 * ????
 * @return
 */
@RequestMapping(params="changepassword") public String changepassword(HttpServletRequest request){
  TSUser user=ResourceUtil.getSessionUser();
  request.setAttribute("user",user);
  return "system/user/changepassword";
}
