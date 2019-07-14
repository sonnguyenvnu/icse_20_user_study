@Nullable public String[] resolveStringArrayAttr(@AttrRes int attrResId,@ArrayRes int defResId){
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return resolveStringArrayRes(a.getResourceId(0,defResId));
  }
  finally {
    a.recycle();
  }
}
