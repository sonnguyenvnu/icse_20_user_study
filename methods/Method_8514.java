@Override public void dismissInternal(){
  if (containerView != null) {
    containerView.setVisibility(View.INVISIBLE);
  }
  super.dismissInternal();
}
