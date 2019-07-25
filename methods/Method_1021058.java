void run(final String url){
  new Thread(new Runnable(){
    @Override public void run(){
      try {
        Request request=new Request.Builder().url(url).build();
        OkHttpClient okHttpClient=new OkHttpClient();
        Response response=okHttpClient.newCall(request).execute();
        String result=response.body().string();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
).start();
}
