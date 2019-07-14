@Override public void update(InvocationStat snapshot){
  invokeCount.addAndGet(-snapshot.getInvokeCount());
  exceptionCount.addAndGet(-snapshot.getExceptionCount());
}
