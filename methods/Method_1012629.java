public void enqueue(final RealCallback<T> callback){
  this.mRawCall.enqueue(new Callback<T>(){
    @Override public void onResponse(    Call<T> call,    Response<T> response){
      T body=response.body();
      if (body instanceof DataBody) {
        DataBody<T> body1=(DataBody<T>)body;
      }
      callback.successful(body);
    }
    @Override public void onFailure(    Call<T> call,    Throwable t){
      callback.failure(t.getMessage());
    }
  }
);
}
