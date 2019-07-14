@Override public void onFinishTemporaryDetach(){
  super.onFinishTemporaryDetach();
  mIsAttached=true;
  if (mDraweeStringBuilder != null) {
    mDraweeStringBuilder.onAttachToView(this);
  }
}
