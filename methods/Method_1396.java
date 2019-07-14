/** 
 * This methods blocks the calling thread until the  {@link DataSource} has a final result, hasbeen cancelled or has failed.
 * @param dataSource The {@link DataSource} to wait for. The caller MUST close the data sourceafter this method returned!
 * @param < T > The type parameter for the {@link DataSource}
 * @return The final result of the {@link DataSource}. Intermediate results are ignored. Might be <code>null</code> if the data source has been cancelled.
 * @throws Throwable if the {@link DataSource} has failed
 */
@Nullable public static <T>T waitForFinalResult(DataSource<T> dataSource) throws Throwable {
  final CountDownLatch latch=new CountDownLatch(1);
  final ValueHolder<T> resultHolder=new ValueHolder<>();
  final ValueHolder<Throwable> pendingException=new ValueHolder<>();
  dataSource.subscribe(new DataSubscriber<T>(){
    @Override public void onNewResult(    DataSource<T> dataSource){
      if (!dataSource.isFinished()) {
        return;
      }
      try {
        resultHolder.value=dataSource.getResult();
      }
  finally {
        latch.countDown();
      }
    }
    @Override public void onFailure(    DataSource<T> dataSource){
      try {
        pendingException.value=dataSource.getFailureCause();
      }
  finally {
        latch.countDown();
      }
    }
    @Override public void onCancellation(    DataSource<T> dataSource){
      latch.countDown();
    }
    @Override public void onProgressUpdate(    DataSource<T> dataSource){
    }
  }
,new Executor(){
    @Override public void execute(    Runnable command){
      command.run();
    }
  }
);
  latch.await();
  if (pendingException.value != null) {
    throw pendingException.value;
  }
  return resultHolder.value;
}
