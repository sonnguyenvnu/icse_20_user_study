private void doCycleRetry(Request request){
  Object cycleTriedTimesObject=request.getExtra(Request.CYCLE_TRIED_TIMES);
  if (cycleTriedTimesObject == null) {
    addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES,1));
  }
 else {
    int cycleTriedTimes=(Integer)cycleTriedTimesObject;
    cycleTriedTimes++;
    if (cycleTriedTimes < site.getCycleRetryTimes()) {
      addRequest(SerializationUtils.clone(request).setPriority(0).putExtra(Request.CYCLE_TRIED_TIMES,cycleTriedTimes));
    }
  }
  sleep(site.getRetrySleepTime());
}
