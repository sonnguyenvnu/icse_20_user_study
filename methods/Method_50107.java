/** 
 * Converts a  {@link ListenableFuture} to a Java8 {@link java.util.concurrent.CompletableFuture}. <p> {@see CompletableFuture} is supported by the provided {@link graphql.execution.AsyncExecutionStrategy}. <p>Note: This should be installed before any other instrumentation.
 */
public static Instrumentation listenableFutureInstrumentation(Executor executor){
  return new SimpleInstrumentation(){
    @Override public DataFetcher<?> instrumentDataFetcher(    DataFetcher<?> dataFetcher,    InstrumentationFieldFetchParameters parameters){
      return (DataFetcher<Object>)dataFetchingEnvironment -> {
        Object data=dataFetcher.get(dataFetchingEnvironment);
        if (data instanceof ListenableFuture) {
          ListenableFuture<Object> listenableFuture=(ListenableFuture<Object>)data;
          CompletableFuture<Object> completableFuture=new CompletableFuture<>();
          Futures.addCallback(listenableFuture,new FutureCallback<Object>(){
            @Override public void onSuccess(            Object result){
              completableFuture.complete(result);
            }
            @Override public void onFailure(            Throwable t){
              completableFuture.completeExceptionally(t);
            }
          }
,executor);
          return completableFuture;
        }
        return data;
      }
;
    }
  }
;
}
