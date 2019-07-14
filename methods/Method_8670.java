private void openSharingLocation(final LocationController.SharingLocationInfo info){
  if (info == null || fragment.getParentActivity() == null) {
    return;
  }
  LaunchActivity launchActivity=((LaunchActivity)fragment.getParentActivity());
  launchActivity.switchToAccount(info.messageObject.currentAccount,true);
  LocationActivity locationActivity=new LocationActivity(2);
  locationActivity.setMessageObject(info.messageObject);
  final long dialog_id=info.messageObject.getDialogId();
  locationActivity.setDelegate((location,live) -> SendMessagesHelper.getInstance(info.messageObject.currentAccount).sendMessage(location,dialog_id,null,null,null));
  launchActivity.presentFragment(locationActivity);
}
