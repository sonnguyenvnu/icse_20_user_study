@Override public void onBookAdded(String[] path,boolean refreshdrawer){
  utilsHandler.saveToDatabase(new OperationData(UtilsHandler.Operation.BOOKMARKS,path[0],path[1]));
  if (refreshdrawer)   drawer.refreshDrawer();
}
