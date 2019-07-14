private void checkReadTasks(){
  long time=SystemClock.elapsedRealtime();
  for (int a=0, size=readTasks.size(); a < size; a++) {
    ReadTask task=readTasks.get(a);
    if (task.sendRequestTime > time) {
      continue;
    }
    completeReadTask(task);
    readTasks.remove(a);
    readTasksMap.remove(task.dialogId);
    a--;
    size--;
  }
}
