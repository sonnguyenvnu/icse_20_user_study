private void selectItem(int position){
  if (position < 0 || mNaviAdapter.getData().size() < position) {
    return;
  }
  mNaviAdapter.getData().get(currentPosition).setSelected(false);
  mNaviAdapter.notifyItemChanged(currentPosition);
  currentPosition=position;
  mNaviAdapter.getData().get(position).setSelected(true);
  mNaviAdapter.notifyItemChanged(position);
}
