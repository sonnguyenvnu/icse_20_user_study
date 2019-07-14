@Nullable public Drawable resolveDrawableAttr(@AttrRes int attrResId,@DrawableRes int defResId){
  if (attrResId == 0) {
    return null;
  }
  mAttrs[0]=attrResId;
  TypedArray a=mTheme.obtainStyledAttributes(mAttrs);
  try {
    return resolveDrawableRes(a.getResourceId(0,defResId));
  }
  finally {
    a.recycle();
  }
}
