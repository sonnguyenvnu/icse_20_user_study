/** 
 * ????
 */
@RequestMapping(value="/remove") public ModelAndView remove(HttpServletRequest request,HttpServletResponse response,Model model){
  AppUser appUser=getUserInfo(request);
  int id=NumberUtils.toInt(request.getParameter("id"));
  InstanceAlertConfig instanceAlertConfig=instanceAlertConfigService.get(id);
  logger.warn("user {} want to delete config id {}, instanceAlertConfig {}",appUser.getName(),id,instanceAlertConfig);
  SuccessEnum successEnum;
  try {
    instanceAlertConfigService.remove(id);
    successEnum=SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    successEnum=SuccessEnum.FAIL;
    model.addAttribute("message",ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
    logger.error(e.getMessage(),e);
  }
  logger.warn("user {} want to delete config id {}, instanceAlertConfig {}, result is {}",appUser.getName(),id,instanceAlertConfig,successEnum.info());
  model.addAttribute("status",successEnum.value());
  return new ModelAndView("");
}
