@RequestMapping(value="/delete") public ModelAndView doDelete(HttpServletRequest request,HttpServletResponse response,Model model){
  String machineIp=request.getParameter("machineIp");
  if (StringUtils.isNotBlank(machineIp)) {
    MachineInfo machineInfo=machineCenter.getMachineInfoByIp(machineIp);
    boolean success=machineDeployCenter.removeMachine(machineInfo);
    logger.warn("delete machine {}, result is {}",machineIp,success);
  }
 else {
    logger.warn("machineIp is empty!");
  }
  return new ModelAndView("redirect:/manage/machine/list");
}
