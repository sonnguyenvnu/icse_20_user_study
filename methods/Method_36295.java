public static void init(Glide glide,OkHttpClient okHttpClient){
  OkHttpClient.Builder builder;
  if (okHttpClient != null) {
    builder=okHttpClient.newBuilder();
  }
 else {
    builder=new OkHttpClient.Builder();
  }
  builder.addNetworkInterceptor(createInterceptor(new DispatchingProgressListener()));
  glide.getRegistry().replace(GlideUrl.class,InputStream.class,new OkHttpUrlLoader.Factory(builder.build()));
}
