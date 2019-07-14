public void onBackgroundJobExited(final BackgroundJob task){
  mHandler.post(() -> {
    mBackgroundTasks.remove(task);
    updateNotification();
  }
);
}
