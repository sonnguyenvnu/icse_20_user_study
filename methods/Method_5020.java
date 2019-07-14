private void saveActions(){
  if (released) {
    return;
  }
  ArrayList<DownloadAction> actions=new ArrayList<>(downloads.size());
  for (int i=0; i < downloads.size(); i++) {
    actions.addAll(downloads.get(i).actionQueue);
  }
  final DownloadAction[] actionsArray=actions.toArray(new DownloadAction[0]);
  fileIOHandler.post(() -> {
    try {
      actionFile.store(actionsArray);
      logd("Actions persisted.");
    }
 catch (    IOException e) {
      Log.e(TAG,"Persisting actions failed.",e);
    }
  }
);
}
