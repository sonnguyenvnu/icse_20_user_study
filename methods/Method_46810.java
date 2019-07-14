@Override public void delete(String title,String path){
  utilsHandler.removeFromDatabase(new OperationData(UtilsHandler.Operation.BOOKMARKS,title,path));
  drawer.refreshDrawer();
}
