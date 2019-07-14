@Override public void onItemCollectionListItemWriteFinished(int requestCode,int position){
  mAdapter.notifyItemCollectionListItemChanged(position);
}
