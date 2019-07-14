public static ColorFilter getShareColorFilter(int color,boolean selected){
  if (selected) {
    if (currentShareSelectedColorFilter == null || currentShareSelectedColorFilterColor != color) {
      currentShareSelectedColorFilterColor=color;
      currentShareSelectedColorFilter=new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY);
    }
    return currentShareSelectedColorFilter;
  }
 else {
    if (currentShareColorFilter == null || currentShareColorFilterColor != color) {
      currentShareColorFilterColor=color;
      currentShareColorFilter=new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY);
    }
    return currentShareColorFilter;
  }
}
