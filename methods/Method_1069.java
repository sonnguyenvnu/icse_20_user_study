private void onNewResultInternal(String id,DataSource<T> dataSource,@Nullable T image,float progress,boolean isFinished,boolean wasImmediate,boolean deliverTempResult){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("AbstractDraweeController#onNewResultInternal");
    }
    if (!isExpectedDataSource(id,dataSource)) {
      logMessageAndImage("ignore_old_datasource @ onNewResult",image);
      releaseImage(image);
      dataSource.close();
      return;
    }
    mEventTracker.recordEvent(isFinished ? Event.ON_DATASOURCE_RESULT : Event.ON_DATASOURCE_RESULT_INT);
    Drawable drawable;
    try {
      drawable=createDrawable(image);
    }
 catch (    Exception exception) {
      logMessageAndImage("drawable_failed @ onNewResult",image);
      releaseImage(image);
      onFailureInternal(id,dataSource,exception,isFinished);
      return;
    }
    T previousImage=mFetchedImage;
    Drawable previousDrawable=mDrawable;
    mFetchedImage=image;
    mDrawable=drawable;
    try {
      if (isFinished) {
        logMessageAndImage("set_final_result @ onNewResult",image);
        mDataSource=null;
        mSettableDraweeHierarchy.setImage(drawable,1f,wasImmediate);
        getControllerListener().onFinalImageSet(id,getImageInfo(image),getAnimatable());
      }
 else       if (deliverTempResult) {
        logMessageAndImage("set_temporary_result @ onNewResult",image);
        mSettableDraweeHierarchy.setImage(drawable,1f,wasImmediate);
        getControllerListener().onFinalImageSet(id,getImageInfo(image),getAnimatable());
      }
 else {
        logMessageAndImage("set_intermediate_result @ onNewResult",image);
        mSettableDraweeHierarchy.setImage(drawable,progress,wasImmediate);
        getControllerListener().onIntermediateImageSet(id,getImageInfo(image));
      }
    }
  finally {
      if (previousDrawable != null && previousDrawable != drawable) {
        releaseDrawable(previousDrawable);
      }
      if (previousImage != null && previousImage != image) {
        logMessageAndImage("release_previous_result @ onNewResult",previousImage);
        releaseImage(previousImage);
      }
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
