public void close(){
  if (parentActivity == null || visibleDialog != null) {
    return;
  }
  AndroidUtilities.cancelRunOnUIThread(showSheetRunnable);
  showProgress=1.0f;
  lastUpdateTime=System.currentTimeMillis();
  containerView.invalidate();
  try {
    if (visibleDialog != null) {
      visibleDialog.dismiss();
      visibleDialog=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  currentDocument=null;
  currentStickerSet=null;
  delegate=null;
  isVisible=false;
}
