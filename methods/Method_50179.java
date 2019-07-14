public static Instrumentation apiFutureInstrumentation(){
  return new NoOpInstrumentation(){
    @Override public DataFetcher<?> instrumentDataFetcher(    DataFetcher<?> dataFetcher,    InstrumentationFieldFetchParameters parameters){
      return (DataFetcher<Object>)dataFetchingEnvironment -> {
        Object data=dataFetcher.get(dataFetchingEnvironment);
        if (data instanceof ApiFuture) {
          return FutureConverter.toCompletableFuture(apiFutureToListenableFuture((ApiFuture<?>)data));
        }
        return data;
      }
;
    }
  }
;
}
