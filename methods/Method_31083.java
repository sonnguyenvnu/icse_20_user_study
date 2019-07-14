public static float getFloatFromAttrRes(@AttrRes int attrRes,float defaultValue,@NonNull Context context){
  TypedArray a=context.obtainStyledAttributes(new int[]{attrRes});
  try {
    return a.getFloat(0,defaultValue);
  }
  finally {
    a.recycle();
  }
}
