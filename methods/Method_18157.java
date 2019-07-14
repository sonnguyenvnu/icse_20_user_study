private void setPaddingFromBackground(Drawable drawable){
  if (drawable != null) {
    final Rect backgroundPadding=new Rect();
    if (getDrawablePadding(drawable,backgroundPadding)) {
      paddingPx(LEFT,backgroundPadding.left);
      paddingPx(TOP,backgroundPadding.top);
      paddingPx(RIGHT,backgroundPadding.right);
      paddingPx(BOTTOM,backgroundPadding.bottom);
    }
  }
}
