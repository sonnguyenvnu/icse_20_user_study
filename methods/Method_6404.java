public void cleanDraft(long did,boolean replyOnly){
  TLRPC.DraftMessage draftMessage=drafts.get(did);
  if (draftMessage == null) {
    return;
  }
  if (!replyOnly) {
    drafts.remove(did);
    draftMessages.remove(did);
    preferences.edit().remove("" + did).remove("r_" + did).commit();
    MessagesController.getInstance(currentAccount).sortDialogs(null);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
  }
 else   if (draftMessage.reply_to_msg_id != 0) {
    draftMessage.reply_to_msg_id=0;
    draftMessage.flags&=~1;
    saveDraft(did,draftMessage.message,draftMessage.entities,null,draftMessage.no_webpage,true);
  }
}
