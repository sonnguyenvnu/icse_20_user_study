@RequestMapping(value="/list") public ModelAndView doUserList(HttpServletRequest request,HttpServletResponse response,Model model){
  List<InstanceFault> faults=memFaultService.getFaultList();
  model.addAttribute("faults",faults);
  return new ModelAndView("manage/fault/list");
}
