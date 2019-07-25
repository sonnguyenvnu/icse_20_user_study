@Override public void print(StatKey statKey,long[] values){
  if (this.isClosePrint.get()) {
    return;
  }
  StatMapKey statMapKey=(StatMapKey)statKey;
  jsonBuffer.reset();
  jsonBuffer.appendBegin(CommonSpanTags.TIME,Timestamp.currentTime());
  jsonBuffer.append(CommonSpanTags.STAT_KEY,this.statKeySplit(statMapKey));
  jsonBuffer.append(CommonSpanTags.COUNT,values[0]);
  jsonBuffer.append(CommonSpanTags.TIME_COST_MILLISECONDS,values[1]);
  jsonBuffer.append(CommonSpanTags.SUCCESS,statMapKey.getResult());
  jsonBuffer.appendEnd();
  try {
    if (appender instanceof LoadTestAwareAppender) {
      ((LoadTestAwareAppender)appender).append(jsonBuffer.toString(),statKey.isLoadTest());
    }
 else {
      appender.append(jsonBuffer.toString());
    }
    appender.flush();
  }
 catch (  Throwable t) {
    SelfLog.error("stat log <" + statTracerName + "> error!",t);
  }
}
