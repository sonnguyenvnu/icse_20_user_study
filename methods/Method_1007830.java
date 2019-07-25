@Override public void monitor(final Task<?> task){
  checkNotNull(task);
synchronized (lock) {
    monitored.add(task);
  }
  task.addListener(() -> {
synchronized (lock) {
      monitored.remove(task);
    }
  }
,MoreExecutors.directExecutor());
}
