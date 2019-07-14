public float getIops(){
  List<Usage> usageList=diskMap.get(DiskUsageType.transfer);
  return getUsage(usageList);
}
