public void invalidate(@ColorInt int accentColor){
  titleColor=PreferenceUtils.getStatusColor(accentColor);
  notifyChanged();
}
