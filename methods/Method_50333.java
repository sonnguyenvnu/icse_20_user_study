public static boolean resolveBoolean(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  boolean defValue=false;
  if (fallback != 0) {
    defValue=context.getResources().getBoolean(fallback);
  }
  try {
    return a.getBoolean(0,defValue);
  }
  finally {
    a.recycle();
  }
}
