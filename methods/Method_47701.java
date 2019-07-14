@Override public void onViewAttachedToWindow(@Nullable HabitCardViewHolder holder){
  if (listView == null)   return;
  listView.attachCardView(holder);
}
