@Override public void onItemClick(int position,View v,Commit item){
  CommitPagerActivity.createIntentForOffline(v.getContext(),item);
}
