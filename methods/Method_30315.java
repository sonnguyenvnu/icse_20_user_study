private void updateOptionsMenu(){
  if (mCollectMenuItem == null && mUncollectMenuItem == null) {
    return;
  }
  boolean showUncollect=mItem.collection != null && (mExtraState == null || getState() == mItem.collection.getState());
  mUncollectMenuItem.setVisible(showUncollect);
}
