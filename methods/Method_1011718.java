@Override public void dispose(MPSProject project){
  TransientModels_ProjectPluginPart.this.myNotifier.projectClosed();
  TransientModels_ProjectPluginPart.this.myNotifier=null;
}
