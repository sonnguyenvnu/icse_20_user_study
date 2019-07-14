private static HystrixExecutable castToExecutable(HystrixInvokable invokable,ExecutionType executionType){
  if (invokable instanceof HystrixExecutable) {
    return (HystrixExecutable)invokable;
  }
  throw new RuntimeException("Command should implement " + HystrixExecutable.class.getCanonicalName() + " interface to execute in: " + executionType + " mode");
}
