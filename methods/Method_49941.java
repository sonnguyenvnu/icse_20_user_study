private void onNetworkUnavailable(int serviceId,int transactionType){
  Timber.v("onNetworkUnavailable: sid=" + serviceId + ", type=" + transactionType);
  int toastType=TOAST_NONE;
  if (transactionType == Transaction.RETRIEVE_TRANSACTION) {
    toastType=TOAST_DOWNLOAD_LATER;
  }
 else   if (transactionType == Transaction.SEND_TRANSACTION) {
    toastType=TOAST_MSG_QUEUED;
  }
  if (toastType != TOAST_NONE) {
    mToastHandler.sendEmptyMessage(toastType);
  }
  stopSelf(serviceId);
}
