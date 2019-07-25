/** 
 * ???????????
 * @return
 */
@RequestMapping(params="list") public ModelAndView list(HttpServletRequest request){
  String userName=ResourceUtil.getSessionUser().getUserName();
  request.setAttribute("userName",userName);
  return new ModelAndView("system/authGroup/depart_role");
}
