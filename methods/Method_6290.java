public static String getBannedRightsString(TLRPC.TL_chatBannedRights bannedRights){
  String currentBannedRights="";
  currentBannedRights+=bannedRights.view_messages ? 1 : 0;
  currentBannedRights+=bannedRights.send_messages ? 1 : 0;
  currentBannedRights+=bannedRights.send_media ? 1 : 0;
  currentBannedRights+=bannedRights.send_stickers ? 1 : 0;
  currentBannedRights+=bannedRights.send_gifs ? 1 : 0;
  currentBannedRights+=bannedRights.send_games ? 1 : 0;
  currentBannedRights+=bannedRights.send_inline ? 1 : 0;
  currentBannedRights+=bannedRights.embed_links ? 1 : 0;
  currentBannedRights+=bannedRights.send_polls ? 1 : 0;
  currentBannedRights+=bannedRights.invite_users ? 1 : 0;
  currentBannedRights+=bannedRights.change_info ? 1 : 0;
  currentBannedRights+=bannedRights.pin_messages ? 1 : 0;
  currentBannedRights+=bannedRights.until_date;
  return currentBannedRights;
}
