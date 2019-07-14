@Override public void enqueue(@NonNull Callback<T> callback){
  mRequest.enqueue(new Callback<S>(){
    @Override public void onResponse(    S response){
      callback.onResponse(transform(response));
    }
    @Override public void onErrorResponse(    ApiError error){
      callback.onErrorResponse(error);
    }
  }
);
}
