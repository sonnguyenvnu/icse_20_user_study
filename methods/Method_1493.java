private Consumer<T> createConsumer(){
  return new BaseConsumer<T>(){
    @Override protected void onNewResultImpl(    @Nullable T newResult,    @Status int status){
      AbstractProducerToDataSourceAdapter.this.onNewResultImpl(newResult,status);
    }
    @Override protected void onFailureImpl(    Throwable throwable){
      AbstractProducerToDataSourceAdapter.this.onFailureImpl(throwable);
    }
    @Override protected void onCancellationImpl(){
      AbstractProducerToDataSourceAdapter.this.onCancellationImpl();
    }
    @Override protected void onProgressUpdateImpl(    float progress){
      AbstractProducerToDataSourceAdapter.this.setProgress(progress);
    }
  }
;
}
