/** 
 * ????
 * @param appDesc ????
 * @param memSize ????(G)
 * @return
 */
@RequestMapping(value="/add",method=RequestMethod.POST) public ModelAndView doAppAdd(HttpServletRequest request,HttpServletResponse response,Model model,AppDesc appDesc,String memSize){
  AppUser appUser=getUserInfo(request);
  if (appDesc != null) {
    Timestamp now=new Timestamp(new Date().getTime());
    appDesc.setCreateTime(now);
    appDesc.setPassedTime(now);
    appDesc.setVerId(1);
    appDesc.setStatus((short)AppStatusEnum.STATUS_ALLOCATED.getStatus());
    appDeployCenter.createApp(appDesc,appUser,memSize);
  }
  return new ModelAndView("redirect:/admin/app/list");
}
