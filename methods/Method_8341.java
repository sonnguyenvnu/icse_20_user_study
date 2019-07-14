public void setInfo(TLRPC.ChatFull chatFull){
  info=chatFull;
  if (chatFull != null) {
    if (chatFull.exported_invite instanceof TLRPC.TL_chatInviteExported) {
      invite=chatFull.exported_invite;
    }
 else {
      generateLink(false);
    }
  }
}
