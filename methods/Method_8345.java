private void generateLink(final boolean newRequest){
  loadingInvite=true;
  TLRPC.TL_messages_exportChatInvite req=new TLRPC.TL_messages_exportChatInvite();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer(-chatId);
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      invite=(TLRPC.ExportedChatInvite)response;
      if (info != null) {
        info.exported_invite=invite;
      }
      if (newRequest) {
        if (getParentActivity() == null) {
          return;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setMessage(LocaleController.getString("RevokeAlertNewLink",R.string.RevokeAlertNewLink));
        builder.setTitle(LocaleController.getString("RevokeLink",R.string.RevokeLink));
        builder.setNegativeButton(LocaleController.getString("OK",R.string.OK),null);
        showDialog(builder.create());
      }
    }
    loadingInvite=false;
    if (privateTextView != null) {
      privateTextView.setText(invite != null ? invite.link : LocaleController.getString("Loading",R.string.Loading),true);
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
