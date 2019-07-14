public void createMessageSendInfo(){
  if (messageOwner.message != null && (messageOwner.id < 0 || isEditing()) && messageOwner.params != null) {
    String param;
    if ((param=messageOwner.params.get("ve")) != null && (isVideo() || isNewGif() || isRoundVideo())) {
      videoEditedInfo=new VideoEditedInfo();
      if (!videoEditedInfo.parseString(param)) {
        videoEditedInfo=null;
      }
 else {
        videoEditedInfo.roundVideo=isRoundVideo();
      }
    }
    if (messageOwner.send_state == MESSAGE_SEND_STATE_EDITING && (param=messageOwner.params.get("prevMedia")) != null) {
      SerializedData serializedData=new SerializedData(Base64.decode(param,Base64.DEFAULT));
      int constructor=serializedData.readInt32(false);
      previousMedia=TLRPC.MessageMedia.TLdeserialize(serializedData,constructor,false);
      previousCaption=serializedData.readString(false);
      previousAttachPath=serializedData.readString(false);
      int count=serializedData.readInt32(false);
      previousCaptionEntities=new ArrayList<>(count);
      for (int a=0; a < count; a++) {
        constructor=serializedData.readInt32(false);
        TLRPC.MessageEntity entity=TLRPC.MessageEntity.TLdeserialize(serializedData,constructor,false);
        previousCaptionEntities.add(entity);
      }
      serializedData.cleanup();
    }
  }
}
