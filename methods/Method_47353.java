@Override public ModelLoader<String,Bitmap> build(MultiModelLoaderFactory multiFactory){
  return new CloudIconModelLoader(context);
}
