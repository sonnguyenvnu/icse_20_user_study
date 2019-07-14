@Override public void changePath(String folder){
  if (folder == null)   folder="";
  if (folder.startsWith("/"))   folder=folder.substring(1);
  boolean addGoBackItem=gobackitem && !isRoot(folder);
  String finalfolder=folder;
  decompressor.changePath(folder,addGoBackItem,data -> {
    elements=data;
    createViews(elements,finalfolder);
    swipeRefreshLayout.setRefreshing(false);
    updateBottomBar();
  }
).execute();
  updateBottomBar();
}
