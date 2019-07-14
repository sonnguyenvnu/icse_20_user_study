public int[] getDialogLoadOffsets(int folderId){
  SharedPreferences preferences=getPreferences();
  int dialogsLoadOffsetId=preferences.getInt("2dialogsLoadOffsetId" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  int dialogsLoadOffsetDate=preferences.getInt("2dialogsLoadOffsetDate" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  int dialogsLoadOffsetUserId=preferences.getInt("2dialogsLoadOffsetUserId" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  int dialogsLoadOffsetChatId=preferences.getInt("2dialogsLoadOffsetChatId" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  int dialogsLoadOffsetChannelId=preferences.getInt("2dialogsLoadOffsetChannelId" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  long dialogsLoadOffsetAccess=preferences.getLong("2dialogsLoadOffsetAccess" + (folderId == 0 ? "" : folderId),hasValidDialogLoadIds ? 0 : -1);
  return new int[]{dialogsLoadOffsetId,dialogsLoadOffsetDate,dialogsLoadOffsetUserId,dialogsLoadOffsetChatId,dialogsLoadOffsetChannelId,(int)dialogsLoadOffsetAccess,(int)(dialogsLoadOffsetAccess >> 32)};
}
