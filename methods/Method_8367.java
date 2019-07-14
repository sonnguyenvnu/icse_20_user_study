private String formatUserPermissions(TLRPC.TL_chatBannedRights rights){
  if (rights == null) {
    return "";
  }
  StringBuilder builder=new StringBuilder();
  if (rights.view_messages && defaultBannedRights.view_messages != rights.view_messages) {
    builder.append(LocaleController.getString("UserRestrictionsNoRead",R.string.UserRestrictionsNoRead));
  }
  if (rights.send_messages && defaultBannedRights.send_messages != rights.send_messages) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoSend",R.string.UserRestrictionsNoSend));
  }
  if (rights.send_media && defaultBannedRights.send_media != rights.send_media) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoSendMedia",R.string.UserRestrictionsNoSendMedia));
  }
  if (rights.send_stickers && defaultBannedRights.send_stickers != rights.send_stickers) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoSendStickers",R.string.UserRestrictionsNoSendStickers));
  }
  if (rights.send_polls && defaultBannedRights.send_polls != rights.send_polls) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoSendPolls",R.string.UserRestrictionsNoSendPolls));
  }
  if (rights.embed_links && defaultBannedRights.embed_links != rights.embed_links) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoEmbedLinks",R.string.UserRestrictionsNoEmbedLinks));
  }
  if (rights.invite_users && defaultBannedRights.invite_users != rights.invite_users) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoInviteUsers",R.string.UserRestrictionsNoInviteUsers));
  }
  if (rights.pin_messages && defaultBannedRights.pin_messages != rights.pin_messages) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoPinMessages",R.string.UserRestrictionsNoPinMessages));
  }
  if (rights.change_info && defaultBannedRights.change_info != rights.change_info) {
    if (builder.length() != 0) {
      builder.append(", ");
    }
    builder.append(LocaleController.getString("UserRestrictionsNoChangeInfo",R.string.UserRestrictionsNoChangeInfo));
  }
  if (builder.length() != 0) {
    builder.replace(0,1,builder.substring(0,1).toUpperCase());
    builder.append('.');
  }
  return builder.toString();
}
