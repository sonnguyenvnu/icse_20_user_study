private static void shutdown(final D2Client d2Client1,final D2Client d2Client2,ExecutorService executorService,Long timeout){
  try {
    executorService.submit(new Runnable(){
      @Override public void run(){
        d2Client1.shutdown(new Callback<None>(){
          @Override public void onError(          Throwable e){
            System.err.println("Error shutting down d2Client.");
            e.printStackTrace();
          }
          @Override public void onSuccess(          None result){
            System.out.println("D2 client stopped");
          }
        }
);
        d2Client2.shutdown(new Callback<None>(){
          @Override public void onError(          Throwable e){
            System.err.println("Error shutting down d2Client.");
            e.printStackTrace();
          }
          @Override public void onSuccess(          None result){
            System.out.println("D2 client stopped");
          }
        }
);
      }
    }
).get(timeout,TimeUnit.MILLISECONDS);
  }
 catch (  Exception e) {
    System.err.println("Cannot stop d2 client. Timeout is set to " + timeout + " ms");
    e.printStackTrace();
  }
 finally {
    executorService.shutdown();
  }
}
