/** 
 * ?????
 * @param appId
 * @return
 */
@RequestMapping("/topology") public ModelAndView statTopology(HttpServletRequest request,HttpServletResponse response,Long appId,Model model){
  AppDesc appDesc=appService.getByAppId(appId);
  model.addAttribute("appDesc",appDesc);
  fillAppInstanceStats(appId,model);
  return new ModelAndView("app/appTopology");
}
