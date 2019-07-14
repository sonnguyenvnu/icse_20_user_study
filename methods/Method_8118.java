public void setDialog(MessageObject messageObject,Location userLocation){
  int fromId=messageObject.messageOwner.from_id;
  if (messageObject.isForwarded()) {
    if (messageObject.messageOwner.fwd_from.channel_id != 0) {
      fromId=-messageObject.messageOwner.fwd_from.channel_id;
    }
 else {
      fromId=messageObject.messageOwner.fwd_from.from_id;
    }
  }
  currentAccount=messageObject.currentAccount;
  String address=null;
  String name;
  if (!TextUtils.isEmpty(messageObject.messageOwner.media.address)) {
    address=messageObject.messageOwner.media.address;
  }
  if (!TextUtils.isEmpty(messageObject.messageOwner.media.title)) {
    name=messageObject.messageOwner.media.title;
    Drawable drawable=getResources().getDrawable(R.drawable.pin);
    drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_location_sendLocationIcon),PorterDuff.Mode.MULTIPLY));
    int color=Theme.getColor(Theme.key_location_placeLocationBackground);
    Drawable circle=Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40),color,color);
    CombinedDrawable combinedDrawable=new CombinedDrawable(circle,drawable);
    combinedDrawable.setCustomSize(AndroidUtilities.dp(40),AndroidUtilities.dp(40));
    combinedDrawable.setIconSize(AndroidUtilities.dp(24),AndroidUtilities.dp(24));
    avatarImageView.setImageDrawable(combinedDrawable);
  }
 else {
    name="";
    avatarDrawable=null;
    if (fromId > 0) {
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(fromId);
      if (user != null) {
        avatarDrawable=new AvatarDrawable(user);
        name=UserObject.getUserName(user);
        avatarImageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
      }
    }
 else {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-fromId);
      if (chat != null) {
        avatarDrawable=new AvatarDrawable(chat);
        name=chat.title;
        avatarImageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
      }
    }
  }
  nameTextView.setText(name);
  location.setLatitude(messageObject.messageOwner.media.geo.lat);
  location.setLongitude(messageObject.messageOwner.media.geo._long);
  if (userLocation != null) {
    float distance=location.distanceTo(userLocation);
    if (address != null) {
      if (distance < 1000) {
        distanceTextView.setText(String.format("%s - %d %s",address,(int)(distance),LocaleController.getString("MetersAway",R.string.MetersAway)));
      }
 else {
        distanceTextView.setText(String.format("%s - %.2f %s",address,distance / 1000.0f,LocaleController.getString("KMetersAway",R.string.KMetersAway)));
      }
    }
 else {
      if (distance < 1000) {
        distanceTextView.setText(String.format("%d %s",(int)(distance),LocaleController.getString("MetersAway",R.string.MetersAway)));
      }
 else {
        distanceTextView.setText(String.format("%.2f %s",distance / 1000.0f,LocaleController.getString("KMetersAway",R.string.KMetersAway)));
      }
    }
  }
 else {
    if (address != null) {
      distanceTextView.setText(address);
    }
 else {
      distanceTextView.setText(LocaleController.getString("Loading",R.string.Loading));
    }
  }
}
