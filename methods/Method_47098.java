@Override public void onLoadFinished(Loader<AppListLoader.AppsDataPair> loader,AppListLoader.AppsDataPair data){
  adapter.setData(data.first);
  modelProvider.setItemList(data.second);
  if (isResumed()) {
    setListShown(true);
  }
 else {
    setListShownNoAnimation(true);
  }
  if (vl != null)   vl.setSelectionFromTop(index,top);
}
