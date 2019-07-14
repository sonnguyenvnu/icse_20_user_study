private void editMessageMedia(MessageObject messageObject,TLRPC.TL_photo photo,VideoEditedInfo videoEditedInfo,TLRPC.TL_document document,String path,HashMap<String,String> params,boolean retry,Object parentObject){
  if (messageObject == null) {
    return;
  }
  TLRPC.Message newMsg=messageObject.messageOwner;
  messageObject.cancelEditing=false;
  try {
    int type=-1;
    DelayedMessage delayedMessage=null;
    long peer=messageObject.getDialogId();
    if (retry) {
      if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
        photo=(TLRPC.TL_photo)messageObject.messageOwner.media.photo;
        type=2;
      }
 else {
        document=(TLRPC.TL_document)messageObject.messageOwner.media.document;
        if (MessageObject.isVideoDocument(document) || videoEditedInfo != null) {
          type=3;
        }
 else {
          type=7;
        }
        videoEditedInfo=messageObject.videoEditedInfo;
      }
      params=newMsg.params;
      messageObject.editingMessage=newMsg.message;
      messageObject.editingMessageEntities=newMsg.entities;
      path=newMsg.attachPath;
    }
 else {
      messageObject.previousMedia=newMsg.media;
      messageObject.previousCaption=newMsg.message;
      messageObject.previousCaptionEntities=newMsg.entities;
      messageObject.previousAttachPath=newMsg.attachPath;
      SerializedData serializedDataCalc=new SerializedData(true);
      writePreviousMessageData(newMsg,serializedDataCalc);
      SerializedData prevMessageData=new SerializedData(serializedDataCalc.length());
      writePreviousMessageData(newMsg,prevMessageData);
      if (params == null) {
        params=new HashMap<>();
      }
      params.put("prevMedia",Base64.encodeToString(prevMessageData.toByteArray(),Base64.DEFAULT));
      prevMessageData.cleanup();
      if (photo != null) {
        newMsg.media=new TLRPC.TL_messageMediaPhoto();
        newMsg.media.flags|=3;
        newMsg.media.photo=photo;
        type=2;
        if (path != null && path.length() > 0 && path.startsWith("http")) {
          newMsg.attachPath=path;
        }
 else {
          TLRPC.FileLocation location1=photo.sizes.get(photo.sizes.size() - 1).location;
          newMsg.attachPath=FileLoader.getPathToAttach(location1,true).toString();
        }
      }
 else       if (document != null) {
        newMsg.media=new TLRPC.TL_messageMediaDocument();
        newMsg.media.flags|=3;
        newMsg.media.document=document;
        if (MessageObject.isVideoDocument(document) || videoEditedInfo != null) {
          type=3;
        }
 else {
          type=7;
        }
        if (videoEditedInfo != null) {
          String ve=videoEditedInfo.getString();
          params.put("ve",ve);
        }
        newMsg.attachPath=path;
      }
      newMsg.params=params;
      newMsg.send_state=MessageObject.MESSAGE_SEND_STATE_EDITING;
    }
    if (newMsg.attachPath == null) {
      newMsg.attachPath="";
    }
    newMsg.local_id=0;
    if ((messageObject.type == 3 || videoEditedInfo != null || messageObject.type == 2) && !TextUtils.isEmpty(newMsg.attachPath)) {
      messageObject.attachPathExists=true;
    }
    if (messageObject.videoEditedInfo != null && videoEditedInfo == null) {
      videoEditedInfo=messageObject.videoEditedInfo;
    }
    if (!retry) {
      if (messageObject.editingMessage != null) {
        newMsg.message=messageObject.editingMessage.toString();
        if (messageObject.editingMessageEntities != null) {
          newMsg.entities=messageObject.editingMessageEntities;
        }
 else {
          CharSequence[] message=new CharSequence[]{messageObject.editingMessage};
          ArrayList<TLRPC.MessageEntity> entities=DataQuery.getInstance(currentAccount).getEntities(message);
          if (entities != null && !entities.isEmpty()) {
            newMsg.entities=entities;
          }
        }
        messageObject.caption=null;
        messageObject.generateCaption();
      }
      ArrayList<TLRPC.Message> arr=new ArrayList<>();
      arr.add(newMsg);
      MessagesStorage.getInstance(currentAccount).putMessages(arr,false,true,false,0);
      messageObject.type=-1;
      messageObject.setType();
      messageObject.createMessageSendInfo();
      ArrayList<MessageObject> arrayList=new ArrayList<>();
      arrayList.add(messageObject);
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,peer,arrayList);
    }
    String originalPath=null;
    if (params != null && params.containsKey("originalPath")) {
      originalPath=params.get("originalPath");
    }
    boolean performMediaUpload=false;
    if (type >= 1 && type <= 3 || type >= 5 && type <= 8) {
      TLRPC.InputMedia inputMedia=null;
      if (type == 2) {
        TLRPC.TL_inputMediaUploadedPhoto uploadedPhoto=new TLRPC.TL_inputMediaUploadedPhoto();
        if (params != null) {
          String masks=params.get("masks");
          if (masks != null) {
            SerializedData serializedData=new SerializedData(Utilities.hexToBytes(masks));
            int count=serializedData.readInt32(false);
            for (int a=0; a < count; a++) {
              uploadedPhoto.stickers.add(TLRPC.InputDocument.TLdeserialize(serializedData,serializedData.readInt32(false),false));
            }
            uploadedPhoto.flags|=1;
            serializedData.cleanup();
          }
        }
        if (photo.access_hash == 0) {
          inputMedia=uploadedPhoto;
          performMediaUpload=true;
        }
 else {
          TLRPC.TL_inputMediaPhoto media=new TLRPC.TL_inputMediaPhoto();
          media.id=new TLRPC.TL_inputPhoto();
          media.id.id=photo.id;
          media.id.access_hash=photo.access_hash;
          media.id.file_reference=photo.file_reference;
          if (media.id.file_reference == null) {
            media.id.file_reference=new byte[0];
          }
          inputMedia=media;
        }
        delayedMessage=new DelayedMessage(peer);
        delayedMessage.type=0;
        delayedMessage.obj=messageObject;
        delayedMessage.originalPath=originalPath;
        delayedMessage.parentObject=parentObject;
        delayedMessage.inputUploadMedia=uploadedPhoto;
        delayedMessage.performMediaUpload=performMediaUpload;
        if (path != null && path.length() > 0 && path.startsWith("http")) {
          delayedMessage.httpLocation=path;
        }
 else {
          delayedMessage.photoSize=photo.sizes.get(photo.sizes.size() - 1);
          delayedMessage.locationParent=photo;
        }
      }
 else       if (type == 3) {
        TLRPC.TL_inputMediaUploadedDocument uploadedDocument=new TLRPC.TL_inputMediaUploadedDocument();
        uploadedDocument.mime_type=document.mime_type;
        uploadedDocument.attributes=document.attributes;
        if (!messageObject.isGif() && (videoEditedInfo == null || !videoEditedInfo.muted)) {
          uploadedDocument.nosound_video=true;
          if (BuildVars.DEBUG_VERSION) {
            FileLog.d("nosound_video = true");
          }
        }
        if (document.access_hash == 0) {
          inputMedia=uploadedDocument;
          performMediaUpload=true;
        }
 else {
          TLRPC.TL_inputMediaDocument media=new TLRPC.TL_inputMediaDocument();
          media.id=new TLRPC.TL_inputDocument();
          media.id.id=document.id;
          media.id.access_hash=document.access_hash;
          media.id.file_reference=document.file_reference;
          if (media.id.file_reference == null) {
            media.id.file_reference=new byte[0];
          }
          inputMedia=media;
        }
        delayedMessage=new DelayedMessage(peer);
        delayedMessage.type=1;
        delayedMessage.obj=messageObject;
        delayedMessage.originalPath=originalPath;
        delayedMessage.parentObject=parentObject;
        delayedMessage.inputUploadMedia=uploadedDocument;
        delayedMessage.performMediaUpload=performMediaUpload;
        if (!document.thumbs.isEmpty()) {
          delayedMessage.photoSize=document.thumbs.get(0);
          delayedMessage.locationParent=document;
        }
        delayedMessage.videoEditedInfo=videoEditedInfo;
      }
 else       if (type == 7) {
        boolean http=false;
        TLRPC.InputMedia uploadedDocument;
        if (originalPath != null && originalPath.length() > 0 && originalPath.startsWith("http") && params != null) {
          http=true;
          TLRPC.TL_inputMediaGifExternal gifExternal=new TLRPC.TL_inputMediaGifExternal();
          String args[]=params.get("url").split("\\|");
          if (args.length == 2) {
            gifExternal.url=args[0];
            gifExternal.q=args[1];
          }
          uploadedDocument=gifExternal;
        }
 else {
          uploadedDocument=new TLRPC.TL_inputMediaUploadedDocument();
        }
        uploadedDocument.mime_type=document.mime_type;
        uploadedDocument.attributes=document.attributes;
        if (document.access_hash == 0) {
          inputMedia=uploadedDocument;
          performMediaUpload=uploadedDocument instanceof TLRPC.TL_inputMediaUploadedDocument;
        }
 else {
          TLRPC.TL_inputMediaDocument media=new TLRPC.TL_inputMediaDocument();
          media.id=new TLRPC.TL_inputDocument();
          media.id.id=document.id;
          media.id.access_hash=document.access_hash;
          media.id.file_reference=document.file_reference;
          if (media.id.file_reference == null) {
            media.id.file_reference=new byte[0];
          }
          inputMedia=media;
        }
        if (!http) {
          delayedMessage=new DelayedMessage(peer);
          delayedMessage.originalPath=originalPath;
          delayedMessage.type=2;
          delayedMessage.obj=messageObject;
          if (!document.thumbs.isEmpty()) {
            delayedMessage.photoSize=document.thumbs.get(0);
            delayedMessage.locationParent=document;
          }
          delayedMessage.parentObject=parentObject;
          delayedMessage.inputUploadMedia=uploadedDocument;
          delayedMessage.performMediaUpload=performMediaUpload;
        }
      }
      TLObject reqSend;
      TLRPC.TL_messages_editMessage request=new TLRPC.TL_messages_editMessage();
      request.id=messageObject.getId();
      request.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)peer);
      request.flags|=16384;
      request.media=inputMedia;
      if (messageObject.editingMessage != null) {
        request.message=messageObject.editingMessage.toString();
        request.flags|=2048;
        if (messageObject.editingMessageEntities != null) {
          request.entities=messageObject.editingMessageEntities;
          request.flags|=8;
        }
 else {
          CharSequence[] message=new CharSequence[]{messageObject.editingMessage};
          ArrayList<TLRPC.MessageEntity> entities=DataQuery.getInstance(currentAccount).getEntities(message);
          if (entities != null && !entities.isEmpty()) {
            request.entities=entities;
            request.flags|=8;
          }
        }
        messageObject.editingMessage=null;
        messageObject.editingMessageEntities=null;
      }
      if (delayedMessage != null) {
        delayedMessage.sendRequest=request;
      }
      reqSend=request;
      if (type == 1) {
        performSendMessageRequest(reqSend,messageObject,null,delayedMessage,parentObject);
      }
 else       if (type == 2) {
        if (performMediaUpload) {
          performSendDelayedMessage(delayedMessage);
        }
 else {
          performSendMessageRequest(reqSend,messageObject,originalPath,null,true,delayedMessage,parentObject);
        }
      }
 else       if (type == 3) {
        if (performMediaUpload) {
          performSendDelayedMessage(delayedMessage);
        }
 else {
          performSendMessageRequest(reqSend,messageObject,originalPath,delayedMessage,parentObject);
        }
      }
 else       if (type == 6) {
        performSendMessageRequest(reqSend,messageObject,originalPath,delayedMessage,parentObject);
      }
 else       if (type == 7) {
        if (performMediaUpload) {
          performSendDelayedMessage(delayedMessage);
        }
 else {
          performSendMessageRequest(reqSend,messageObject,originalPath,delayedMessage,parentObject);
        }
      }
 else       if (type == 8) {
        if (performMediaUpload) {
          performSendDelayedMessage(delayedMessage);
        }
 else {
          performSendMessageRequest(reqSend,messageObject,originalPath,delayedMessage,parentObject);
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
    revertEditingMessageObject(messageObject);
  }
}
