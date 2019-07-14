void setTapToAutofocusListener(Camera.AutoFocusCallback callback){
  if (this.mFocus != FOCUS_TAP) {
    throw new IllegalArgumentException("Please set the camera to FOCUS_TAP.");
  }
  this.mAutofocusCallback=callback;
}
