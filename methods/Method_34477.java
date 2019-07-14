private static HystrixObservable castToObservable(HystrixInvokable invokable){
  if (invokable instanceof HystrixObservable) {
    return (HystrixObservable)invokable;
  }
  throw new RuntimeException("Command should implement " + HystrixObservable.class.getCanonicalName() + " interface to execute in observable mode");
}
