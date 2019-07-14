public void sendSticker(TLRPC.Document document,long peer,MessageObject replyingMessageObject,Object parentObject){
  if (document == null) {
    return;
  }
  if ((int)peer == 0) {
    int high_id=(int)(peer >> 32);
    TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
    if (encryptedChat == null) {
      return;
    }
    TLRPC.TL_document_layer82 newDocument=new TLRPC.TL_document_layer82();
    newDocument.id=document.id;
    newDocument.access_hash=document.access_hash;
    newDocument.date=document.date;
    newDocument.mime_type=document.mime_type;
    newDocument.file_reference=document.file_reference;
    if (newDocument.file_reference == null) {
      newDocument.file_reference=new byte[0];
    }
    newDocument.size=document.size;
    newDocument.dc_id=document.dc_id;
    newDocument.attributes=new ArrayList<>(document.attributes);
    if (newDocument.mime_type == null) {
      newDocument.mime_type="";
    }
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    if (thumb instanceof TLRPC.TL_photoSize) {
      File file=FileLoader.getPathToAttach(thumb,true);
      if (file.exists()) {
        try {
          int len=(int)file.length();
          byte[] arr=new byte[(int)file.length()];
          RandomAccessFile reader=new RandomAccessFile(file,"r");
          reader.readFully(arr);
          TLRPC.PhotoSize newThumb=new TLRPC.TL_photoCachedSize();
          TLRPC.TL_fileLocation_layer82 fileLocation=new TLRPC.TL_fileLocation_layer82();
          fileLocation.dc_id=thumb.location.dc_id;
          fileLocation.volume_id=thumb.location.volume_id;
          fileLocation.local_id=thumb.location.local_id;
          fileLocation.secret=thumb.location.secret;
          newThumb.location=fileLocation;
          newThumb.size=thumb.size;
          newThumb.w=thumb.w;
          newThumb.h=thumb.h;
          newThumb.type=thumb.type;
          newThumb.bytes=arr;
          newDocument.thumbs.add(newThumb);
          newDocument.flags|=1;
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
    if (newDocument.thumbs.isEmpty()) {
      thumb=new TLRPC.TL_photoSizeEmpty();
      thumb.type="s";
      newDocument.thumbs.add(thumb);
    }
    document=newDocument;
  }
  if (document instanceof TLRPC.TL_document) {
    sendMessage((TLRPC.TL_document)document,null,null,peer,replyingMessageObject,null,null,null,null,0,parentObject);
  }
}
