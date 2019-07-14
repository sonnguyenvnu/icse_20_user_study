@RequestMapping("/fault") public ModelAndView fault(HttpServletRequest request,HttpServletResponse response,Model model,Integer admin,Integer instanceId,Long appId){
  List<InstanceFault> list=null;
  try {
    list=instanceAlertService.getListByInstId(instanceId);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  if (list == null) {
    list=new ArrayList<InstanceFault>();
  }
  model.addAttribute("list",list);
  return new ModelAndView("instance/instanceFault");
}
