@Override public void postUnBindView(BaseCell cell){
  recycleView();
  getContext().unregisterReceiver(mScreenBroadcastReceiver);
}
