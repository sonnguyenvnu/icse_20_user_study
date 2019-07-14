@SuppressWarnings("unchecked") public void requestReference(Object parentObject,Object... args){
  String locationKey;
  TLRPC.InputFileLocation location;
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("start loading request reference for parent = " + parentObject + " args = " + args[0]);
  }
  if (args[0] instanceof TLRPC.TL_inputSingleMedia) {
    TLRPC.TL_inputSingleMedia req=(TLRPC.TL_inputSingleMedia)args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      locationKey="file_" + mediaDocument.id.id;
      location=new TLRPC.TL_inputDocumentFileLocation();
      location.id=mediaDocument.id.id;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      locationKey="photo_" + mediaPhoto.id.id;
      location=new TLRPC.TL_inputPhotoFileLocation();
      location.id=mediaPhoto.id.id;
    }
 else {
      sendErrorToObject(args,0);
      return;
    }
  }
 else   if (args[0] instanceof TLRPC.TL_messages_sendMultiMedia) {
    TLRPC.TL_messages_sendMultiMedia req=(TLRPC.TL_messages_sendMultiMedia)args[0];
    ArrayList<Object> parentObjects=(ArrayList<Object>)parentObject;
    multiMediaCache.put(req,args);
    for (int a=0, size=req.multi_media.size(); a < size; a++) {
      TLRPC.TL_inputSingleMedia media=req.multi_media.get(a);
      parentObject=parentObjects.get(a);
      if (parentObject == null) {
        continue;
      }
      requestReference(parentObject,media,req);
    }
    return;
  }
 else   if (args[0] instanceof TLRPC.TL_messages_sendMedia) {
    TLRPC.TL_messages_sendMedia req=(TLRPC.TL_messages_sendMedia)args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      locationKey="file_" + mediaDocument.id.id;
      location=new TLRPC.TL_inputDocumentFileLocation();
      location.id=mediaDocument.id.id;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      locationKey="photo_" + mediaPhoto.id.id;
      location=new TLRPC.TL_inputPhotoFileLocation();
      location.id=mediaPhoto.id.id;
    }
 else {
      sendErrorToObject(args,0);
      return;
    }
  }
 else   if (args[0] instanceof TLRPC.TL_messages_editMessage) {
    TLRPC.TL_messages_editMessage req=(TLRPC.TL_messages_editMessage)args[0];
    if (req.media instanceof TLRPC.TL_inputMediaDocument) {
      TLRPC.TL_inputMediaDocument mediaDocument=(TLRPC.TL_inputMediaDocument)req.media;
      locationKey="file_" + mediaDocument.id.id;
      location=new TLRPC.TL_inputDocumentFileLocation();
      location.id=mediaDocument.id.id;
    }
 else     if (req.media instanceof TLRPC.TL_inputMediaPhoto) {
      TLRPC.TL_inputMediaPhoto mediaPhoto=(TLRPC.TL_inputMediaPhoto)req.media;
      locationKey="photo_" + mediaPhoto.id.id;
      location=new TLRPC.TL_inputPhotoFileLocation();
      location.id=mediaPhoto.id.id;
    }
 else {
      sendErrorToObject(args,0);
      return;
    }
  }
 else   if (args[0] instanceof TLRPC.TL_messages_saveGif) {
    TLRPC.TL_messages_saveGif req=(TLRPC.TL_messages_saveGif)args[0];
    locationKey="file_" + req.id.id;
    location=new TLRPC.TL_inputDocumentFileLocation();
    location.id=req.id.id;
  }
 else   if (args[0] instanceof TLRPC.TL_messages_saveRecentSticker) {
    TLRPC.TL_messages_saveRecentSticker req=(TLRPC.TL_messages_saveRecentSticker)args[0];
    locationKey="file_" + req.id.id;
    location=new TLRPC.TL_inputDocumentFileLocation();
    location.id=req.id.id;
  }
 else   if (args[0] instanceof TLRPC.TL_messages_faveSticker) {
    TLRPC.TL_messages_faveSticker req=(TLRPC.TL_messages_faveSticker)args[0];
    locationKey="file_" + req.id.id;
    location=new TLRPC.TL_inputDocumentFileLocation();
    location.id=req.id.id;
  }
 else   if (args[0] instanceof TLRPC.TL_messages_getAttachedStickers) {
    TLRPC.TL_messages_getAttachedStickers req=(TLRPC.TL_messages_getAttachedStickers)args[0];
    if (req.media instanceof TLRPC.TL_inputStickeredMediaDocument) {
      TLRPC.TL_inputStickeredMediaDocument mediaDocument=(TLRPC.TL_inputStickeredMediaDocument)req.media;
      locationKey="file_" + mediaDocument.id.id;
      location=new TLRPC.TL_inputDocumentFileLocation();
      location.id=mediaDocument.id.id;
    }
 else     if (req.media instanceof TLRPC.TL_inputStickeredMediaPhoto) {
      TLRPC.TL_inputStickeredMediaPhoto mediaPhoto=(TLRPC.TL_inputStickeredMediaPhoto)req.media;
      locationKey="photo_" + mediaPhoto.id.id;
      location=new TLRPC.TL_inputPhotoFileLocation();
      location.id=mediaPhoto.id.id;
    }
 else {
      sendErrorToObject(args,0);
      return;
    }
  }
 else   if (args[0] instanceof TLRPC.TL_inputFileLocation) {
    location=(TLRPC.TL_inputFileLocation)args[0];
    locationKey="loc_" + location.local_id + "_" + location.volume_id;
  }
 else   if (args[0] instanceof TLRPC.TL_inputDocumentFileLocation) {
    location=(TLRPC.TL_inputDocumentFileLocation)args[0];
    locationKey="file_" + location.id;
  }
 else   if (args[0] instanceof TLRPC.TL_inputPhotoFileLocation) {
    location=(TLRPC.TL_inputPhotoFileLocation)args[0];
    locationKey="photo_" + location.id;
  }
 else {
    sendErrorToObject(args,0);
    return;
  }
  if (parentObject instanceof MessageObject) {
    MessageObject messageObject=(MessageObject)parentObject;
    if (messageObject.getRealId() < 0 && messageObject.messageOwner.media.webpage != null) {
      parentObject=messageObject.messageOwner.media.webpage;
    }
  }
  String parentKey=getKeyForParentObject(parentObject);
  if (parentKey == null) {
    sendErrorToObject(args,0);
    return;
  }
  Requester requester=new Requester();
  requester.args=args;
  requester.location=location;
  requester.locationKey=locationKey;
  int added=0;
  ArrayList<Requester> arrayList=locationRequester.get(locationKey);
  if (arrayList == null) {
    arrayList=new ArrayList<>();
    locationRequester.put(locationKey,arrayList);
    added++;
  }
  arrayList.add(requester);
  arrayList=parentRequester.get(parentKey);
  if (arrayList == null) {
    arrayList=new ArrayList<>();
    parentRequester.put(parentKey,arrayList);
    added++;
  }
  arrayList.add(requester);
  if (added != 2) {
    return;
  }
  cleanupCache();
  CachedResult cachedResult=getCachedResponse(locationKey);
  if (cachedResult != null) {
    if (!onRequestComplete(locationKey,parentKey,cachedResult.response,false)) {
      responseCache.remove(locationKey);
    }
 else {
      return;
    }
  }
 else {
    cachedResult=getCachedResponse(parentKey);
    if (cachedResult != null) {
      if (!onRequestComplete(locationKey,parentKey,cachedResult.response,false)) {
        responseCache.remove(parentKey);
      }
 else {
        return;
      }
    }
  }
  requestReferenceFromServer(parentObject,locationKey,parentKey,args);
}
