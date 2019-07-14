private void checkLocationExpired(){
  if (currentMessageObject == null) {
    return;
  }
  boolean newExpired=isCurrentLocationTimeExpired(currentMessageObject);
  if (newExpired != locationExpired) {
    locationExpired=newExpired;
    if (!locationExpired) {
      AndroidUtilities.runOnUIThread(invalidateRunnable,1000);
      scheduledInvalidate=true;
      int maxWidth=backgroundWidth - AndroidUtilities.dp(37 + 54);
      docTitleLayout=new StaticLayout(TextUtils.ellipsize(LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation),Theme.chat_locationTitlePaint,maxWidth,TextUtils.TruncateAt.END),Theme.chat_locationTitlePaint,maxWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
    }
 else {
      MessageObject messageObject=currentMessageObject;
      currentMessageObject=null;
      setMessageObject(messageObject,currentMessagesGroup,pinnedBottom,pinnedTop);
    }
  }
}
