private void onFailureInternal(String id,DataSource<T> dataSource,Throwable throwable,boolean isFinished){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("AbstractDraweeController#onFailureInternal");
  }
  if (!isExpectedDataSource(id,dataSource)) {
    logMessageAndFailure("ignore_old_datasource @ onFailure",throwable);
    dataSource.close();
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
    return;
  }
  mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_FAILURE : Event.ON_DATASOURCE_FAILURE_INT);
  if (isFinished) {
    logMessageAndFailure("final_failed @ onFailure",throwable);
    mDataSource=null;
    mHasFetchFailed=true;
    if (mRetainImageOnFailure && mDrawable != null) {
      mSettableDraweeHierarchy.setImage(mDrawable,1f,true);
    }
 else     if (shouldRetryOnTap()) {
      mSettableDraweeHierarchy.setRetry(throwable);
    }
 else {
      mSettableDraweeHierarchy.setFailure(throwable);
    }
    getControllerListener().onFailure(mId,throwable);
  }
 else {
    logMessageAndFailure("intermediate_failed @ onFailure",throwable);
    getControllerListener().onIntermediateImageFailed(mId,throwable);
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
