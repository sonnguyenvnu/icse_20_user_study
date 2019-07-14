public boolean setTextDetectionListener(final CameraKitEventCallback<CameraKitTextDetect> callback) throws GooglePlayServicesUnavailableException {
  TextRecognizer textRecognizer=new TextRecognizer.Builder(getContext()).build();
  textRecognizer.setProcessor(new TextProcessor(mEventDispatcher,callback));
  int code=GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext().getApplicationContext());
  if (code != ConnectionResult.SUCCESS) {
    throw new GooglePlayServicesUnavailableException();
  }
  if (textRecognizer.isOperational()) {
    mCameraImpl.setTextDetector(textRecognizer);
    return true;
  }
 else {
    return false;
  }
}
