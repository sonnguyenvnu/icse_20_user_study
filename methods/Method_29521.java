/** 
 * ??????
 * @return
 */
@RequestMapping(value="/process") public ModelAndView showProcess(HttpServletRequest request,HttpServletResponse response,Model model){
  long id=NumberUtils.toLong(request.getParameter("id"));
  Map<RedisMigrateToolConstant,Map<String,Object>> migrateToolStatMap=appDataMigrateCenter.showMiragteToolProcess(id);
  model.addAttribute("migrateToolStatMap",migrateToolStatMap);
  return new ModelAndView("migrate/process");
}
