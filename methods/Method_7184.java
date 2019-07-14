@UiThread public static void prepareSendingPhoto(String imageFilePath,Uri imageUri,long dialog_id,MessageObject reply_to_msg,CharSequence caption,ArrayList<TLRPC.MessageEntity> entities,ArrayList<TLRPC.InputDocument> stickers,InputContentInfoCompat inputContent,int ttl,MessageObject editingMessageObject){
  SendingMediaInfo info=new SendingMediaInfo();
  info.path=imageFilePath;
  info.uri=imageUri;
  if (caption != null) {
    info.caption=caption.toString();
  }
  info.entities=entities;
  info.ttl=ttl;
  if (stickers != null && !stickers.isEmpty()) {
    info.masks=new ArrayList<>(stickers);
  }
  ArrayList<SendingMediaInfo> infos=new ArrayList<>();
  infos.add(info);
  prepareSendingMedia(infos,dialog_id,reply_to_msg,inputContent,false,false,editingMessageObject);
}
