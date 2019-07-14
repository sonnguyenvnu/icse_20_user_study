private void init(){
  mInstrumentation=new Instrumentation(this);
  if (mConfig.instrumentationEnabled) {
    mListener=new BaseControllerListener<Object>(){
      @Override public void onSubmit(      String id,      Object callerContext){
        mInstrumentation.onStart();
      }
      @Override public void onFinalImageSet(      String id,      @Nullable Object imageInfo,      @Nullable Animatable animatable){
        mInstrumentation.onSuccess();
      }
      @Override public void onFailure(      String id,      Throwable throwable){
        mInstrumentation.onFailure();
      }
      @Override public void onRelease(      String id){
        mInstrumentation.onCancellation();
      }
    }
;
  }
  DraweeUtil.setBgColor(this,mConfig);
}
