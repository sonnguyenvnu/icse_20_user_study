public void changeChatAvatar(int chat_id,TLRPC.InputFile uploadedAvatar,TLRPC.FileLocation smallSize,TLRPC.FileLocation bigSize){
  TLObject request;
  if (ChatObject.isChannel(chat_id,currentAccount)) {
    TLRPC.TL_channels_editPhoto req=new TLRPC.TL_channels_editPhoto();
    req.channel=getInputChannel(chat_id);
    if (uploadedAvatar != null) {
      req.photo=new TLRPC.TL_inputChatUploadedPhoto();
      req.photo.file=uploadedAvatar;
    }
 else {
      req.photo=new TLRPC.TL_inputChatPhotoEmpty();
    }
    request=req;
  }
 else {
    TLRPC.TL_messages_editChatPhoto req=new TLRPC.TL_messages_editChatPhoto();
    req.chat_id=chat_id;
    if (uploadedAvatar != null) {
      req.photo=new TLRPC.TL_inputChatUploadedPhoto();
      req.photo.file=uploadedAvatar;
    }
 else {
      req.photo=new TLRPC.TL_inputChatPhotoEmpty();
    }
    request=req;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
    if (error != null) {
      return;
    }
    TLRPC.Updates updates=(TLRPC.Updates)response;
    TLRPC.Photo photo=null;
    for (int a=0, N=updates.updates.size(); a < N; a++) {
      TLRPC.Update update=updates.updates.get(a);
      if (update instanceof TLRPC.TL_updateNewChannelMessage) {
        TLRPC.Message message=((TLRPC.TL_updateNewChannelMessage)update).message;
        if (message.action instanceof TLRPC.TL_messageActionChatEditPhoto && message.action.photo instanceof TLRPC.TL_photo) {
          photo=message.action.photo;
          break;
        }
      }
 else       if (update instanceof TLRPC.TL_updateNewMessage) {
        TLRPC.Message message=((TLRPC.TL_updateNewMessage)update).message;
        if (message.action instanceof TLRPC.TL_messageActionChatEditPhoto && message.action.photo instanceof TLRPC.TL_photo) {
          photo=message.action.photo;
          break;
        }
      }
    }
    if (photo != null) {
      TLRPC.PhotoSize small=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,150);
      if (small != null && smallSize != null) {
        File destFile=FileLoader.getPathToAttach(small,true);
        File src=FileLoader.getPathToAttach(smallSize,true);
        src.renameTo(destFile);
        String oldKey=smallSize.volume_id + "_" + smallSize.local_id + "@50_50";
        String newKey=small.location.volume_id + "_" + small.location.local_id + "@50_50";
        ImageLoader.getInstance().replaceImageInCache(oldKey,newKey,ImageLocation.getForPhoto(small,photo),true);
      }
      TLRPC.PhotoSize big=FileLoader.getClosestPhotoSizeWithSize(photo.sizes,800);
      if (big != null && bigSize != null) {
        File destFile=FileLoader.getPathToAttach(big,true);
        File src=FileLoader.getPathToAttach(bigSize,true);
        src.renameTo(destFile);
      }
    }
    processUpdates(updates,false);
  }
,ConnectionsManager.RequestFlagInvokeAfter);
}
