@Override public void onItemCollectionListItemWriteStarted(int requestCode,int position){
  mAdapter.notifyItemCollectionListItemChanged(position);
}
