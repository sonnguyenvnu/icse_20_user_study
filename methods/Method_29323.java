public float getRead(){
  List<Usage> usageList=diskMap.get(DiskUsageType.read);
  return getUsage(usageList);
}
