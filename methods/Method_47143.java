public void addSearchResult(HybridFileParcelable a,String query){
  if (listView != null) {
    if (!results) {
      LIST_ELEMENTS.clear();
      file_count=0;
      folder_count=0;
    }
    LayoutElementParcelable layoutElementAdded=addTo(a);
    if (!results) {
      reloadListElements(false,false,!IS_LIST);
      getMainActivity().getAppbar().getBottomBar().setPathText("");
      getMainActivity().getAppbar().getBottomBar().setFullPathText(getString(R.string.searching,query));
      results=true;
    }
 else {
      adapter.addItem(layoutElementAdded);
    }
    stopAnimation();
  }
}
