@RequestMapping(value="/list") public ModelAndView doMachineList(HttpServletRequest request,HttpServletResponse response,Model model,String ipLike){
  List<MachineStats> machineList=machineCenter.getMachineStats(ipLike);
  Map<String,Integer> machineInstanceCountMap=machineCenter.getMachineInstanceCountMap();
  model.addAttribute("list",machineList);
  model.addAttribute("ipLike",ipLike);
  model.addAttribute("machineActive",SuccessEnum.SUCCESS.value());
  model.addAttribute("collectAlert","(???" + ConstUtils.MACHINE_STATS_CRON_MINUTE + "??)");
  model.addAttribute("machineInstanceCountMap",machineInstanceCountMap);
  return new ModelAndView("manage/machine/list");
}
