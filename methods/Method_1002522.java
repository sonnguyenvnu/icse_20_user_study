@Override public void echo(String msg,Callback<String> callback){
  callback.onError(new Exception("service failure"));
}
