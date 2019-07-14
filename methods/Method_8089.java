private void checkText(){
  LocationController.SharingLocationInfo info=LocationController.getInstance(currentAccount).getSharingLocationInfo(dialogId);
  if (info != null) {
    setText(LocaleController.getString("StopLiveLocation",R.string.StopLiveLocation),LocaleController.formatLocationUpdateDate(info.messageObject.messageOwner.edit_date != 0 ? info.messageObject.messageOwner.edit_date : info.messageObject.messageOwner.date));
  }
 else {
    setText(LocaleController.getString("SendLiveLocation",R.string.SendLiveLocation),LocaleController.getString("SendLiveLocationInfo",R.string.SendLiveLocationInfo));
  }
}
