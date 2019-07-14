/** 
 * ????
 */
private void showRecyclerViewData(){
  mEverydayModel.showRecyclerViewData(new RequestImpl(){
    @Override public void loadSuccess(    Object object){
      if (mLists != null) {
        mLists.clear();
      }
      mLists=(ArrayList<List<AndroidBean>>)object;
      if (mLists.size() > 0 && mLists.get(0).size() > 0) {
        maCache.remove(Constants.EVERYDAY_CONTENT);
        maCache.put(Constants.EVERYDAY_CONTENT,mLists);
        saveDate();
        contentData.setValue(mLists);
      }
 else {
        mLists=(ArrayList<List<AndroidBean>>)maCache.getAsObject(Constants.EVERYDAY_CONTENT);
        if (mLists != null && mLists.size() > 0) {
          saveDate();
          contentData.setValue(mLists);
        }
 else {
          ArrayList<String> lastTime=TimeUtil.getLastTime(year,month,day);
          mEverydayModel.setData(lastTime.get(0),lastTime.get(1),lastTime.get(2));
          year=lastTime.get(0);
          month=lastTime.get(1);
          day=lastTime.get(2);
          showRecyclerViewData();
        }
      }
    }
    @Override public void loadFailed(){
      if (mLists != null && mLists.size() > 0) {
        return;
      }
      handleNoData();
    }
    @Override public void addSubscription(    Disposable subscription){
      addDisposable(subscription);
    }
  }
);
}
