@Override public void onItemClick(int position){
  if (mCallback != null) {
    mCallback.onPlayListSelected(mAdapter.getItem(position));
  }
  dismiss();
}
