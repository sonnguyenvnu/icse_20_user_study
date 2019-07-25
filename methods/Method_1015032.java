public static <T>void get(final String url,Map<String,String> params,final Callback<T> callback){
  HttpUrl httpUrl=HttpUrl.parse(url);
  if (params != null) {
    HttpUrl.Builder builder=httpUrl.newBuilder();
    for (    Map.Entry<String,String> entry : params.entrySet()) {
      builder.addQueryParameter(entry.getKey(),entry.getValue());
    }
    httpUrl=builder.build();
  }
  final Request request=new Request.Builder().url(httpUrl).get().build();
  okHttpClient.newCall(request).enqueue(new okhttp3.Callback(){
    @Override public void onFailure(    Call call,    IOException e){
      if (callback != null) {
        callback.onFailure(-1,e.getMessage());
      }
    }
    @Override public void onResponse(    Call call,    Response response) throws IOException {
      handleResponse(url,call,response,callback);
    }
  }
);
}
