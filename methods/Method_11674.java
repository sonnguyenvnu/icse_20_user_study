private String getQueueNoPriorityKey(Task task){
  return QUEUE_PREFIX + task.getUUID() + NO_PRIORITY_SUFFIX;
}
