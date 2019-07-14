private void notifyErrorListener(@NonNull final String details){
  CameraKitError error=new CameraKitError();
  error.setMessage(details);
  mEventDispatcher.dispatch(error);
}
