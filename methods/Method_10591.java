public void handleDecode(Result result){
  inactivityTimer.onActivity();
  RxBeepTool.playBeep(mContext,vibrate);
  String result1=result.getText();
  Log.v("???/??? ????",result1);
  if (mScanerListener == null) {
    RxToast.success(result1);
    initDialogResult(result);
  }
 else {
    mScanerListener.onSuccess("From to Camera",result);
  }
}
