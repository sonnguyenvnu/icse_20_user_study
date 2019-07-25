/** 
 * ????????
 * @return
 */
@RequestMapping(params="operation") public ModelAndView operation(HttpServletRequest request,String functionId){
  request.setAttribute("functionId",functionId);
  return new ModelAndView("system/operation/operationList");
}
