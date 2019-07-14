public static int getHeightExcludingPadding(@NonNull View view){
  return Math.max(0,view.getHeight() - view.getPaddingTop() - view.getPaddingBottom());
}
