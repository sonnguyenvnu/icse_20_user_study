public int resolveDimenSizeAttr(@AttrRes int attrResId,@DimenRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getDimensionPixelSize(0,resolveDimenSizeRes(defResId));
  }
  finally {
    a.recycle();
  }
}
