public static boolean getBooleanFromAttrRes(@AttrRes int attrRes,boolean defaultValue,@NonNull Context context){
  TypedArray a=context.obtainStyledAttributes(new int[]{attrRes});
  try {
    return a.getBoolean(0,defaultValue);
  }
  finally {
    a.recycle();
  }
}
