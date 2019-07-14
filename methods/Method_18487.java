final int resolveResIdAttr(@AttrRes int attrResId,int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getResourceId(0,defResId);
  }
  finally {
    a.recycle();
  }
}
