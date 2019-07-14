private void updateMediaPaths(MessageObject newMsgObj,TLRPC.Message sentMessage,int newMsgId,String originalPath,boolean post){
  TLRPC.Message newMsg=newMsgObj.messageOwner;
  if (newMsg.media != null) {
    TLRPC.PhotoSize strippedOld=null;
    TLRPC.PhotoSize strippedNew=null;
    TLObject photoObject=null;
    if (newMsg.media.photo != null) {
      strippedOld=FileLoader.getClosestPhotoSizeWithSize(newMsg.media.photo.sizes,40);
      if (sentMessage != null && sentMessage.media != null && sentMessage.media.photo != null) {
        strippedNew=FileLoader.getClosestPhotoSizeWithSize(sentMessage.media.photo.sizes,40);
      }
 else {
        strippedNew=strippedOld;
      }
      photoObject=newMsg.media.photo;
    }
 else     if (newMsg.media.document != null) {
      strippedOld=FileLoader.getClosestPhotoSizeWithSize(newMsg.media.document.thumbs,40);
      if (sentMessage != null && sentMessage.media != null && sentMessage.media.document != null) {
        strippedNew=FileLoader.getClosestPhotoSizeWithSize(sentMessage.media.document.thumbs,40);
      }
 else {
        strippedNew=strippedOld;
      }
      photoObject=newMsg.media.document;
    }
 else     if (newMsg.media.webpage != null) {
      if (newMsg.media.webpage.photo != null) {
        strippedOld=FileLoader.getClosestPhotoSizeWithSize(newMsg.media.webpage.photo.sizes,40);
        if (sentMessage != null && sentMessage.media != null && sentMessage.media.webpage != null && sentMessage.media.webpage.photo != null) {
          strippedNew=FileLoader.getClosestPhotoSizeWithSize(sentMessage.media.webpage.photo.sizes,40);
        }
 else {
          strippedNew=strippedOld;
        }
        photoObject=newMsg.media.webpage.photo;
      }
 else       if (newMsg.media.webpage.document != null) {
        strippedOld=FileLoader.getClosestPhotoSizeWithSize(newMsg.media.webpage.document.thumbs,40);
        if (sentMessage != null && sentMessage.media != null && sentMessage.media.webpage != null && sentMessage.media.webpage.document != null) {
          strippedNew=FileLoader.getClosestPhotoSizeWithSize(sentMessage.media.webpage.document.thumbs,40);
        }
 else {
          strippedNew=strippedOld;
        }
        photoObject=newMsg.media.webpage.document;
      }
    }
    if (strippedNew instanceof TLRPC.TL_photoStrippedSize && strippedOld instanceof TLRPC.TL_photoStrippedSize) {
      String oldKey="stripped" + FileRefController.getKeyForParentObject(newMsgObj);
      String newKey=null;
      if (sentMessage != null) {
        newKey="stripped" + FileRefController.getKeyForParentObject(sentMessage);
      }
 else {
        newKey="stripped" + "message" + newMsgId + "_" + newMsgObj.getChannelId();
      }
      ImageLoader.getInstance().replaceImageInCache(oldKey,newKey,ImageLocation.getForObject(strippedNew,photoObject),post);
    }
  }
  if (sentMessage == null) {
    return;
  }
  if (sentMessage.media instanceof TLRPC.TL_messageMediaPhoto && sentMessage.media.photo != null && newMsg.media instanceof TLRPC.TL_messageMediaPhoto && newMsg.media.photo != null) {
    if (sentMessage.media.ttl_seconds == 0) {
      MessagesStorage.getInstance(currentAccount).putSentFile(originalPath,sentMessage.media.photo,0,"sent_" + sentMessage.to_id.channel_id + "_" + sentMessage.id);
    }
    if (newMsg.media.photo.sizes.size() == 1 && newMsg.media.photo.sizes.get(0).location instanceof TLRPC.TL_fileLocationUnavailable) {
      newMsg.media.photo.sizes=sentMessage.media.photo.sizes;
    }
 else {
      for (int a=0; a < sentMessage.media.photo.sizes.size(); a++) {
        TLRPC.PhotoSize size=sentMessage.media.photo.sizes.get(a);
        if (size == null || size.location == null || size instanceof TLRPC.TL_photoSizeEmpty || size.type == null) {
          continue;
        }
        for (int b=0; b < newMsg.media.photo.sizes.size(); b++) {
          TLRPC.PhotoSize size2=newMsg.media.photo.sizes.get(b);
          if (size2 == null || size2.location == null || size2.type == null) {
            continue;
          }
          if (size2.location.volume_id == Integer.MIN_VALUE && size.type.equals(size2.type) || size.w == size2.w && size.h == size2.h) {
            String fileName=size2.location.volume_id + "_" + size2.location.local_id;
            String fileName2=size.location.volume_id + "_" + size.location.local_id;
            if (fileName.equals(fileName2)) {
              break;
            }
            File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName + ".jpg");
            File cacheFile2;
            if (sentMessage.media.ttl_seconds == 0 && (sentMessage.media.photo.sizes.size() == 1 || size.w > 90 || size.h > 90)) {
              cacheFile2=FileLoader.getPathToAttach(size);
            }
 else {
              cacheFile2=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName2 + ".jpg");
            }
            cacheFile.renameTo(cacheFile2);
            ImageLoader.getInstance().replaceImageInCache(fileName,fileName2,ImageLocation.getForPhoto(size,sentMessage.media.photo),post);
            size2.location=size.location;
            size2.size=size.size;
            break;
          }
        }
      }
    }
    sentMessage.message=newMsg.message;
    sentMessage.attachPath=newMsg.attachPath;
    newMsg.media.photo.id=sentMessage.media.photo.id;
    newMsg.media.photo.access_hash=sentMessage.media.photo.access_hash;
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaDocument && sentMessage.media.document != null && newMsg.media instanceof TLRPC.TL_messageMediaDocument && newMsg.media.document != null) {
    if (sentMessage.media.ttl_seconds == 0) {
      boolean isVideo=MessageObject.isVideoMessage(sentMessage);
      if ((isVideo || MessageObject.isGifMessage(sentMessage)) && MessageObject.isGifDocument(sentMessage.media.document) == MessageObject.isGifDocument(newMsg.media.document)) {
        MessagesStorage.getInstance(currentAccount).putSentFile(originalPath,sentMessage.media.document,2,"sent_" + sentMessage.to_id.channel_id + "_" + sentMessage.id);
        if (isVideo) {
          sentMessage.attachPath=newMsg.attachPath;
        }
      }
 else       if (!MessageObject.isVoiceMessage(sentMessage) && !MessageObject.isRoundVideoMessage(sentMessage)) {
        MessagesStorage.getInstance(currentAccount).putSentFile(originalPath,sentMessage.media.document,1,"sent_" + sentMessage.to_id.channel_id + "_" + sentMessage.id);
      }
    }
    TLRPC.PhotoSize size2=FileLoader.getClosestPhotoSizeWithSize(newMsg.media.document.thumbs,320);
    TLRPC.PhotoSize size=FileLoader.getClosestPhotoSizeWithSize(sentMessage.media.document.thumbs,320);
    if (size2 != null && size2.location != null && size2.location.volume_id == Integer.MIN_VALUE && size != null && size.location != null && !(size instanceof TLRPC.TL_photoSizeEmpty) && !(size2 instanceof TLRPC.TL_photoSizeEmpty)) {
      String fileName=size2.location.volume_id + "_" + size2.location.local_id;
      String fileName2=size.location.volume_id + "_" + size.location.local_id;
      if (!fileName.equals(fileName2)) {
        File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName + ".jpg");
        File cacheFile2=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName2 + ".jpg");
        cacheFile.renameTo(cacheFile2);
        ImageLoader.getInstance().replaceImageInCache(fileName,fileName2,ImageLocation.getForDocument(size,sentMessage.media.document),post);
        size2.location=size.location;
        size2.size=size.size;
      }
    }
 else     if (size2 != null && MessageObject.isStickerMessage(sentMessage) && size2.location != null) {
      size.location=size2.location;
    }
 else     if (size2 == null || size2 != null && size2.location instanceof TLRPC.TL_fileLocationUnavailable || size2 instanceof TLRPC.TL_photoSizeEmpty) {
      newMsg.media.document.thumbs=sentMessage.media.document.thumbs;
    }
    newMsg.media.document.dc_id=sentMessage.media.document.dc_id;
    newMsg.media.document.id=sentMessage.media.document.id;
    newMsg.media.document.access_hash=sentMessage.media.document.access_hash;
    byte[] oldWaveform=null;
    for (int a=0; a < newMsg.media.document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=newMsg.media.document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        oldWaveform=attribute.waveform;
        break;
      }
    }
    newMsg.media.document.attributes=sentMessage.media.document.attributes;
    if (oldWaveform != null) {
      for (int a=0; a < newMsg.media.document.attributes.size(); a++) {
        TLRPC.DocumentAttribute attribute=newMsg.media.document.attributes.get(a);
        if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
          attribute.waveform=oldWaveform;
          attribute.flags|=4;
        }
      }
    }
    newMsg.media.document.size=sentMessage.media.document.size;
    newMsg.media.document.mime_type=sentMessage.media.document.mime_type;
    if ((sentMessage.flags & TLRPC.MESSAGE_FLAG_FWD) == 0 && MessageObject.isOut(sentMessage)) {
      if (MessageObject.isNewGifDocument(sentMessage.media.document)) {
        DataQuery.getInstance(currentAccount).addRecentGif(sentMessage.media.document,sentMessage.date);
      }
 else       if (MessageObject.isStickerDocument(sentMessage.media.document)) {
        DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_IMAGE,sentMessage,sentMessage.media.document,sentMessage.date,false);
      }
    }
    if (newMsg.attachPath != null && newMsg.attachPath.startsWith(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE).getAbsolutePath())) {
      File cacheFile=new File(newMsg.attachPath);
      File cacheFile2=FileLoader.getPathToAttach(sentMessage.media.document,sentMessage.media.ttl_seconds != 0);
      if (!cacheFile.renameTo(cacheFile2)) {
        if (cacheFile.exists()) {
          sentMessage.attachPath=newMsg.attachPath;
        }
 else {
          newMsgObj.attachPathExists=false;
        }
        newMsgObj.mediaExists=cacheFile2.exists();
        sentMessage.message=newMsg.message;
      }
 else {
        if (MessageObject.isVideoMessage(sentMessage)) {
          newMsgObj.attachPathExists=true;
        }
 else {
          newMsgObj.mediaExists=newMsgObj.attachPathExists;
          newMsgObj.attachPathExists=false;
          newMsg.attachPath="";
          if (originalPath != null && originalPath.startsWith("http")) {
            MessagesStorage.getInstance(currentAccount).addRecentLocalFile(originalPath,cacheFile2.toString(),newMsg.media.document);
          }
        }
      }
    }
 else {
      sentMessage.attachPath=newMsg.attachPath;
      sentMessage.message=newMsg.message;
    }
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaContact && newMsg.media instanceof TLRPC.TL_messageMediaContact) {
    newMsg.media=sentMessage.media;
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaWebPage) {
    newMsg.media=sentMessage.media;
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaGeo) {
    sentMessage.media.geo.lat=newMsg.media.geo.lat;
    sentMessage.media.geo._long=newMsg.media.geo._long;
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaGame) {
    newMsg.media=sentMessage.media;
    if (newMsg.media instanceof TLRPC.TL_messageMediaGame && !TextUtils.isEmpty(sentMessage.message)) {
      newMsg.entities=sentMessage.entities;
      newMsg.message=sentMessage.message;
    }
  }
 else   if (sentMessage.media instanceof TLRPC.TL_messageMediaPoll) {
    newMsg.media=sentMessage.media;
  }
}
