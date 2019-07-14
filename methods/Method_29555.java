/** 
 * /manage/client/exception
 * @param request
 * @param response
 * @param model
 * @return
 */
@RequestMapping(value="/exception") public ModelAndView doClientExceptionStat(HttpServletRequest request,HttpServletResponse response,Model model){
  String ip=request.getParameter("ip");
  model.addAttribute("ip",ip);
  long collectTime=NumberUtils.toLong(new SimpleDateFormat("yyyyMMdd000000").format(DateUtils.addMonths(new Date(),-1)));
  List<ClientInstanceException> clientInstanceExceptionList=clientReportExceptionService.getInstanceExceptionStat(ip,collectTime);
  model.addAttribute("clientInstanceExceptionList",clientInstanceExceptionList);
  fillAppInfoMap(model);
  model.addAttribute("clientExceptionActive",SuccessEnum.SUCCESS.value());
  return new ModelAndView("manage/client/exception/list");
}
