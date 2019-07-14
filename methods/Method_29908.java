private void updateOptionsMenu(){
  if (mCopyTextMenuItem == null && mDeleteMenuItem == null) {
    return;
  }
  Broadcast broadcast=mResource.getEffectiveBroadcast();
  boolean hasBroadcast=broadcast != null;
  mCopyTextMenuItem.setEnabled(hasBroadcast);
  boolean canDelete=hasBroadcast && broadcast.isAuthorOneself();
  mDeleteMenuItem.setVisible(canDelete);
}
