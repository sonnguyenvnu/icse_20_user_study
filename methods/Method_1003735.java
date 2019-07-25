public static <T>void retry(RetryPolicy retryPolicy,Upstream<? extends T> up,Downstream<? super T> down,BiAction<? super Integer,? super Throwable> onError) throws Exception {
  up.connect(down.onError(e -> {
    if (retryPolicy.isExhausted()) {
      down.error(e);
    }
 else {
      Promise<Duration> delay;
      try {
        onError.execute(retryPolicy.attempts(),e);
        delay=retryPolicy.delay();
      }
 catch (      Throwable errorHandlerError) {
        if (errorHandlerError != e) {
          errorHandlerError.addSuppressed(e);
        }
        down.error(errorHandlerError);
        return;
      }
      delay.connect(new Downstream<Duration>(){
        @Override public void success(        Duration value){
          Execution.sleep(value,() -> retry(retryPolicy.increaseAttempt(),up,down,onError));
        }
        @Override public void error(        Throwable throwable){
          down.error(throwable);
        }
        @Override public void complete(){
          down.complete();
        }
      }
);
    }
  }
));
}
