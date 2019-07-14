@Override public void onViewDetachedFromWindow(@Nullable HabitCardViewHolder holder){
  if (listView == null)   return;
  listView.detachCardView(holder);
}
