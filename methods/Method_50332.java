public static String resolveString(Context context,@AttrRes int attr,int fallback){
  TypedArray a=context.getTheme().obtainStyledAttributes(new int[]{attr});
  String value;
  try {
    String s=a.getString(0);
    if (TextUtils.isEmpty(s)) {
      s=context.getString(fallback);
    }
    value=s;
  }
  finally {
    a.recycle();
  }
  return value;
}
