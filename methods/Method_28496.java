public void tintDrawables(@ColorInt int color){
  if (color != -1) {
    this.tintColor=color;
    Drawable[] drawables=getCompoundDrawablesRelative();
    for (    Drawable drawable : drawables) {
      if (drawable == null)       continue;
      ViewHelper.tintDrawable(drawable,color);
    }
  }
}
