/** 
 * ???????
 */
@RequestMapping(value="/updateAppImportantLevel") public ModelAndView doUpdateAppImportantLevel(HttpServletRequest request,HttpServletResponse response,Model model){
  long appId=NumberUtils.toLong(request.getParameter("appId"));
  int importantLevel=NumberUtils.toInt(request.getParameter("importantLevel"));
  SuccessEnum successEnum=SuccessEnum.FAIL;
  if (appId > 0 && importantLevel >= 0) {
    try {
      AppDesc appDesc=appService.getByAppId(appId);
      appDesc.setImportantLevel(importantLevel);
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
