public void setTopImage(int resId){
  if (resId == 0) {
    textView.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
  }
 else {
    Drawable drawable=getContext().getResources().getDrawable(resId).mutate();
    if (drawable != null) {
      drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_emptyListPlaceholder),PorterDuff.Mode.MULTIPLY));
    }
    textView.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
    textView.setCompoundDrawablePadding(AndroidUtilities.dp(1));
  }
}
