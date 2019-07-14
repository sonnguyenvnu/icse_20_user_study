/** 
 * ??????
 * @return
 */
@RequestMapping(value="/config") public ModelAndView config(HttpServletRequest request,HttpServletResponse response,Model model){
  long id=NumberUtils.toLong(request.getParameter("id"));
  String config=appDataMigrateCenter.showDataMigrateConf(id);
  model.addAttribute("configList",Arrays.asList(config.split(ConstUtils.NEXT_LINE)));
  return new ModelAndView("migrate/config");
}
