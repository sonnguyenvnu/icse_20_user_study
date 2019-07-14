private void fillAppInfoMap(Model model){
  List<AppDesc> appDescList=appService.getAllAppDesc();
  Map<Long,String> appIdOwnerMap=new HashMap<Long,String>();
  for (  AppDesc appDesc : appDescList) {
    appIdOwnerMap.put(appDesc.getAppId(),appDesc.getOfficer());
  }
  model.addAttribute("appIdOwnerMap",appIdOwnerMap);
  Map<Long,String> appIdNameMap=new HashMap<Long,String>();
  for (  AppDesc appDesc : appDescList) {
    appIdNameMap.put(appDesc.getAppId(),appDesc.getName());
  }
  model.addAttribute("appIdNameMap",appIdNameMap);
}
