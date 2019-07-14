public int resolveIntAttr(@AttrRes int attrResId,@IntegerRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getInt(0,resolveIntRes(defResId));
  }
  finally {
    a.recycle();
  }
}
