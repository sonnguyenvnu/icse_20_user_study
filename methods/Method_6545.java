public static ImageLocation getForChat(TLRPC.Chat chat,boolean big){
  if (chat == null || chat.photo == null) {
    return null;
  }
  TLRPC.FileLocation fileLocation=big ? chat.photo.photo_big : chat.photo.photo_small;
  if (fileLocation == null) {
    return null;
  }
  TLRPC.InputPeer inputPeer;
  if (ChatObject.isChannel(chat)) {
    if (chat.access_hash == 0) {
      return null;
    }
    inputPeer=new TLRPC.TL_inputPeerChannel();
    inputPeer.channel_id=chat.id;
    inputPeer.access_hash=chat.access_hash;
  }
 else {
    inputPeer=new TLRPC.TL_inputPeerChat();
    inputPeer.chat_id=chat.id;
  }
  int dc_id;
  if (chat.photo.dc_id != 0) {
    dc_id=chat.photo.dc_id;
  }
 else {
    dc_id=fileLocation.dc_id;
  }
  return getForPhoto(fileLocation,0,null,null,inputPeer,big,dc_id,null,null);
}
