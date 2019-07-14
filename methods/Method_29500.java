/** 
 * ??????
 * @param appId
 * @return
 */
@RequestMapping("/command") public ModelAndView command(HttpServletRequest request,HttpServletResponse response,Model model,Long appId){
  if (appId != null && appId > 0) {
    model.addAttribute("appId",appId);
  }
  return new ModelAndView("app/appCommand");
}
