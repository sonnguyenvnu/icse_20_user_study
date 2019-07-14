public float resolveFloatAttr(@AttrRes int attrResId,@DimenRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getDimension(0,resolveFloatRes(defResId));
  }
  finally {
    a.recycle();
  }
}
