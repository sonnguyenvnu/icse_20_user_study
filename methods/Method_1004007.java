@Override protected int _update(long startTime,long rtt,int inflight,boolean didDrop){
  int currentLimit=getLimit();
  if (didDrop || rtt > timeout) {
    currentLimit=(int)(currentLimit * backoffRatio);
  }
 else   if (inflight * 2 >= currentLimit) {
    currentLimit=currentLimit + 1;
  }
  if (currentLimit >= maxLimit) {
    currentLimit=currentLimit / 2;
  }
  return Math.min(maxLimit,Math.max(minLimit,currentLimit));
}
