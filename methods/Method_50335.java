public static int resolveInteger(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  int defValue=0;
  if (fallback != 0) {
    defValue=context.getResources().getInteger(fallback);
  }
  try {
    return a.getInteger(0,defValue);
  }
  finally {
    a.recycle();
  }
}
