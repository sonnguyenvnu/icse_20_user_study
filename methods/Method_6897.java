public void markDialogAsReadNow(final long dialogId){
  Utilities.stageQueue.postRunnable(() -> {
    ReadTask currentReadTask=readTasksMap.get(dialogId);
    if (currentReadTask == null) {
      return;
    }
    completeReadTask(currentReadTask);
    readTasks.remove(currentReadTask);
    readTasksMap.remove(dialogId);
  }
);
}
