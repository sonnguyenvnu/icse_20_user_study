@RequestMapping("/command") public ModelAndView command(HttpServletRequest request,HttpServletResponse response,Model model,Integer admin,Long instanceId,Long appId){
  if (instanceId != null && instanceId > 0) {
    model.addAttribute("instanceId",instanceId);
  }
  return new ModelAndView("instance/instanceCommand");
}
