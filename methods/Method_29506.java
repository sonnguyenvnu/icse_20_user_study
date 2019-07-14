/** 
 * ????????
 */
@RequestMapping(value="/changeAppAlertConfig") public ModelAndView doChangeAppAlertConfig(HttpServletRequest request,HttpServletResponse response,Model model){
  long appId=NumberUtils.toLong(request.getParameter("appId"),-1);
  int memAlertValue=NumberUtils.toInt(request.getParameter("memAlertValue"),-1);
  int clientConnAlertValue=NumberUtils.toInt(request.getParameter("clientConnAlertValue"),-1);
  SuccessEnum result=appService.changeAppAlertConfig(appId,memAlertValue,clientConnAlertValue,getUserInfo(request));
  write(response,String.valueOf(result.value()));
  return null;
}
