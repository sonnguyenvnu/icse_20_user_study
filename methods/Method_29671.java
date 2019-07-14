private void resetFocus(final boolean success,final Camera camera){
  mHandler.removeCallbacksAndMessages(null);
  mHandler.postDelayed(new Runnable(){
    @Override public void run(){
synchronized (mCameraLock) {
        if (mCamera != null) {
          mCamera.cancelAutoFocus();
          Camera.Parameters parameters=getCameraParameters();
          if (parameters == null)           return;
          if (parameters.getFocusMode() != Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            parameters.setFocusAreas(null);
            parameters.setMeteringAreas(null);
            mCamera.setParameters(parameters);
          }
          if (mAutofocusCallback != null) {
            mAutofocusCallback.onAutoFocus(success,mCamera);
          }
        }
      }
    }
  }
,DELAY_MILLIS_BEFORE_RESETTING_FOCUS);
}
