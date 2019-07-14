public static int resolveColor(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  int color=0;
  if (fallback != 0) {
    color=ContextCompat.getColor(context,fallback);
  }
  try {
    return a.getColor(0,color);
  }
  finally {
    a.recycle();
  }
}
