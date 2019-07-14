@Override public void deleteConnection(final String name,final String path){
  int i=dataUtils.containsServer(new String[]{name,path});
  if (i != -1) {
    dataUtils.removeServer(i);
    AppConfig.runInBackground(() -> {
      utilsHandler.removeFromDatabase(new OperationData(UtilsHandler.Operation.SMB,name,path));
    }
);
    drawer.refreshDrawer();
  }
}
