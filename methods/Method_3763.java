void prefetch(long deadlineNs){
  buildTaskList();
  flushTasksWithDeadline(deadlineNs);
}
