/** 
 * ??????
 * @return
 */
@RequestMapping(value="/log") public ModelAndView log(HttpServletRequest request,HttpServletResponse response,Model model){
  long id=NumberUtils.toLong(request.getParameter("id"));
  int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),0);
  if (pageSize == 0) {
    pageSize=100;
  }
  String log=appDataMigrateCenter.showDataMigrateLog(id,pageSize);
  model.addAttribute("logList",Arrays.asList(log.split(ConstUtils.NEXT_LINE)));
  return new ModelAndView("migrate/log");
}
