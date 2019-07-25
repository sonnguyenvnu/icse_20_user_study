void process(){
  if (!(isProcessingAllowed())) {
    return;
  }
  ArrayList<T> tasks=new ArrayList();
  myTasks.drainTo(tasks);
  if (tasks.isEmpty()) {
    return;
  }
  processTask(tasks);
}
