private void generateLink(){
  if (loadingInvite || invite != null) {
    return;
  }
  loadingInvite=true;
  TLRPC.TL_messages_exportChatInvite req=new TLRPC.TL_messages_exportChatInvite();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer(-chatId);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      invite=(TLRPC.ExportedChatInvite)response;
    }
    loadingInvite=false;
    privateContainer.setText(invite != null ? invite.link : LocaleController.getString("Loading",R.string.Loading),false);
  }
));
}
