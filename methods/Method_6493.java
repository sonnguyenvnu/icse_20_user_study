private boolean getPeerReferenceReplacement(TLRPC.User user,TLRPC.Chat chat,boolean big,TLRPC.InputFileLocation location,TLRPC.InputFileLocation[] replacement,boolean[] needReplacement){
  if (needReplacement != null && needReplacement[0]) {
    replacement[0]=new TLRPC.TL_inputPeerPhotoFileLocation();
    replacement[0].id=location.volume_id;
    replacement[0].volume_id=location.volume_id;
    replacement[0].local_id=location.local_id;
    replacement[0].big=big;
    TLRPC.InputPeer peer;
    if (user != null) {
      TLRPC.TL_inputPeerUser inputPeerUser=new TLRPC.TL_inputPeerUser();
      inputPeerUser.user_id=user.id;
      inputPeerUser.access_hash=user.access_hash;
      peer=inputPeerUser;
    }
 else {
      if (ChatObject.isChannel(chat)) {
        TLRPC.TL_inputPeerChat inputPeerChat=new TLRPC.TL_inputPeerChat();
        inputPeerChat.chat_id=chat.id;
        peer=inputPeerChat;
      }
 else {
        TLRPC.TL_inputPeerChannel inputPeerChannel=new TLRPC.TL_inputPeerChannel();
        inputPeerChannel.channel_id=chat.id;
        inputPeerChannel.access_hash=chat.access_hash;
        peer=inputPeerChannel;
      }
    }
    replacement[0].peer=peer;
    return true;
  }
  return false;
}
