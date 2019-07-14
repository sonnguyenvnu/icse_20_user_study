public float getBusy(){
  List<Usage> usageList=diskMap.get(DiskUsageType.busy);
  return getUsage(usageList);
}
