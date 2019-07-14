private void notifyErrorListener(@NonNull final Exception e){
  CameraKitError error=new CameraKitError(e);
  mEventDispatcher.dispatch(error);
}
