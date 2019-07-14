private void generateLink(final boolean newRequest){
  loading=true;
  TLRPC.TL_messages_exportChatInvite req=new TLRPC.TL_messages_exportChatInvite();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer(-chat_id);
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      invite=(TLRPC.ExportedChatInvite)response;
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
    loading=false;
    listAdapter.notifyDataSetChanged();
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
