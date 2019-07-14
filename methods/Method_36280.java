@Override public void prefetch(Uri uri){
  ImagePipeline pipeline=Fresco.getImagePipeline();
  pipeline.prefetchToDiskCache(ImageRequest.fromUri(uri),false);
}
