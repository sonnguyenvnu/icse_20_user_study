/** 
 * ??targetMinute?????
 * @param targetMinute
 */
private static void clearCostTime(String targetMinute){
  try {
    if (targetMinute == "" || "".equals(targetMinute)) {
      return;
    }
    long targetMinuteLong=NumberUtil.toLong(targetMinute);
    if (targetMinuteLong == 0) {
      return;
    }
    for (    CostTimeDetailStatKey key : DATA_COST_TIME_MAP_ALL.keySet()) {
      long minute=NumberUtil.toLong(key.getCurrentMinute());
      if (minute < targetMinuteLong) {
        DATA_COST_TIME_MAP_ALL.remove(key);
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
}
