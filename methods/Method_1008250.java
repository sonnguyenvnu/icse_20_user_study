private static String setting(Allocation allocation,boolean usedIndexSetting){
  StringBuilder buf=new StringBuilder();
  if (usedIndexSetting) {
    buf.append("index setting [");
    buf.append(INDEX_ROUTING_ALLOCATION_ENABLE_SETTING.getKey());
  }
 else {
    buf.append("cluster setting [");
    buf.append(CLUSTER_ROUTING_ALLOCATION_ENABLE_SETTING.getKey());
  }
  buf.append("=").append(allocation.toString().toLowerCase(Locale.ROOT)).append("]");
  return buf.toString();
}
