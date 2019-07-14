private void runArtworkTasks(boolean complete){
  if (complete) {
    currentArtworkTasksCount--;
  }
  while (currentArtworkTasksCount < 4 && !artworkTasks.isEmpty()) {
    try {
      ArtworkLoadTask task=artworkTasks.poll();
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentArtworkTasksCount++;
    }
 catch (    Throwable ignore) {
      runArtworkTasks(false);
    }
  }
}
