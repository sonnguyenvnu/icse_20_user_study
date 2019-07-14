@Override public void loadImage(final int requestId,final Uri uri,final Callback callback){
  ImageDownloadTarget target=new ImageDownloadTarget(uri.toString()){
    @Override public void onResourceReady(    @NonNull File resource,    Transition<? super File> transition){
      super.onResourceReady(resource,transition);
      callback.onCacheHit(ImageInfoExtractor.getImageType(resource),resource);
      callback.onSuccess(resource);
    }
    @Override public void onLoadFailed(    final Drawable errorDrawable){
      super.onLoadFailed(errorDrawable);
      callback.onFail(new GlideLoaderException(errorDrawable));
    }
    @Override public void onDownloadStart(){
      callback.onStart();
    }
    @Override public void onProgress(    int progress){
      callback.onProgress(progress);
    }
    @Override public void onDownloadFinish(){
      callback.onFinish();
    }
  }
;
  clearTarget(requestId);
  saveTarget(requestId,target);
  downloadImageInto(uri,target);
}
