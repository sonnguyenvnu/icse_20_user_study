@NonNull @Override public ModelLoader<CustomImageSizeModel,InputStream> build(@NonNull MultiModelLoaderFactory multiFactory){
  ModelLoader<GlideUrl,InputStream> modelLoader=multiFactory.build(GlideUrl.class,InputStream.class);
  return new CustomImageSizeUrlLoader(modelLoader,modelCache);
}
