public void doneEditingMessage(){
  if (editingMessageObject != null) {
    delegate.onMessageEditEnd(true);
    showEditDoneProgress(true,true);
    CharSequence[] message=new CharSequence[]{messageEditText.getText()};
    ArrayList<TLRPC.MessageEntity> entities=DataQuery.getInstance(currentAccount).getEntities(message);
    editingMessageReqId=SendMessagesHelper.getInstance(currentAccount).editMessage(editingMessageObject,message[0].toString(),messageWebPageSearch,parentFragment,entities,() -> {
      editingMessageReqId=0;
      setEditingMessageObject(null,false);
    }
);
  }
}
