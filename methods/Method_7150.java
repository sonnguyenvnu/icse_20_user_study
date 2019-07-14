public void processForwardFromMyName(MessageObject messageObject,long did){
  if (messageObject == null) {
    return;
  }
  if (messageObject.messageOwner.media != null && !(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaEmpty) && !(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) && !(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame) && !(messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice)) {
    HashMap<String,String> params=null;
    if ((int)did == 0 && messageObject.messageOwner.to_id != null && (messageObject.messageOwner.media.photo instanceof TLRPC.TL_photo || messageObject.messageOwner.media.document instanceof TLRPC.TL_document)) {
      params=new HashMap<>();
      params.put("parentObject","sent_" + messageObject.messageOwner.to_id.channel_id + "_" + messageObject.getId());
    }
    if (messageObject.messageOwner.media.photo instanceof TLRPC.TL_photo) {
      sendMessage((TLRPC.TL_photo)messageObject.messageOwner.media.photo,null,did,messageObject.replyMessageObject,messageObject.messageOwner.message,messageObject.messageOwner.entities,null,params,messageObject.messageOwner.media.ttl_seconds,messageObject);
    }
 else     if (messageObject.messageOwner.media.document instanceof TLRPC.TL_document) {
      sendMessage((TLRPC.TL_document)messageObject.messageOwner.media.document,null,messageObject.messageOwner.attachPath,did,messageObject.replyMessageObject,messageObject.messageOwner.message,messageObject.messageOwner.entities,null,params,messageObject.messageOwner.media.ttl_seconds,messageObject);
    }
 else     if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaVenue || messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo) {
      sendMessage(messageObject.messageOwner.media,did,messageObject.replyMessageObject,null,null);
    }
 else     if (messageObject.messageOwner.media.phone_number != null) {
      TLRPC.User user=new TLRPC.TL_userContact_old2();
      user.phone=messageObject.messageOwner.media.phone_number;
      user.first_name=messageObject.messageOwner.media.first_name;
      user.last_name=messageObject.messageOwner.media.last_name;
      user.id=messageObject.messageOwner.media.user_id;
      sendMessage(user,did,messageObject.replyMessageObject,null,null);
    }
 else     if ((int)did != 0) {
      ArrayList<MessageObject> arrayList=new ArrayList<>();
      arrayList.add(messageObject);
      sendMessage(arrayList,did);
    }
  }
 else   if (messageObject.messageOwner.message != null) {
    TLRPC.WebPage webPage=null;
    if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
      webPage=messageObject.messageOwner.media.webpage;
    }
    ArrayList<TLRPC.MessageEntity> entities;
    if (messageObject.messageOwner.entities != null && !messageObject.messageOwner.entities.isEmpty()) {
      entities=new ArrayList<>();
      for (int a=0; a < messageObject.messageOwner.entities.size(); a++) {
        TLRPC.MessageEntity entity=messageObject.messageOwner.entities.get(a);
        if (entity instanceof TLRPC.TL_messageEntityBold || entity instanceof TLRPC.TL_messageEntityItalic || entity instanceof TLRPC.TL_messageEntityPre || entity instanceof TLRPC.TL_messageEntityCode || entity instanceof TLRPC.TL_messageEntityTextUrl) {
          entities.add(entity);
        }
      }
    }
 else {
      entities=null;
    }
    sendMessage(messageObject.messageOwner.message,did,messageObject.replyMessageObject,webPage,true,entities,null,null);
  }
 else   if ((int)did != 0) {
    ArrayList<MessageObject> arrayList=new ArrayList<>();
    arrayList.add(messageObject);
    sendMessage(arrayList,did);
  }
}
