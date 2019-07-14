@RequestMapping(value="/list") public ModelAndView doTotalList(HttpServletRequest request,HttpServletResponse response,Model model){
  AppUser currentUser=getUserInfo(request);
  List<AppDesc> apps=appService.getAppDescList(currentUser,new AppSearch());
  List<AppDetailVO> appDetailList=new ArrayList<AppDetailVO>();
  long totalApplyMem=0;
  long totalUsedMem=0;
  long totalApps=0;
  long totalRunningApps=0;
  if (apps != null && apps.size() > 0) {
    for (    AppDesc appDesc : apps) {
      AppDetailVO appDetail=appStatsCenter.getAppDetail(appDesc.getAppId());
      appDetailList.add(appDetail);
      totalApplyMem+=appDetail.getMem();
      totalUsedMem+=appDetail.getMemUsePercent() * appDetail.getMem() / 100.0;
      if (appDesc.getStatus() == AppStatusEnum.STATUS_PUBLISHED.getStatus()) {
        totalRunningApps++;
      }
      totalApps++;
    }
  }
  long totalMachineMem=0;
  long totalFreeMachineMem=0;
  List<MachineStats> allMachineStats=machineCenter.getAllMachineStats();
  for (  MachineStats machineStats : allMachineStats) {
    totalMachineMem+=NumberUtils.toLong(machineStats.getMemoryTotal(),0l);
    totalFreeMachineMem+=NumberUtils.toLong(machineStats.getMemoryFree(),0l);
  }
  long totalInstanceMem=0;
  long totalUseInstanceMem=0;
  List<InstanceStats> instanceStats=instanceStatsCenter.getInstanceStats();
  for (  InstanceStats instanceStat : instanceStats) {
    totalInstanceMem+=instanceStat.getMaxMemory();
    totalUseInstanceMem+=instanceStat.getUsedMemory();
  }
  model.addAttribute("totalApps",totalApps);
  model.addAttribute("totalApplyMem",totalApplyMem);
  model.addAttribute("totalUsedMem",totalUsedMem);
  model.addAttribute("totalRunningApps",totalRunningApps);
  model.addAttribute("totalMachineMem",totalMachineMem);
  model.addAttribute("totalFreeMachineMem",totalFreeMachineMem);
  model.addAttribute("totalInstanceMem",totalInstanceMem);
  model.addAttribute("totalUseInstanceMem",totalUseInstanceMem);
  model.addAttribute("apps",apps);
  model.addAttribute("appDetailList",appDetailList);
  model.addAttribute("list",apps);
  model.addAttribute("totalActive",SuccessEnum.SUCCESS.value());
  return new ModelAndView("manage/total/list");
}
