private void runHttpFileLoadTasks(final HttpFileTask oldTask,final int reason){
  AndroidUtilities.runOnUIThread(() -> {
    if (oldTask != null) {
      currentHttpFileLoadTasksCount--;
    }
    if (oldTask != null) {
      if (reason == 1) {
        if (oldTask.canRetry) {
          final HttpFileTask newTask=new HttpFileTask(oldTask.url,oldTask.tempFile,oldTask.ext,oldTask.currentAccount);
          Runnable runnable=() -> {
            httpFileLoadTasks.add(newTask);
            runHttpFileLoadTasks(null,0);
          }
;
          retryHttpsTasks.put(oldTask.url,runnable);
          AndroidUtilities.runOnUIThread(runnable,1000);
        }
 else {
          httpFileLoadTasksByKeys.remove(oldTask.url);
          NotificationCenter.getInstance(oldTask.currentAccount).postNotificationName(NotificationCenter.httpFileDidFailedLoad,oldTask.url,0);
        }
      }
 else       if (reason == 2) {
        httpFileLoadTasksByKeys.remove(oldTask.url);
        File file=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(oldTask.url) + "." + oldTask.ext);
        String result=oldTask.tempFile.renameTo(file) ? file.toString() : oldTask.tempFile.toString();
        NotificationCenter.getInstance(oldTask.currentAccount).postNotificationName(NotificationCenter.httpFileDidLoad,oldTask.url,result);
      }
    }
    while (currentHttpFileLoadTasksCount < 2 && !httpFileLoadTasks.isEmpty()) {
      HttpFileTask task=httpFileLoadTasks.poll();
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentHttpFileLoadTasksCount++;
    }
  }
);
}
