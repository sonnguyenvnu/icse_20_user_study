protected void submitRequest(){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("AbstractDraweeController#submitRequest");
  }
  final T closeableImage=getCachedImage();
  if (closeableImage != null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("AbstractDraweeController#submitRequest->cache");
    }
    mDataSource=null;
    mIsRequestSubmitted=true;
    mHasFetchFailed=false;
    mEventTracker.recordEvent(Event.ON_SUBMIT_CACHE_HIT);
    getControllerListener().onSubmit(mId,mCallerContext);
    onImageLoadedFromCacheImmediately(mId,closeableImage);
    onNewResultInternal(mId,mDataSource,closeableImage,1.0f,true,true,true);
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
    return;
  }
  mEventTracker.recordEvent(Event.ON_DATASOURCE_SUBMIT);
  getControllerListener().onSubmit(mId,mCallerContext);
  mSettableDraweeHierarchy.setProgress(0,true);
  mIsRequestSubmitted=true;
  mHasFetchFailed=false;
  mDataSource=getDataSource();
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"controller %x %s: submitRequest: dataSource: %x",System.identityHashCode(this),mId,System.identityHashCode(mDataSource));
  }
  final String id=mId;
  final boolean wasImmediate=mDataSource.hasResult();
  final DataSubscriber<T> dataSubscriber=new BaseDataSubscriber<T>(){
    @Override public void onNewResultImpl(    DataSource<T> dataSource){
      boolean isFinished=dataSource.isFinished();
      boolean hasMultipleResults=dataSource.hasMultipleResults();
      float progress=dataSource.getProgress();
      T image=dataSource.getResult();
      if (image != null) {
        onNewResultInternal(id,dataSource,image,progress,isFinished,wasImmediate,hasMultipleResults);
      }
 else       if (isFinished) {
        onFailureInternal(id,dataSource,new NullPointerException(),true);
      }
    }
    @Override public void onFailureImpl(    DataSource<T> dataSource){
      onFailureInternal(id,dataSource,dataSource.getFailureCause(),true);
    }
    @Override public void onProgressUpdate(    DataSource<T> dataSource){
      boolean isFinished=dataSource.isFinished();
      float progress=dataSource.getProgress();
      onProgressUpdateInternal(id,dataSource,progress,isFinished);
    }
  }
;
  mDataSource.subscribe(dataSubscriber,mUiThreadImmediateExecutor);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
