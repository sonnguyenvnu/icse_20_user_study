private void checkLocationString(){
  if (!(fragment instanceof ChatActivity) || titleTextView == null) {
    return;
  }
  ChatActivity chatActivity=(ChatActivity)fragment;
  long dialogId=chatActivity.getDialogId();
  int currentAccount=chatActivity.getCurrentAccount();
  ArrayList<TLRPC.Message> messages=LocationController.getInstance(currentAccount).locationsCache.get(dialogId);
  if (!firstLocationsLoaded) {
    LocationController.getInstance(currentAccount).loadLiveLocations(dialogId);
    firstLocationsLoaded=true;
  }
  int locationSharingCount=0;
  TLRPC.User notYouUser=null;
  if (messages != null) {
    int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
    int date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
    for (int a=0; a < messages.size(); a++) {
      TLRPC.Message message=messages.get(a);
      if (message.media == null) {
        continue;
      }
      if (message.date + message.media.period > date) {
        if (notYouUser == null && message.from_id != currentUserId) {
          notYouUser=MessagesController.getInstance(currentAccount).getUser(message.from_id);
        }
        locationSharingCount++;
      }
    }
  }
  if (lastLocationSharingCount == locationSharingCount) {
    return;
  }
  lastLocationSharingCount=locationSharingCount;
  String liveLocation=LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation);
  String fullString;
  if (locationSharingCount == 0) {
    fullString=liveLocation;
  }
 else {
    int otherSharingCount=locationSharingCount - 1;
    if (LocationController.getInstance(currentAccount).isSharingLocation(dialogId)) {
      if (otherSharingCount != 0) {
        if (otherSharingCount == 1 && notYouUser != null) {
          fullString=String.format("%1$s - %2$s",liveLocation,LocaleController.formatString("SharingYouAndOtherName",R.string.SharingYouAndOtherName,UserObject.getFirstName(notYouUser)));
        }
 else {
          fullString=String.format("%1$s - %2$s %3$s",liveLocation,LocaleController.getString("ChatYourSelfName",R.string.ChatYourSelfName),LocaleController.formatPluralString("AndOther",otherSharingCount));
        }
      }
 else {
        fullString=String.format("%1$s - %2$s",liveLocation,LocaleController.getString("ChatYourSelfName",R.string.ChatYourSelfName));
      }
    }
 else {
      if (otherSharingCount != 0) {
        fullString=String.format("%1$s - %2$s %3$s",liveLocation,UserObject.getFirstName(notYouUser),LocaleController.formatPluralString("AndOther",otherSharingCount));
      }
 else {
        fullString=String.format("%1$s - %2$s",liveLocation,UserObject.getFirstName(notYouUser));
      }
    }
  }
  if (lastString != null && fullString.equals(lastString)) {
    return;
  }
  lastString=fullString;
  int start=fullString.indexOf(liveLocation);
  SpannableStringBuilder stringBuilder=new SpannableStringBuilder(fullString);
  titleTextView.setEllipsize(TextUtils.TruncateAt.END);
  if (start >= 0) {
    TypefaceSpan span=new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf"),0,Theme.getColor(Theme.key_inappPlayerPerformer));
    stringBuilder.setSpan(span,start,start + liveLocation.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
  }
  titleTextView.setText(stringBuilder);
}
