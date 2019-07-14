@DoNotStrip private static void setBitmapSize(@Nullable BitmapFactory.Options options,int width,int height){
  if (options != null) {
    options.outWidth=width;
    options.outHeight=height;
  }
}
