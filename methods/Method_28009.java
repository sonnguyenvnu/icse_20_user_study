@Override public void onNotifyAdapter(){
  hideProgress();
  adapter.notifyDataSetChanged();
}
