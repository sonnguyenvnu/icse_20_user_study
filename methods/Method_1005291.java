/** 
 * ?????? ????
 * @return
 */
@RequestMapping(params="list") public ModelAndView list(HttpServletRequest request){
  String companyId=request.getParameter("companyId");
  request.setAttribute("companyId",companyId);
  return new ModelAndView("system/position/tSCompanyPositionList");
}
