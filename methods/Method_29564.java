/** 
 * ????
 */
@RequestMapping(value="/add") public ModelAndView add(HttpServletRequest request,HttpServletResponse response,Model model){
  AppUser appUser=getUserInfo(request);
  InstanceAlertConfig instanceAlertConfig=getInstanceAlertConfig(request);
  SuccessEnum successEnum;
  try {
    logger.warn("user {} want to add instanceAlertConfig {}, result is {}",appUser.getName(),instanceAlertConfig);
    instanceAlertConfigService.save(instanceAlertConfig);
    successEnum=SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    successEnum=SuccessEnum.FAIL;
    model.addAttribute("message",ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
    logger.error(e.getMessage(),e);
  }
  logger.warn("user {} add instanceAlertConfig {}, result is {}",appUser.getName(),instanceAlertConfig,successEnum.value());
  model.addAttribute("status",successEnum.value());
  return new ModelAndView("");
}
