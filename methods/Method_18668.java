public void unmount(){
  mDraweeHolder.onDetach();
  setDrawable(mNoOpDrawable);
}
