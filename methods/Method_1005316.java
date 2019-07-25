/** 
 * ??????????
 * @return
 */
@RequestMapping(params="roles") public ModelAndView roles(HttpServletRequest request){
  ModelAndView mv=new ModelAndView("system/user/users");
  String ids=oConvertUtils.getString(request.getParameter("ids"));
  mv.addObject("ids",ids);
  return mv;
}
