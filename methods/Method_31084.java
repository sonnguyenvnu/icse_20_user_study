@AnyRes public static int getResIdFromAttrRes(@AttrRes int attrRes,int defaultValue,@NonNull Context context){
  TypedArray a=context.obtainStyledAttributes(new int[]{attrRes});
  try {
    return a.getResourceId(0,defaultValue);
  }
  finally {
    a.recycle();
  }
}
