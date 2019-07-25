static void rethrottle(Logger logger,String localNodeId,Client client,BulkByScrollTask task,float newRequestsPerSecond,ActionListener<TaskInfo> listener){
  if (task.isWorker()) {
    rethrottleChildTask(logger,localNodeId,task,newRequestsPerSecond,listener);
    return;
  }
  if (task.isLeader()) {
    rethrottleParentTask(logger,localNodeId,client,task,newRequestsPerSecond,listener);
    return;
  }
  throw new IllegalArgumentException("task [" + task.getId() + "] has not yet been initialized to the point where it knows how to " + "rethrottle itself");
}
