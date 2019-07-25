@Override public void destroy(){
  for (  SlaveSyncTask task : slaveSyncTasks.values()) {
    try {
      task.shutdown();
    }
 catch (    Exception e) {
      LOG.error("disposable destroy failed",e);
    }
  }
}
