void checkLimits(long instant,String desc){
  DateTime limit;
  if ((limit=iLowerLimit) != null && instant < limit.getMillis()) {
    throw new LimitException(desc,true);
  }
  if ((limit=iUpperLimit) != null && instant >= limit.getMillis()) {
    throw new LimitException(desc,false);
  }
}
