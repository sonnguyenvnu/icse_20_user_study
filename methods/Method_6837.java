public void didAddedNewTask(final int minDate,final SparseArray<ArrayList<Long>> mids){
  Utilities.stageQueue.postRunnable(() -> {
    if (currentDeletingTaskMids == null && !gettingNewDeleteTask || currentDeletingTaskTime != 0 && minDate < currentDeletingTaskTime) {
      getNewDeleteTask(null,0);
    }
  }
);
  AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didCreatedNewDeleteTask,mids));
}
