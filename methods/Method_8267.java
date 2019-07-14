private void fillEditingMediaWithCaption(CharSequence caption,ArrayList<TLRPC.MessageEntity> entities){
  if (editingMessageObject == null) {
    return;
  }
  if (!TextUtils.isEmpty(caption)) {
    editingMessageObject.editingMessage=caption;
    editingMessageObject.editingMessageEntities=entities;
  }
 else   if (chatActivityEnterView != null) {
    editingMessageObject.editingMessage=chatActivityEnterView.getFieldText();
    if (editingMessageObject.editingMessage == null && !TextUtils.isEmpty(editingMessageObject.messageOwner.message)) {
      editingMessageObject.editingMessage="";
    }
  }
}
