public void update(int mask){
  if (currentContact == null) {
    return;
  }
  String newName=null;
  avatarDrawable.setInfo(currentContact.contact_id,currentContact.first_name,currentContact.last_name,false);
  if (currentName != null) {
    nameTextView.setText(currentName,true);
  }
 else {
    nameTextView.setText(ContactsController.formatName(currentContact.first_name,currentContact.last_name));
  }
  statusTextView.setTag(Theme.key_windowBackgroundWhiteGrayText);
  statusTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
  if (currentContact.imported > 0) {
    statusTextView.setText(LocaleController.formatPluralString("TelegramContacts",currentContact.imported));
  }
 else {
    statusTextView.setText(currentContact.phones.get(0));
  }
  avatarImageView.setImageDrawable(avatarDrawable);
}
