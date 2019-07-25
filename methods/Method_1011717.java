@Override public void init(MPSProject project){
  TransientModels_ProjectPluginPart.this.myNotifier=new TransientModelsNotification(project);
  ThreadUtils.runInUIThreadNoWait(new Runnable(){
    public void run(){
      TransientModels_ProjectPluginPart.this.myNotifier.projectOpened();
    }
  }
);
}
