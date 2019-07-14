@Override public void onBroadcastInserted(int requestCode,int position,Broadcast insertedBroadcast){
  boolean hasFirstItemView=mList.getLayoutManager().findViewByPosition(0) != null;
  onItemInserted(position,insertedBroadcast);
  if (position == 0 && hasFirstItemView) {
    mList.scrollToPosition(0);
  }
}
