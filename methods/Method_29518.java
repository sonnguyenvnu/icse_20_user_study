/** 
 * ??????
 * @return
 */
@RequestMapping(value="/stop") public ModelAndView stop(HttpServletRequest request,HttpServletResponse response,Model model){
  long id=NumberUtils.toLong(request.getParameter("id"));
  AppDataMigrateResult stopMigrateResult=appDataMigrateCenter.stopMigrate(id);
  model.addAttribute("status",stopMigrateResult.getStatus());
  model.addAttribute("message",stopMigrateResult.getMessage());
  return new ModelAndView("");
}
