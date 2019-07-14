private void requestReferenceFromServer(Object parentObject,String locationKey,String parentKey,Object[] args){
  if (parentObject instanceof MessageObject) {
    MessageObject messageObject=(MessageObject)parentObject;
    int channelId=messageObject.getChannelId();
    if (channelId != 0) {
      TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
      req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelId);
      req.id.add(messageObject.getRealId());
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else {
      TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
      req.id.add(messageObject.getRealId());
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
  }
 else   if (parentObject instanceof TLRPC.TL_wallPaper) {
    TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)parentObject;
    TLRPC.TL_account_getWallPaper req=new TLRPC.TL_account_getWallPaper();
    TLRPC.TL_inputWallPaper inputWallPaper=new TLRPC.TL_inputWallPaper();
    inputWallPaper.id=wallPaper.id;
    inputWallPaper.access_hash=wallPaper.access_hash;
    req.wallpaper=inputWallPaper;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else   if (parentObject instanceof TLRPC.WebPage) {
    TLRPC.WebPage webPage=(TLRPC.WebPage)parentObject;
    TLRPC.TL_messages_getWebPage req=new TLRPC.TL_messages_getWebPage();
    req.url=webPage.url;
    req.hash=0;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else   if (parentObject instanceof TLRPC.User) {
    TLRPC.User user=(TLRPC.User)parentObject;
    TLRPC.TL_users_getUsers req=new TLRPC.TL_users_getUsers();
    req.id.add(MessagesController.getInstance(currentAccount).getInputUser(user));
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else   if (parentObject instanceof TLRPC.Chat) {
    TLRPC.Chat chat=(TLRPC.Chat)parentObject;
    if (chat instanceof TLRPC.TL_chat) {
      TLRPC.TL_messages_getChats req=new TLRPC.TL_messages_getChats();
      req.id.add(chat.id);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else     if (chat instanceof TLRPC.TL_channel) {
      TLRPC.TL_channels_getChannels req=new TLRPC.TL_channels_getChannels();
      req.id.add(MessagesController.getInputChannel(chat));
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
  }
 else   if (parentObject instanceof String) {
    String string=(String)parentObject;
    if ("wallpaper".equals(string)) {
      TLRPC.TL_account_getWallPapers req=new TLRPC.TL_account_getWallPapers();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else     if (string.startsWith("gif")) {
      TLRPC.TL_messages_getSavedGifs req=new TLRPC.TL_messages_getSavedGifs();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else     if ("recent".equals(string)) {
      TLRPC.TL_messages_getRecentStickers req=new TLRPC.TL_messages_getRecentStickers();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else     if ("fav".equals(string)) {
      TLRPC.TL_messages_getFavedStickers req=new TLRPC.TL_messages_getFavedStickers();
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
    }
 else     if (string.startsWith("avatar_")) {
      int id=Utilities.parseInt(string);
      if (id > 0) {
        TLRPC.TL_photos_getUserPhotos req=new TLRPC.TL_photos_getUserPhotos();
        req.limit=80;
        req.offset=0;
        req.max_id=0;
        req.user_id=MessagesController.getInstance(currentAccount).getInputUser(id);
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
      }
 else {
        TLRPC.TL_messages_search req=new TLRPC.TL_messages_search();
        req.filter=new TLRPC.TL_inputMessagesFilterChatPhotos();
        req.limit=80;
        req.offset_id=0;
        req.q="";
        req.peer=MessagesController.getInstance(currentAccount).getInputPeer(id);
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
      }
    }
 else     if (string.startsWith("sent_")) {
      String[] params=string.split("_");
      if (params.length == 3) {
        int channelId=Utilities.parseInt(params[1]);
        if (channelId != 0) {
          TLRPC.TL_channels_getMessages req=new TLRPC.TL_channels_getMessages();
          req.channel=MessagesController.getInstance(currentAccount).getInputChannel(channelId);
          req.id.add(Utilities.parseInt(params[2]));
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,false));
        }
 else {
          TLRPC.TL_messages_getMessages req=new TLRPC.TL_messages_getMessages();
          req.id.add(Utilities.parseInt(params[2]));
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,false));
        }
      }
 else {
        sendErrorToObject(args,0);
      }
    }
 else {
      sendErrorToObject(args,0);
    }
  }
 else   if (parentObject instanceof TLRPC.TL_messages_stickerSet) {
    TLRPC.TL_messages_stickerSet stickerSet=(TLRPC.TL_messages_stickerSet)parentObject;
    TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
    req.stickerset=new TLRPC.TL_inputStickerSetID();
    req.stickerset.id=stickerSet.set.id;
    req.stickerset.access_hash=stickerSet.set.access_hash;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else   if (parentObject instanceof TLRPC.StickerSetCovered) {
    TLRPC.StickerSetCovered stickerSet=(TLRPC.StickerSetCovered)parentObject;
    TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
    req.stickerset=new TLRPC.TL_inputStickerSetID();
    req.stickerset.id=stickerSet.set.id;
    req.stickerset.access_hash=stickerSet.set.access_hash;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else   if (parentObject instanceof TLRPC.InputStickerSet) {
    TLRPC.TL_messages_getStickerSet req=new TLRPC.TL_messages_getStickerSet();
    req.stickerset=(TLRPC.InputStickerSet)parentObject;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> onRequestComplete(locationKey,parentKey,response,true));
  }
 else {
    sendErrorToObject(args,0);
  }
}
