/** 
 * ???????????
 * @param appId    ??id
 * @param userName ???(????)
 * @return
 */
@RequestMapping(value="/addAppToUser") public ModelAndView doAddAppToUser(HttpServletRequest request,HttpServletResponse response,Model model,Long appId,String userName){
  if (StringUtils.isNotBlank(userName)) {
    AppUser needAddAppUser=userService.getByName(userName);
    if (needAddAppUser != null) {
      appService.saveAppToUser(appId,needAddAppUser.getId());
      write(response,String.valueOf(SuccessEnum.SUCCESS.value()));
    }
 else {
      write(response,String.valueOf(SuccessEnum.FAIL.value()));
    }
  }
  return null;
}
