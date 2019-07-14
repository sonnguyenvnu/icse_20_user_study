@Override public ModelLoader<String,Drawable> build(MultiModelLoaderFactory multiFactory){
  return new ApkImageModelLoader(packageManager);
}
