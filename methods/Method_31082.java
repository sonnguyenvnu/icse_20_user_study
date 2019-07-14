@Nullable public static Drawable getDrawableFromAttrRes(@AttrRes int attrRes,@NonNull Context context){
  TypedArray a=context.obtainStyledAttributes(new int[]{attrRes});
  try {
    int resId=a.getResourceId(0,0);
    if (resId != 0) {
      return AppCompatResources.getDrawable(context,resId);
    }
    return null;
  }
  finally {
    a.recycle();
  }
}
