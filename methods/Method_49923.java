public void update(Observable observable){
  try {
    Transaction t=(Transaction)observable;
    Timber.v("[RetryScheduler] update " + observable);
    if ((t instanceof NotificationTransaction) || (t instanceof RetrieveTransaction) || (t instanceof ReadRecTransaction) || (t instanceof SendTransaction)) {
      try {
        TransactionState state=t.getState();
        if (state.getState() == TransactionState.FAILED) {
          Uri uri=state.getContentUri();
          if (uri != null) {
            scheduleRetry(uri);
          }
        }
      }
  finally {
        t.detach(this);
      }
    }
  }
  finally {
    if (isConnected()) {
      setRetryAlarm(mContext);
    }
  }
}
