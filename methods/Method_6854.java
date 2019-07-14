private void removeDialog(TLRPC.Dialog dialog){
  if (dialog == null) {
    return;
  }
  long did=dialog.id;
  if (dialogsServerOnly.remove(dialog) && DialogObject.isChannel(dialog)) {
    Utilities.stageQueue.postRunnable(() -> {
      channelsPts.delete(-(int)did);
      shortPollChannels.delete(-(int)did);
      needShortPollChannels.delete(-(int)did);
      shortPollOnlines.delete(-(int)did);
      needShortPollOnlines.delete(-(int)did);
    }
);
  }
  allDialogs.remove(dialog);
  dialogsCanAddUsers.remove(dialog);
  dialogsChannelsOnly.remove(dialog);
  dialogsGroupsOnly.remove(dialog);
  dialogsUsersOnly.remove(dialog);
  dialogsForward.remove(dialog);
  dialogs_dict.remove(did);
  dialogs_read_inbox_max.remove(did);
  dialogs_read_outbox_max.remove(did);
  ArrayList<TLRPC.Dialog> dialogs=dialogsByFolder.get(dialog.folder_id);
  if (dialogs != null) {
    dialogs.remove(dialog);
  }
}
