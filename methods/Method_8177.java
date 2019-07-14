public void needHideProgress(){
  if (progressDialog == null) {
    return;
  }
  try {
    progressDialog.dismiss();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  progressDialog=null;
}
