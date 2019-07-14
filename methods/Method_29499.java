/** 
 * ?????????
 * @param appName
 * @return
 */
@RequestMapping(value="/checkAppNameExist") public ModelAndView doCheckAppNameExist(HttpServletRequest request,HttpServletResponse response,Model model,String appName){
  AppDesc appDesc=appService.getAppByName(appName);
  if (appDesc != null) {
    write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
  }
 else {
    write(response,String.valueOf(SuccessEnum.FAIL.value()));
  }
  return null;
}
