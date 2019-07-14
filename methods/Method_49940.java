private void launchTransaction(int serviceId,TransactionBundle txnBundle,boolean noNetwork){
  if (noNetwork) {
    Timber.w("launchTransaction: no network error!");
    onNetworkUnavailable(serviceId,txnBundle.getTransactionType());
    return;
  }
  Message msg=mServiceHandler.obtainMessage(EVENT_TRANSACTION_REQUEST);
  msg.arg1=serviceId;
  msg.obj=txnBundle;
  Timber.v("launchTransaction: sending message " + msg);
  mServiceHandler.sendMessage(msg);
}
