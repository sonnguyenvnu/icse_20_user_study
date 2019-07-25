public static FSChangesWatcher instance(){
  return ApplicationManager.getApplication().getComponent(FSChangesWatcher.class);
}
