@OnLoadStyle static void onLoadStyle(ComponentContext c,Output<Drawable> drawable,Output<ScaleType> scaleType){
  final TypedArray a=c.obtainStyledAttributes(R.styleable.Image,0);
  for (int i=0, size=a.getIndexCount(); i < size; i++) {
    final int attr=a.getIndex(i);
    if (attr == R.styleable.Image_android_src) {
      drawable.set(c.getAndroidContext().getResources().getDrawable(a.getResourceId(attr,0)));
    }
 else     if (attr == R.styleable.Image_android_scaleType) {
      scaleType.set(SCALE_TYPE[a.getInteger(attr,-1)]);
    }
  }
  a.recycle();
}
