@Override public void registerComponents(Context context,Glide glide,Registry registry){
  registry.prepend(String.class,Drawable.class,new ApkImageModelLoaderFactory(context.getPackageManager()));
  registry.prepend(String.class,Bitmap.class,new CloudIconModelFactory(context));
}
