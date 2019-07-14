/** 
 * ?????????
 * @param appId ??id
 */
@RequestMapping("/index") public ModelAndView doIndex(HttpServletRequest request,HttpServletResponse response,Model model){
  Long appId=NumberUtils.toLong(request.getParameter("appId"));
  if (appId == null || appId <= 0) {
    return new ModelAndView("");
  }
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appId",appId);
  model.addAttribute("appDesc",appDesc);
  model.addAttribute("tabTag",request.getParameter("tabTag"));
  model.addAttribute("type",request.getParameter("type"));
  model.addAttribute("startDate",request.getParameter("startDate"));
  model.addAttribute("endDate",request.getParameter("endDate"));
  model.addAttribute("exceptionStartDate",request.getParameter("exceptionStartDate"));
  model.addAttribute("exceptionEndDate",request.getParameter("exceptionEndDate"));
  model.addAttribute("valueDistriStartDate",request.getParameter("valueDistriStartDate"));
  model.addAttribute("valueDistriEndDate",request.getParameter("valueDistriEndDate"));
  model.addAttribute("costDistriStartDate",request.getParameter("costDistriStartDate"));
  model.addAttribute("costDistriEndDate",request.getParameter("costDistriEndDate"));
  model.addAttribute("clientIp",request.getParameter("clientIp"));
  model.addAttribute("pageNo",request.getParameter("pageNo"));
  model.addAttribute("firstCommand",request.getParameter("firstCommand"));
  model.addAttribute("timeDimensionality",request.getParameter("timeDimensionality"));
  return new ModelAndView("client/appClientIndex");
}
