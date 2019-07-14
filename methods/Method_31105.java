public static void setBackgroundPreservingPadding(@NonNull View view,@Nullable Drawable background){
  int savedPaddingStart=ViewCompat.getPaddingStart(view);
  int savedPaddingEnd=ViewCompat.getPaddingEnd(view);
  int savedPaddingTop=view.getPaddingTop();
  int savedPaddingBottom=view.getPaddingBottom();
  view.setBackground(background);
  ViewCompat.setPaddingRelative(view,savedPaddingStart,savedPaddingTop,savedPaddingEnd,savedPaddingBottom);
}
