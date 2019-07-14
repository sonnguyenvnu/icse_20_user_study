@RequestMapping(value="/remove") public String remove(HttpServletRequest request,HttpServletResponse response,Model model){
  String name=request.getParameter("name");
  String group=request.getParameter("group");
  if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(group)) {
    schedulerCenter.unscheduleJob(new TriggerKey(name,group));
  }
  return "redirect:/manage/quartz/list";
}
