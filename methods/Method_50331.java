public static float resolveDimen(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  float size=0;
  if (fallback != 0) {
    size=context.getResources().getDimension(fallback);
  }
  try {
    return a.getDimension(0,size);
  }
  finally {
    a.recycle();
  }
}
