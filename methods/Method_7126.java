protected void performSendEncryptedRequest(final TLRPC.TL_messages_sendEncryptedMultiMedia req,final SendMessagesHelper.DelayedMessage message){
  for (int a=0; a < req.files.size(); a++) {
    performSendEncryptedRequest(req.messages.get(a),message.messages.get(a),message.encryptedChat,req.files.get(a),message.originalPaths.get(a),message.messageObjects.get(a));
  }
}
