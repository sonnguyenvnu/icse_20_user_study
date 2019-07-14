private void loadActions(){
  fileIOHandler.post(() -> {
    DownloadAction[] loadedActions;
    try {
      loadedActions=actionFile.load();
      logd("Action file is loaded.");
    }
 catch (    Throwable e) {
      Log.e(TAG,"Action file loading failed.",e);
      loadedActions=new DownloadAction[0];
    }
    final DownloadAction[] actions=loadedActions;
    handler.post(() -> {
      if (released) {
        return;
      }
      for (      DownloadAction action : actions) {
        addDownloadForAction(action);
      }
      if (!actionQueue.isEmpty()) {
        while (!actionQueue.isEmpty()) {
          addDownloadForAction(actionQueue.remove());
        }
        saveActions();
      }
      logd("Downloads are created.");
      initialized=true;
      for (      Listener listener : listeners) {
        listener.onInitialized(DownloadManager.this);
      }
      clearStopFlags(STOP_FLAG_DOWNLOAD_MANAGER_NOT_READY);
    }
);
  }
);
}
