public void setSession(TLObject object,boolean divider){
  needDivider=divider;
  if (object instanceof TLRPC.TL_authorization) {
    TLRPC.TL_authorization session=(TLRPC.TL_authorization)object;
    nameTextView.setText(String.format(Locale.US,"%s %s",session.app_name,session.app_version));
    if ((session.flags & 1) != 0) {
      setTag(Theme.key_windowBackgroundWhiteValueText);
      onlineTextView.setText(LocaleController.getString("Online",R.string.Online));
      onlineTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
    }
 else {
      setTag(Theme.key_windowBackgroundWhiteGrayText3);
      onlineTextView.setText(LocaleController.stringForMessageListDate(session.date_active));
      onlineTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
    }
    StringBuilder stringBuilder=new StringBuilder();
    if (session.ip.length() != 0) {
      stringBuilder.append(session.ip);
    }
    if (session.country.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(" ");
      }
      stringBuilder.append("— ");
      stringBuilder.append(session.country);
    }
    detailExTextView.setText(stringBuilder);
    stringBuilder=new StringBuilder();
    if (session.device_model.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append(session.device_model);
    }
    if (session.system_version.length() != 0 || session.platform.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      }
      if (session.platform.length() != 0) {
        stringBuilder.append(session.platform);
      }
      if (session.system_version.length() != 0) {
        if (session.platform.length() != 0) {
          stringBuilder.append(" ");
        }
        stringBuilder.append(session.system_version);
      }
    }
    if (!session.official_app) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append(LocaleController.getString("UnofficialApp",R.string.UnofficialApp));
      stringBuilder.append(" (ID: ");
      stringBuilder.append(session.api_id);
      stringBuilder.append(")");
    }
    detailTextView.setText(stringBuilder);
  }
 else   if (object instanceof TLRPC.TL_webAuthorization) {
    TLRPC.TL_webAuthorization session=(TLRPC.TL_webAuthorization)object;
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(session.bot_id);
    nameTextView.setText(session.domain);
    String name;
    if (user != null) {
      avatarDrawable.setInfo(user);
      name=UserObject.getFirstName(user);
      imageView.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    }
 else {
      name="";
    }
    setTag(Theme.key_windowBackgroundWhiteGrayText3);
    onlineTextView.setText(LocaleController.stringForMessageListDate(session.date_active));
    onlineTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText3));
    StringBuilder stringBuilder=new StringBuilder();
    if (session.ip.length() != 0) {
      stringBuilder.append(session.ip);
    }
    if (session.region.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(" ");
      }
      stringBuilder.append("— ");
      stringBuilder.append(session.region);
    }
    detailExTextView.setText(stringBuilder);
    stringBuilder=new StringBuilder();
    if (!TextUtils.isEmpty(name)) {
      stringBuilder.append(name);
    }
    if (session.browser.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append(session.browser);
    }
    if (session.platform.length() != 0) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      }
      stringBuilder.append(session.platform);
    }
    detailTextView.setText(stringBuilder);
  }
}
