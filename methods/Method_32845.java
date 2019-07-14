@Override public boolean readControlKey(){
  return (mActivity.mExtraKeysView != null && mActivity.mExtraKeysView.readSpecialButton(ExtraKeysView.SpecialButton.CTRL)) || mVirtualControlKeyDown;
}
