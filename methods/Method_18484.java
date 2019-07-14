public int resolveDimenOffsetAttr(@AttrRes int attrResId,@DimenRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return a.getDimensionPixelOffset(0,resolveDimenOffsetRes(defResId));
  }
  finally {
    a.recycle();
  }
}
