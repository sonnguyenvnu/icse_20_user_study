/** 
 * ????
 */
public void onLoadMore(){
  if (isSucceed == false && page <= HttpManager.PAGE_NUM_0) {
    Log.w(TAG,"onLoadMore  isSucceed == false && page <= HttpManager.PAGE_NUM_0 >> return;");
    return;
  }
  loadData(page + (isSucceed ? 1 : 0));
}
