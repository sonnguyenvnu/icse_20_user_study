synchronized public boolean isAllowedByUser(){
  while (sMutexLock) {
    try {
      wait();
    }
 catch (    InterruptedException e) {
    }
  }
  sMutexLock=true;
  mContext.registerReceiver(mBroadcastReceiver,new IntentFilter(RATE_LIMIT_CONFIRMED_ACTION));
  mAnswer=NO_ANSWER;
  try {
    Intent intent=new Intent(RATE_LIMIT_SURPASSED_ACTION);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mContext.startActivity(intent);
    return waitForAnswer() == ANSWER_YES;
  }
  finally {
    mContext.unregisterReceiver(mBroadcastReceiver);
    sMutexLock=false;
    notifyAll();
  }
}
