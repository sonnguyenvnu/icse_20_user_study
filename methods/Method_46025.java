@Override public double getExceptionRate(){
  long invokeCount=getInvokeCount();
  return invokeCount == 0 ? -1 : CalculateUtils.divide(getExceptionCount(),invokeCount);
}
