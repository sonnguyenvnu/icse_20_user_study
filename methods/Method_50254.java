@Override protected void setTheme(){
  Drawable closeDrawable=ThemeUtils.resolveDrawable(this,R.attr.gallery_toolbar_close_image,R.drawable.gallery_default_toolbar_close_image);
  int closeColor=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_close_color,R.color.gallery_default_toolbar_widget_color);
  closeDrawable.setColorFilter(closeColor,PorterDuff.Mode.SRC_ATOP);
  mToolbar.setNavigationIcon(closeDrawable);
  int overButtonBg=ThemeUtils.resolveDrawableRes(this,R.attr.gallery_toolbar_over_button_bg);
  if (overButtonBg != 0) {
    mTvOverAction.setBackgroundResource(overButtonBg);
  }
 else {
    OsCompat.setBackgroundDrawableCompat(mTvOverAction,createDefaultOverButtonBgDrawable());
  }
  float overTextSize=ThemeUtils.resolveDimen(this,R.attr.gallery_toolbar_over_button_text_size,R.dimen.gallery_default_toolbar_over_button_text_size);
  mTvOverAction.setTextSize(TypedValue.COMPLEX_UNIT_PX,overTextSize);
  int overTextColor=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_over_button_text_color,R.color.gallery_default_toolbar_over_button_text_color);
  mTvOverAction.setTextColor(overTextColor);
  float titleTextSize=ThemeUtils.resolveDimen(this,R.attr.gallery_toolbar_text_size,R.dimen.gallery_default_toolbar_text_size);
  mTvToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,titleTextSize);
  int titleTextColor=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_text_color,R.color.gallery_default_toolbar_text_color);
  mTvToolbarTitle.setTextColor(titleTextColor);
  int gravity=ThemeUtils.resolveInteger(this,R.attr.gallery_toolbar_text_gravity,R.integer.gallery_default_toolbar_text_gravity);
  mTvToolbarTitle.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,Toolbar.LayoutParams.WRAP_CONTENT,gravity));
  int toolbarBg=ThemeUtils.resolveColor(this,R.attr.gallery_toolbar_bg,R.color.gallery_default_color_toolbar_bg);
  mToolbar.setBackgroundColor(toolbarBg);
  int toolbarHeight=(int)ThemeUtils.resolveDimen(this,R.attr.gallery_toolbar_height,R.dimen.gallery_default_toolbar_height);
  mToolbar.setMinimumHeight(toolbarHeight);
  int statusBarColor=ThemeUtils.resolveColor(this,R.attr.gallery_color_statusbar,R.color.gallery_default_color_statusbar);
  ThemeUtils.setStatusBarColor(statusBarColor,getWindow());
  int dividerHeight=(int)ThemeUtils.resolveDimen(this,R.attr.gallery_toolbar_divider_height,R.dimen.gallery_default_toolbar_divider_height);
  int dividerBottomMargin=(int)ThemeUtils.resolveDimen(this,R.attr.gallery_toolbar_bottom_margin,R.dimen.gallery_default_toolbar_bottom_margin);
  LayoutParams dividerLP=new LayoutParams(LayoutParams.MATCH_PARENT,dividerHeight);
  dividerLP.bottomMargin=dividerBottomMargin;
  mToolbarDivider.setLayoutParams(dividerLP);
  Drawable dividerDrawable=ThemeUtils.resolveDrawable(this,R.attr.gallery_toolbar_divider_bg,R.color.gallery_default_toolbar_divider_bg);
  OsCompat.setBackgroundDrawableCompat(mToolbarDivider,dividerDrawable);
  setSupportActionBar(mToolbar);
}
