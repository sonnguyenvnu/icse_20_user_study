@Override public void setTheme(){
  super.setTheme();
  int checkTint=ThemeUtils.resolveColor(getContext(),R.attr.gallery_checkbox_button_tint_color,R.color.gallery_default_checkbox_button_tint_color);
  CompoundButtonCompat.setButtonTintList(mCbCheck,ColorStateList.valueOf(checkTint));
  int cbTextColor=ThemeUtils.resolveColor(getContext(),R.attr.gallery_checkbox_text_color,R.color.gallery_default_checkbox_text_color);
  mCbCheck.setTextColor(cbTextColor);
  int pageColor=ThemeUtils.resolveColor(getContext(),R.attr.gallery_page_bg,R.color.gallery_default_page_bg);
  mRlRootView.setBackgroundColor(pageColor);
}
