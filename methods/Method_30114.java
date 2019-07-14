private void updateOptionsMenu(){
  if (mSaveMenuItem == null || mShareMenuItem == null) {
    return;
  }
  boolean hasFile=mAdapter.getFile(mViewPager.getCurrentItem()) != null;
  mSaveMenuItem.setEnabled(hasFile);
  mShareMenuItem.setEnabled(hasFile);
}
