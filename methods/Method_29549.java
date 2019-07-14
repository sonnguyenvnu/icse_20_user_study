/** 
 * ??????
 */
@RequestMapping(value="/updateAppPassword") public ModelAndView doUpdateAppPassword(HttpServletRequest request,HttpServletResponse response,Model model){
  long appId=NumberUtils.toLong(request.getParameter("appId"));
  String password=request.getParameter("password");
  SuccessEnum successEnum=SuccessEnum.FAIL;
  if (appId > 0) {
    try {
      AppDesc appDesc=appService.getByAppId(appId);
      appDesc.setPassword(password);
      appService.update(appDesc);
      successEnum=SuccessEnum.SUCCESS;
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
    }
  }
  model.addAttribute("status",successEnum.value());
  return new ModelAndView("");
}
