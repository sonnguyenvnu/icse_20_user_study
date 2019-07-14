public void setItemColor(int item,int color,int icon){
  if (item < 0 || item >= itemViews.size()) {
    return;
  }
  BottomSheetCell cell=itemViews.get(item);
  cell.textView.setTextColor(color);
  cell.imageView.setColorFilter(new PorterDuffColorFilter(icon,PorterDuff.Mode.MULTIPLY));
}
