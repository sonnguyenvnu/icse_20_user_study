public String getExt(){
  StringBuilder sb=new StringBuilder();
  for (  DiskUsageType type : diskMap.keySet()) {
    if (DiskUsageType.space == type) {
      continue;
    }
    sb.append(type.getValue());
    sb.append("=");
    List<Usage> usageList=diskMap.get(type);
    for (    Usage use : usageList) {
      sb.append(use.getName());
      sb.append(":");
      sb.append(use.getValue());
      sb.append(",");
    }
    sb.append(";");
  }
  return sb.toString();
}
