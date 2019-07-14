/** 
 * ????
 * @param appId
 */
@RequestMapping("/index") public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Model model,Long appId){
  model.addAttribute("appId",appId);
  return new ModelAndView("manage/appOps/appOpsIndex");
}
