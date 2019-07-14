public static GlideCustomImageLoader with(Context context,OkHttpClient okHttpClient,Class<? extends GlideModel> glideModel){
  return new GlideCustomImageLoader(context,okHttpClient,glideModel);
}
