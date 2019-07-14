protected void onOpenTypeHierarchy(){
  if (currentPage instanceof FocusedTypeGettable) {
    FocusedTypeGettable ftg=(FocusedTypeGettable)currentPage;
    openTypeHierarchyController.show(getCollectionOfFutureIndexes(),ftg.getEntry(),ftg.getFocusedTypeName(),uri -> openURI(uri));
  }
}
