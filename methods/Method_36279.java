@Override public void loadImage(int requestId,Uri uri,final Callback callback){
  ImageRequest request=ImageRequest.fromUri(uri);
  final File localCache=getCacheFile(request);
  if (localCache.exists()) {
    mExecutorSupplier.forLocalStorageRead().execute(new Runnable(){
      @Override public void run(){
        callback.onCacheHit(ImageInfoExtractor.getImageType(localCache),localCache);
        callback.onSuccess(localCache);
      }
    }
);
  }
 else {
    callback.onStart();
    callback.onProgress(0);
    ImagePipeline pipeline=Fresco.getImagePipeline();
    DataSource<CloseableReference<PooledByteBuffer>> source=pipeline.fetchEncodedImage(request,true);
    source.subscribe(new ImageDownloadSubscriber(mAppContext){
      @Override protected void onProgress(      int progress){
        callback.onProgress(progress);
      }
      @Override protected void onSuccess(      final File image){
        callback.onFinish();
        callback.onCacheMiss(ImageInfoExtractor.getImageType(image),image);
        callback.onSuccess(image);
      }
      @Override protected void onFail(      final Throwable t){
        t.printStackTrace();
        callback.onFail((Exception)t);
      }
    }
,mExecutorSupplier.forBackgroundTasks());
    closeSource(requestId);
    saveSource(requestId,source);
  }
}
