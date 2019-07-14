/** 
 * ??redis sentinel??
 * @param nodeInfoList
 * @return
 */
private DataFormatCheckResult checkSentinelAppDeploy(String[] nodeInfoList){
  int redisLineNum=0;
  int sentinelLineNum=0;
  for (  String nodeInfo : nodeInfoList) {
    nodeInfo=StringUtils.trim(nodeInfo);
    String[] array=nodeInfo.split(ConstUtils.COLON);
    if (array.length == 3) {
      redisLineNum++;
    }
 else     if (array.length == 1) {
      sentinelLineNum++;
    }
  }
  final int redisLineMustNum=1;
  if (redisLineNum < redisLineMustNum) {
    return DataFormatCheckResult.fail("????????, Sentinel??????Redis????!");
  }
 else   if (redisLineNum > redisLineMustNum) {
    return DataFormatCheckResult.fail("????????, Sentinel???Redis?????????!");
  }
  final int sentinelLessNum=3;
  if (sentinelLineNum < sentinelLessNum) {
    return DataFormatCheckResult.fail("????????, Sentinel???Sentinel??????" + sentinelLessNum + "?!");
  }
  return DataFormatCheckResult.success("????????????????!");
}
