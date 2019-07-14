private static boolean getBannedRight(TLRPC.TL_chatBannedRights rights,int action){
  if (rights == null) {
    return false;
  }
  boolean value;
switch (action) {
case ACTION_PIN:
    return rights.pin_messages;
case ACTION_CHANGE_INFO:
  return rights.change_info;
case ACTION_INVITE:
return rights.invite_users;
case ACTION_SEND:
return rights.send_messages;
case ACTION_SEND_MEDIA:
return rights.send_media;
case ACTION_SEND_STICKERS:
return rights.send_stickers;
case ACTION_EMBED_LINKS:
return rights.embed_links;
case ACTION_SEND_POLLS:
return rights.send_polls;
case ACTION_VIEW:
return rights.view_messages;
}
return false;
}
