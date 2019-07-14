/** 
 * ??????(????)
 * @return
 */
@RequestMapping(value="/list") public ModelAndView list(HttpServletRequest request,HttpServletResponse response,Model model,AppDataMigrateSearch appDataMigrateSearch){
  List<AppDataMigrateStatus> appDataMigrateStatusList=appDataMigrateCenter.search(appDataMigrateSearch);
  model.addAttribute("appDataMigrateStatusList",appDataMigrateStatusList);
  model.addAttribute("appDataMigrateSearch",appDataMigrateSearch);
  return new ModelAndView("migrate/list");
}
