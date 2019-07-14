public String getSpace(){
  StringBuilder sb=new StringBuilder();
  List<Usage> usageList=diskMap.get(DiskUsageType.space);
  if (usageList == null) {
    return sb.toString();
  }
  for (  Usage use : usageList) {
    sb.append(use.getName());
    sb.append(":");
    sb.append(use.getValue());
    sb.append(",");
  }
  return sb.toString();
}
