private void updateMediaPaths(MessageObject newMsgObj,TLRPC.EncryptedFile file,TLRPC.DecryptedMessage decryptedMessage,String originalPath){
  TLRPC.Message newMsg=newMsgObj.messageOwner;
  if (file != null) {
    if (newMsg.media instanceof TLRPC.TL_messageMediaPhoto && newMsg.media.photo != null) {
      TLRPC.PhotoSize size=newMsg.media.photo.sizes.get(newMsg.media.photo.sizes.size() - 1);
      String fileName=size.location.volume_id + "_" + size.location.local_id;
      size.location=new TLRPC.TL_fileEncryptedLocation();
      size.location.key=decryptedMessage.media.key;
      size.location.iv=decryptedMessage.media.iv;
      size.location.dc_id=file.dc_id;
      size.location.volume_id=file.id;
      size.location.secret=file.access_hash;
      size.location.local_id=file.key_fingerprint;
      String fileName2=size.location.volume_id + "_" + size.location.local_id;
      File cacheFile=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),fileName + ".jpg");
      File cacheFile2=FileLoader.getPathToAttach(size);
      cacheFile.renameTo(cacheFile2);
      ImageLoader.getInstance().replaceImageInCache(fileName,fileName2,ImageLocation.getForPhoto(size,newMsg.media.photo),true);
      ArrayList<TLRPC.Message> arr=new ArrayList<>();
      arr.add(newMsg);
      MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
    }
 else     if (newMsg.media instanceof TLRPC.TL_messageMediaDocument && newMsg.media.document != null) {
      TLRPC.Document document=newMsg.media.document;
      newMsg.media.document=new TLRPC.TL_documentEncrypted();
      newMsg.media.document.id=file.id;
      newMsg.media.document.access_hash=file.access_hash;
      newMsg.media.document.date=document.date;
      newMsg.media.document.attributes=document.attributes;
      newMsg.media.document.mime_type=document.mime_type;
      newMsg.media.document.size=file.size;
      newMsg.media.document.key=decryptedMessage.media.key;
      newMsg.media.document.iv=decryptedMessage.media.iv;
      newMsg.media.document.thumbs=document.thumbs;
      newMsg.media.document.dc_id=file.dc_id;
      if (newMsg.media.document.thumbs.isEmpty()) {
        TLRPC.PhotoSize thumb=new TLRPC.TL_photoSizeEmpty();
        thumb.type="s";
        newMsg.media.document.thumbs.add(thumb);
      }
      if (newMsg.attachPath != null && newMsg.attachPath.startsWith(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE).getAbsolutePath())) {
        File cacheFile=new File(newMsg.attachPath);
        File cacheFile2=FileLoader.getPathToAttach(newMsg.media.document);
        if (cacheFile.renameTo(cacheFile2)) {
          newMsgObj.mediaExists=newMsgObj.attachPathExists;
          newMsgObj.attachPathExists=false;
          newMsg.attachPath="";
        }
      }
      ArrayList<TLRPC.Message> arr=new ArrayList<>();
      arr.add(newMsg);
      MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
    }
  }
}
