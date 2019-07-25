synchronized void stop(){
  stopped=true;
  if (useAutoFocus) {
    cancelOutstandingTask();
    try {
      camera.cancelAutoFocus();
    }
 catch (    RuntimeException re) {
      Log.w(TAG,"Unexpected exception while cancelling focusing",re);
    }
  }
}
