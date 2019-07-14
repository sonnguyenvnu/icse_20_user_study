public String resolveStringAttr(@AttrRes int attrResId,@StringRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    String result=a.getString(0);
    if (result == null) {
      result=resolveStringRes(defResId);
    }
    return result;
  }
  finally {
    a.recycle();
  }
}
