public void setDialog(LocationActivity.LiveLocation info,Location userLocation){
  liveLocation=info;
  int lower_id=info.id;
  if (lower_id > 0) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(lower_id);
    if (user != null) {
      avatarDrawable.setInfo(user);
      nameTextView.setText(ContactsController.formatName(user.first_name,user.last_name));
      avatarImageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
    if (chat != null) {
      avatarDrawable.setInfo(chat);
      nameTextView.setText(chat.title);
      avatarImageView.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
    }
  }
  LatLng position=info.marker.getPosition();
  location.setLatitude(position.latitude);
  location.setLongitude(position.longitude);
  String time=LocaleController.formatLocationUpdateDate(info.object.edit_date != 0 ? info.object.edit_date : info.object.date);
  if (userLocation != null) {
    float distance=location.distanceTo(userLocation);
    if (distance < 1000) {
      distanceTextView.setText(String.format("%s - %d %s",time,(int)(distance),LocaleController.getString("MetersAway",R.string.MetersAway)));
    }
 else {
      distanceTextView.setText(String.format("%s - %.2f %s",time,distance / 1000.0f,LocaleController.getString("KMetersAway",R.string.KMetersAway)));
    }
  }
 else {
    distanceTextView.setText(time);
  }
}
