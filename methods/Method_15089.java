/** 
 * ???????
 * @param callBack
 */
@SuppressWarnings("unchecked") @Override public void setList(AdapterCallBack<BA> callBack){
  super.setList(callBack);
  boolean empty=adapter == null || adapter.isEmpty();
  Log.d(TAG,"setList  adapter empty = " + empty);
  lvBaseList.showFooter(!empty);
  if (adapter != null && adapter instanceof zuo.biao.library.base.BaseAdapter) {
    ((zuo.biao.library.base.BaseAdapter<T>)adapter).setOnReachViewBorderListener(empty || lvBaseList.isFooterShowing() == false ? null : new OnReachViewBorderListener(){
      @Override public void onReach(      int type,      View v){
        if (type == TYPE_BOTTOM) {
          lvBaseList.onLoadMore();
        }
      }
    }
);
  }
}
