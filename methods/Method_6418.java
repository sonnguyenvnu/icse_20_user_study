public static long getPeerDialogId(TLRPC.InputPeer peer){
  if (peer == null) {
    return 0;
  }
  if (peer.user_id != 0) {
    return peer.user_id;
  }
 else   if (peer.chat_id != 0) {
    return -peer.chat_id;
  }
 else {
    return -peer.channel_id;
  }
}
