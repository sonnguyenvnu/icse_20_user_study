@OnLoadStyle static void onLoadStyle(ComponentContext c,Output<Boolean> scrollbarEnabled){
  final TypedArray a=c.obtainStyledAttributes(R.styleable.HorizontalScroll,0);
  for (int i=0, size=a.getIndexCount(); i < size; i++) {
    final int attr=a.getIndex(i);
    if (attr == R.styleable.HorizontalScroll_android_scrollbars) {
      scrollbarEnabled.set(a.getInt(attr,0) != 0);
    }
  }
  a.recycle();
}
