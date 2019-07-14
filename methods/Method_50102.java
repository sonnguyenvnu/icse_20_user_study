public void query(String query){
  GraphQlRequest request=GraphQlRequest.newBuilder().build().newBuilder().setQuery(query).build();
  stub.execute(request,new StreamObserver<GraphQlResponse>(){
    @Override public void onNext(    GraphQlResponse value){
      logger.info("onNext: " + value);
    }
    @Override public void onError(    Throwable t){
      logger.log(Level.WARNING,t,() -> "onError");
      ON_COMPLETE.countDown();
    }
    @Override public void onCompleted(){
      logger.info("onCompleted");
      ON_COMPLETE.countDown();
    }
  }
);
}
