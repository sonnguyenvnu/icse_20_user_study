private void init(){
  if (getAutoLinkMask() != 0) {
    throw new IllegalStateException("Don't set android:autoLink");
  }
  ViewUtils.setTextViewLinkClickable(this);
}
