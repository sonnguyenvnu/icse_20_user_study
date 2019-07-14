/** 
 * ??AppDesc
 * @param request
 * @return
 */
private AppDesc genAppDesc(HttpServletRequest request){
  AppUser currentUser=getUserInfo(request);
  Date date=new Date();
  AppDesc appDesc=new AppDesc();
  appDesc.setName(request.getParameter("name"));
  appDesc.setIntro(request.getParameter("intro"));
  appDesc.setOfficer(request.getParameter("officer"));
  appDesc.setType(NumberUtils.toInt(request.getParameter("type")));
  appDesc.setIsTest(NumberUtils.toInt(request.getParameter("isTest")));
  appDesc.setMemAlertValue(NumberUtils.toInt(request.getParameter("memAlertValue")));
  appDesc.setPassword(request.getParameter("password"));
  appDesc.setUserId(currentUser.getId());
  appDesc.setStatus(2);
  appDesc.setCreateTime(date);
  appDesc.setPassedTime(date);
  appDesc.setVerId(1);
  return appDesc;
}
