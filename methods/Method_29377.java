/** 
 * ??????
 * @param nodeInfoList
 * @return
 */
private DataFormatCheckResult checkStandaloneAppDeploy(String[] nodeInfoList){
  int redisLineNum=0;
  for (  String nodeInfo : nodeInfoList) {
    nodeInfo=StringUtils.trim(nodeInfo);
    String[] array=nodeInfo.split(ConstUtils.COLON);
    if (array.length == 2) {
      redisLineNum++;
    }
  }
  if (redisLineNum != 1) {
    return DataFormatCheckResult.fail("????????, Standalone???????masterIp:memSize(M)");
  }
  return DataFormatCheckResult.success("????????????????!");
}
