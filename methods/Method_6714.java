private MessageObject getMessageObjectForBlock(TLRPC.WebPage webPage,TLRPC.PageBlock pageBlock){
  TLRPC.TL_message message=null;
  if (pageBlock instanceof TLRPC.TL_pageBlockPhoto) {
    TLRPC.TL_pageBlockPhoto pageBlockPhoto=(TLRPC.TL_pageBlockPhoto)pageBlock;
    TLRPC.Photo photo=getPhotoWithId(webPage,pageBlockPhoto.photo_id);
    if (photo == webPage.photo) {
      return this;
    }
    message=new TLRPC.TL_message();
    message.media=new TLRPC.TL_messageMediaPhoto();
    message.media.photo=photo;
  }
 else   if (pageBlock instanceof TLRPC.TL_pageBlockVideo) {
    TLRPC.TL_pageBlockVideo pageBlockVideo=(TLRPC.TL_pageBlockVideo)pageBlock;
    TLRPC.Document document=getDocumentWithId(webPage,pageBlockVideo.video_id);
    if (document == webPage.document) {
      return this;
    }
    message=new TLRPC.TL_message();
    message.media=new TLRPC.TL_messageMediaDocument();
    message.media.document=getDocumentWithId(webPage,pageBlockVideo.video_id);
  }
  message.message="";
  message.realId=getId();
  message.id=Utilities.random.nextInt();
  message.date=messageOwner.date;
  message.to_id=messageOwner.to_id;
  message.out=messageOwner.out;
  message.from_id=messageOwner.from_id;
  return new MessageObject(currentAccount,message,false);
}
