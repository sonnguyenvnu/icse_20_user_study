private void startEditingMessageObject(MessageObject messageObject){
  if (messageObject == null || getParentActivity() == null) {
    return;
  }
  if (searchItem != null && actionBar.isSearchFieldVisible()) {
    actionBar.closeSearchField();
    chatActivityEnterView.setFieldFocused();
  }
  mentionsAdapter.setNeedBotContext(false);
  chatActivityEnterView.setVisibility(View.VISIBLE);
  showFieldPanelForEdit(true,messageObject);
  updateBottomOverlay();
  checkEditTimer();
  chatActivityEnterView.setAllowStickersAndGifs(false,false);
  updatePinnedMessageView(true);
  updateVisibleRows();
  TLRPC.TL_messages_getMessageEditData req=new TLRPC.TL_messages_getMessageEditData();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)dialog_id);
  req.id=messageObject.getId();
  editingMessageObjectReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    editingMessageObjectReqId=0;
    if (response == null) {
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(LocaleController.getString("EditMessageError",R.string.EditMessageError));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      showDialog(builder.create());
      if (chatActivityEnterView != null) {
        chatActivityEnterView.setEditingMessageObject(null,false);
        hideFieldPanel(true);
      }
    }
 else {
      if (chatActivityEnterView != null) {
        chatActivityEnterView.showEditDoneProgress(false,true);
      }
    }
  }
));
}
