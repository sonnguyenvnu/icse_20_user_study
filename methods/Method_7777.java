@Override public boolean isEnabled(RecyclerView.ViewHolder holder){
  int viewType=holder.getItemViewType();
  if (viewType == 6) {
    return !(LocationController.getInstance(currentAccount).getSharingLocationInfo(dialogId) == null && gpsLocation == null);
  }
  return viewType == 1 || viewType == 3 || viewType == 7;
}
