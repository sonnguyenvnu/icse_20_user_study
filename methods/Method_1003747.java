/** 
 * Facilitates capturing a value before the the promise is subscribed and using it to later augment the result. <p> The  {@code before} factory is invoked as the promise is subscribed.As the promise result becomes available, it and the result are given to the  {@code after} function.The return value of the  {@code after} function forms the basis of the promise returned from this method.
 * @param before the before value supplier
 * @param after the after function
 * @param < B > the before value type
 * @param < A > the after value type
 * @return a promise
 * @since 1.5
 */
default <B,A>Promise<A> around(Factory<? extends B> before,BiFunction<? super B,? super ExecResult<T>,? extends ExecResult<A>> after){
  return transform(up -> down -> {
    B start;
    try {
      start=before.create();
    }
 catch (    Throwable e) {
      down.error(e);
      return;
    }
    up.connect(new Downstream<T>(){
      private void onResult(      ExecResult<T> originalResult){
        ExecResult<A> newResult;
        try {
          newResult=after.apply(start,originalResult);
        }
 catch (        Throwable t) {
          if (originalResult.isError() && originalResult.getThrowable() != t) {
            t.addSuppressed(originalResult.getThrowable());
          }
          down.error(t);
          return;
        }
        down.accept(newResult);
      }
      @Override public void success(      T value){
        onResult(ExecResult.of(Result.success(value)));
      }
      @Override public void error(      Throwable throwable){
        onResult(ExecResult.of(Result.error(throwable)));
      }
      @Override public void complete(){
        onResult(ExecResult.complete());
      }
    }
);
  }
);
}
