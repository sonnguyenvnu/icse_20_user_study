private void fetchFolderInLoadedPinnedDialogs(TLRPC.TL_messages_peerDialogs res){
  for (int a=0, N=res.dialogs.size(); a < N; a++) {
    TLRPC.Dialog dialog=res.dialogs.get(a);
    if (dialog instanceof TLRPC.TL_dialogFolder) {
      TLRPC.TL_dialogFolder dialogFolder=(TLRPC.TL_dialogFolder)dialog;
      long folderTopDialogId=DialogObject.getPeerDialogId(dialog.peer);
      if (dialogFolder.top_message == 0 || folderTopDialogId == 0) {
        res.dialogs.remove(dialogFolder);
        continue;
      }
      for (int b=0, N2=res.messages.size(); b < N2; b++) {
        TLRPC.Message message=res.messages.get(b);
        long messageDialogId=MessageObject.getDialogId(message);
        if (folderTopDialogId == messageDialogId && dialog.top_message == message.id) {
          TLRPC.TL_dialog newDialog=new TLRPC.TL_dialog();
          newDialog.peer=dialog.peer;
          newDialog.top_message=dialog.top_message;
          newDialog.folder_id=dialogFolder.folder.id;
          newDialog.flags|=16;
          res.dialogs.add(newDialog);
          TLRPC.InputPeer inputPeer;
          if (dialog.peer instanceof TLRPC.TL_peerChannel) {
            inputPeer=new TLRPC.TL_inputPeerChannel();
            inputPeer.channel_id=dialog.peer.channel_id;
            for (int c=0, N3=res.chats.size(); c < N3; c++) {
              TLRPC.Chat chat=res.chats.get(c);
              if (chat.id == inputPeer.channel_id) {
                inputPeer.access_hash=chat.access_hash;
                break;
              }
            }
          }
 else           if (dialog.peer instanceof TLRPC.TL_peerChat) {
            inputPeer=new TLRPC.TL_inputPeerChat();
            inputPeer.chat_id=dialog.peer.chat_id;
          }
 else {
            inputPeer=new TLRPC.TL_inputPeerUser();
            inputPeer.user_id=dialog.peer.user_id;
            for (int c=0, N3=res.users.size(); c < N3; c++) {
              TLRPC.User user=res.users.get(c);
              if (user.id == inputPeer.user_id) {
                inputPeer.access_hash=user.access_hash;
                break;
              }
            }
          }
          loadUnknownDialog(inputPeer,0);
          break;
        }
      }
      break;
    }
  }
}
