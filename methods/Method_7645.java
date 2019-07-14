public void onBeginSlide(){
  try {
    if (visibleDialog != null && visibleDialog.isShowing()) {
      visibleDialog.dismiss();
      visibleDialog=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  if (actionBar != null) {
    actionBar.onPause();
  }
}
