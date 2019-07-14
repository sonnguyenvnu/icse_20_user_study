protected void onSearchFieldVisibilityChanged(boolean visible){
  isSearchFieldVisible=visible;
  if (titleTextView != null) {
    titleTextView.setVisibility(visible ? INVISIBLE : VISIBLE);
  }
  if (subtitleTextView != null && !TextUtils.isEmpty(subtitleTextView.getText())) {
    subtitleTextView.setVisibility(visible ? INVISIBLE : VISIBLE);
  }
  Drawable drawable=backButtonImageView.getDrawable();
  if (drawable instanceof MenuDrawable) {
    MenuDrawable menuDrawable=(MenuDrawable)drawable;
    menuDrawable.setRotateToBack(true);
    menuDrawable.setRotation(visible ? 1 : 0,true);
  }
}
