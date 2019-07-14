/** 
 * ??????
 * @param instanceReshardProcessId
 * @return
 */
@RequestMapping(value="/retryHorizontalScale") public ModelAndView retryHorizontalScale(HttpServletRequest request,HttpServletResponse response,Model model,int instanceReshardProcessId){
  AppUser appUser=getUserInfo(request);
  logger.warn("user {} retryHorizontalScale id {}",appUser.getName(),instanceReshardProcessId);
  HorizontalResult horizontalResult=appDeployCenter.retryHorizontal(instanceReshardProcessId);
  model.addAttribute("status",horizontalResult.getStatus());
  model.addAttribute("message",horizontalResult.getMessage());
  return new ModelAndView("");
}
