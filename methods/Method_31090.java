public static int getWidthExcludingPadding(@NonNull View view){
  return Math.max(0,view.getWidth() - view.getPaddingLeft() - view.getPaddingRight());
}
