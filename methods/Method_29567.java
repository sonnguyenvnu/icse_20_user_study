/** 
 * ????
 */
@RequestMapping(value="/update") public ModelAndView update(HttpServletRequest request,HttpServletResponse response,Model model){
  AppUser appUser=getUserInfo(request);
  int id=NumberUtils.toInt(request.getParameter("id"));
  String alertValue=request.getParameter("alertValue");
  int checkCycle=NumberUtils.toInt(request.getParameter("checkCycle"));
  logger.warn("user {} want to change instance alert id={}, alertValue={}, checkCycle={}",appUser.getName(),alertValue,checkCycle);
  SuccessEnum successEnum;
  try {
    instanceAlertConfigService.update(id,alertValue,checkCycle);
    successEnum=SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    successEnum=SuccessEnum.FAIL;
    model.addAttribute("message",ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
    logger.error(e.getMessage(),e);
  }
  logger.warn("user {} change instance alert id={}, alertValue={}, checkCycle={}, result is {}",appUser.getName(),alertValue,checkCycle,successEnum.info());
  model.addAttribute("status",successEnum.value());
  return new ModelAndView("");
}
