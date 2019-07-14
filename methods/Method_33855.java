public boolean isFooter(int position){
  return position < getItemCount() && position >= getItemCount() - mFootViews.size();
}
