public static void onRequestNewServerIpAndPort(final int second,final int currentAccount){
  Utilities.stageQueue.postRunnable(() -> {
    if (currentTask != null || second == 0 && Math.abs(lastDnsRequestTime - System.currentTimeMillis()) < 10000 || !ApplicationLoader.isNetworkOnline()) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("don't start task, current task = " + currentTask + " next task = " + second + " time diff = " + Math.abs(lastDnsRequestTime - System.currentTimeMillis()) + " network = " + ApplicationLoader.isNetworkOnline());
      }
      return;
    }
    lastDnsRequestTime=System.currentTimeMillis();
    if (second == 2) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("start azure dns task");
      }
      AzureLoadTask task=new AzureLoadTask(currentAccount);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else     if (second == 1) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("start dns txt task");
      }
      DnsTxtLoadTask task=new DnsTxtLoadTask(currentAccount);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("start firebase task");
      }
      FirebaseTask task=new FirebaseTask(currentAccount);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
  }
);
}
