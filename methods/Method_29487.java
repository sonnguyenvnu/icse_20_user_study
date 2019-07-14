/** 
 * ??????????
 */
@RequestMapping("/valueDistribute") public ModelAndView doValueDistribute(HttpServletRequest request,HttpServletResponse response,Model model) throws ParseException {
  Long appId=NumberUtils.toLong(request.getParameter("appId"));
  if (appId <= 0) {
    return new ModelAndView("");
  }
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appDesc",appDesc);
  TimeBetween timeBetween=new TimeBetween();
  try {
    timeBetween=fillWithValueDistriTime(request,model);
  }
 catch (  ParseException e) {
    logger.error(e.getMessage(),e);
  }
  long startTime=timeBetween.getStartTime();
  long endTime=timeBetween.getEndTime();
  List<AppClientValueDistriSimple> appClientValueDistriSimpleList=clientReportValueDistriService.getAppValueDistriList(appId,startTime,endTime);
  model.addAttribute("appClientValueDistriSimpleList",appClientValueDistriSimpleList);
  model.addAttribute("appClientValueDistriSimpleListJson",JSONObject.toJSONString(appClientValueDistriSimpleList));
  return new ModelAndView("client/clientValueDistribute");
}
