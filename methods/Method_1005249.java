/** 
 * ????????
 */
@RequestMapping(params="dialog") public ModelAndView dialog(HttpServletRequest request){
  String code=request.getParameter("code");
  request.setAttribute("queryCode",code);
  String tableName=request.getParameter("tableName");
  request.setAttribute("tableName",tableName);
  return new ModelAndView("jeecg/superquery/dialog");
}
