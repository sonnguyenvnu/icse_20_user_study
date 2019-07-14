boolean isDisappearing(RecyclerView.ViewHolder holder){
  final InfoRecord record=mLayoutHolderMap.get(holder);
  return record != null && ((record.flags & FLAG_DISAPPEARED) != 0);
}
