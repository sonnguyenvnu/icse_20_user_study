public void onResp(int error_code){
  if (mCallback == null) {
    return;
  }
  if (error_code == 0) {
    mCallback.onSuccess();
  }
 else   if (error_code == -1) {
    mCallback.onError(ERROR_PAY);
  }
 else   if (error_code == -2) {
    mCallback.onCancel();
  }
  mCallback=null;
}
