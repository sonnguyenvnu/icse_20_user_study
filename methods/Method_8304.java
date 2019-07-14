private void createDeleteMessagesAlert(final MessageObject finalSelectedObject,final MessageObject.GroupedMessages finalSelectedGroup,int loadParticipant){
  AlertsCreator.createDeleteMessagesAlert(this,currentUser,currentChat,currentEncryptedChat,chatInfo,mergeDialogId,finalSelectedObject,selectedMessagesIds,finalSelectedGroup,loadParticipant,() -> {
    hideActionMode();
    updatePinnedMessageView(true);
  }
);
}
