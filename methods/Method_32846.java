@Override public boolean readAltKey(){
  return (mActivity.mExtraKeysView != null && mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.ALT));
}
