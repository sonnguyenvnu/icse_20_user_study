private void handleNoData(){
  mLists=(ArrayList<List<AndroidBean>>)maCache.getAsObject(Constants.EVERYDAY_CONTENT);
  if (mLists != null && mLists.size() > 0) {
    saveDate();
    contentData.setValue(mLists);
  }
 else {
    isShowLoading.setValue(false);
    contentData.setValue(null);
  }
}
