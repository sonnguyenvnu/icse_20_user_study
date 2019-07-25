private static String setting(Rebalance rebalance,boolean usedIndexSetting){
  StringBuilder buf=new StringBuilder();
  if (usedIndexSetting) {
    buf.append("index setting [");
    buf.append(INDEX_ROUTING_REBALANCE_ENABLE_SETTING.getKey());
  }
 else {
    buf.append("cluster setting [");
    buf.append(CLUSTER_ROUTING_REBALANCE_ENABLE_SETTING.getKey());
  }
  buf.append("=").append(rebalance.toString().toLowerCase(Locale.ROOT)).append("]");
  return buf.toString();
}
