private void apply(final Paint paint){
  final Typeface oldTypeface=paint.getTypeface();
  final int oldStyle=oldTypeface != null ? oldTypeface.getStyle() : 0;
  final int fakeStyle=oldStyle & ~mTypeface.getStyle();
  if ((fakeStyle & Typeface.BOLD) != 0) {
    paint.setFakeBoldText(true);
  }
  if ((fakeStyle & Typeface.ITALIC) != 0) {
    paint.setTextSkewX(-0.25f);
  }
  paint.setTypeface(mTypeface);
}
