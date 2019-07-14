private boolean checkDeletingTask(boolean runnable){
  int currentServerTime=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
  if (currentDeletingTaskMids != null && (runnable || currentDeletingTaskTime != 0 && currentDeletingTaskTime <= currentServerTime)) {
    currentDeletingTaskTime=0;
    if (currentDeleteTaskRunnable != null && !runnable) {
      Utilities.stageQueue.cancelRunnable(currentDeleteTaskRunnable);
    }
    currentDeleteTaskRunnable=null;
    final ArrayList<Integer> mids=new ArrayList<>(currentDeletingTaskMids);
    AndroidUtilities.runOnUIThread(() -> {
      if (!mids.isEmpty() && mids.get(0) > 0) {
        MessagesStorage.getInstance(currentAccount).emptyMessagesMedia(mids);
      }
 else {
        deleteMessages(mids,null,null,0,false);
      }
      Utilities.stageQueue.postRunnable(() -> {
        getNewDeleteTask(mids,currentDeletingTaskChannelId);
        currentDeletingTaskTime=0;
        currentDeletingTaskMids=null;
      }
);
    }
);
    return true;
  }
  return false;
}
