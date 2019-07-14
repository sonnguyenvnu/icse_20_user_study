public static int resolveDrawableRes(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  try {
    return a.getResourceId(0,fallback);
  }
  finally {
    a.recycle();
  }
}
