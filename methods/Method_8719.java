private void stopProgressTimer(){
  if (progressTimer != null) {
    try {
      progressTimer.cancel();
      progressTimer=null;
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
