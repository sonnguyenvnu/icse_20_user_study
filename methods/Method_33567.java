/** 
 * ??? ??????activity??????? ?????
 */
@Override protected void loadData(){
  if (!isPrepared || !mIsVisible) {
    return;
  }
  String oneData=SPUtils.getString("one_data","2016-11-26");
  if (!oneData.equals(TimeUtil.getData()) && !mIsLoading) {
    showLoading();
    postDelayLoad();
  }
 else {
    if (mIsLoading || !isFirst) {
      return;
    }
    showLoading();
    if (mHotMovieBean == null && !mIsLoading) {
      postDelayLoad();
    }
 else {
      bindingView.listOne.postDelayed(new Runnable(){
        @Override public void run(){
synchronized (this) {
            setAdapter(mHotMovieBean);
            showContentView();
          }
        }
      }
,300);
    }
  }
}
