protected String getQueueKey(Task task){
  return QUEUE_PREFIX + task.getUUID();
}
