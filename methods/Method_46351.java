/** 
 * ???? span
 * @param sofaTracerSpan ?????? span
 */
@Override public void doReportStat(SofaTracerSpan sofaTracerSpan){
  Map<String,String> tagsWithStr=sofaTracerSpan.getTagsWithStr();
  StatMapKey statKey=new StatMapKey();
  String fromApp=getFromApp(tagsWithStr);
  String toApp=getToApp(tagsWithStr);
  String zone=getZone(tagsWithStr);
  String serviceName=tagsWithStr.get(RpcSpanTags.SERVICE);
  String methodName=tagsWithStr.get(RpcSpanTags.METHOD);
  statKey.setKey(buildString(new String[]{fromApp,toApp,serviceName,methodName}));
  String resultCode=tagsWithStr.get(RpcSpanTags.RESULT_CODE);
  statKey.setResult(isSuccess(resultCode) ? "Y" : "N");
  statKey.setEnd(buildString(new String[]{getLoadTestMark(sofaTracerSpan),zone}));
  statKey.setLoadTest(TracerUtils.isLoadTest(sofaTracerSpan));
  statKey.addKey(RpcSpanTags.LOCAL_APP,tagsWithStr.get(RpcSpanTags.LOCAL_APP));
  statKey.addKey(RpcSpanTags.REMOTE_APP,tagsWithStr.get(RpcSpanTags.REMOTE_APP));
  statKey.addKey(RpcSpanTags.SERVICE,serviceName);
  statKey.addKey(RpcSpanTags.METHOD,methodName);
  long duration=sofaTracerSpan.getEndTime() - sofaTracerSpan.getStartTime();
  long[] values=new long[]{1,duration};
  this.addStat(statKey,values);
}
