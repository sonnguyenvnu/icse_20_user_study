@Override public Object call(final Method method,final Object[] args,@Nullable AsyncMethodCallback callback,@Nullable Amount<Long,Time> connectTimeoutOverride) throws Throwable {
  ResultCapture capture=new ResultCapture(){
    @Override public void success(){
    }
    @Override public boolean fail(    Throwable t){
      StringBuilder message=new StringBuilder("Thrift call failed: ");
      message.append(method.getName()).append("(");
      ARG_JOINER.appendTo(message,args);
      message.append(")");
      LOG.warning(message.toString());
      return true;
    }
  }
;
  try {
    return invoke(method,args,callback,capture,connectTimeoutOverride);
  }
 catch (  Throwable t) {
    capture.fail(t);
    throw t;
  }
}
