public void cancelLoadHttpFile(String url){
  HttpFileTask task=httpFileLoadTasksByKeys.get(url);
  if (task != null) {
    task.cancel(true);
    httpFileLoadTasksByKeys.remove(url);
    httpFileLoadTasks.remove(task);
  }
  Runnable runnable=retryHttpsTasks.get(url);
  if (runnable != null) {
    AndroidUtilities.cancelRunOnUIThread(runnable);
  }
  runHttpFileLoadTasks(null,0);
}
