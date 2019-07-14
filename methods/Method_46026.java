@Override public InvocationStat snapshot(){
  ServiceExceptionInvocationStat invocationStat=new ServiceExceptionInvocationStat(dimension);
  invocationStat.setInvokeCount(getInvokeCount());
  invocationStat.setExceptionCount(getExceptionCount());
  return invocationStat;
}
