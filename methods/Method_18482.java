public boolean resolveBoolAttr(@AttrRes int attrResId,@BoolRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getBoolean(0,resolveBoolRes(defResId));
  }
  finally {
    a.recycle();
  }
}
