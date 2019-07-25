/** 
 * ????????
 * @param request
 * @return
 */
@RequestMapping(params="addsign") public ModelAndView addsign(HttpServletRequest request){
  String id=request.getParameter("id");
  request.setAttribute("id",id);
  return new ModelAndView("system/user/usersign");
}
