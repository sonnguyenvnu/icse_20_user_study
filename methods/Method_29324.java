public float getWrite(){
  List<Usage> usageList=diskMap.get(DiskUsageType.write);
  return getUsage(usageList);
}
