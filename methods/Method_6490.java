private boolean onRequestComplete(String locationKey,String parentKey,TLObject response,boolean cache){
  boolean found=false;
  if (parentKey != null) {
    ArrayList<Requester> arrayList=parentRequester.get(parentKey);
    if (arrayList != null) {
      for (int q=0, N=arrayList.size(); q < N; q++) {
        Requester requester=arrayList.get(q);
        if (requester.completed) {
          continue;
        }
        if (onRequestComplete(requester.locationKey,null,response,cache && !found)) {
          found=true;
        }
      }
      if (found) {
        putReponseToCache(parentKey,response);
      }
      parentRequester.remove(parentKey);
    }
  }
  byte result[]=null;
  TLRPC.InputFileLocation[] locationReplacement=null;
  boolean needReplacement[]=null;
  ArrayList<Requester> arrayList=locationRequester.get(locationKey);
  if (arrayList == null) {
    return found;
  }
  for (int q=0, N=arrayList.size(); q < N; q++) {
    Requester requester=arrayList.get(q);
    if (requester.completed) {
      continue;
    }
    if (requester.location instanceof TLRPC.TL_inputFileLocation) {
      locationReplacement=new TLRPC.InputFileLocation[1];
      needReplacement=new boolean[1];
    }
    requester.completed=true;
    if (response instanceof TLRPC.messages_Messages) {
      TLRPC.messages_Messages res=(TLRPC.messages_Messages)response;
      if (!res.messages.isEmpty()) {
        for (int i=0, size3=res.messages.size(); i < size3; i++) {
          TLRPC.Message message=res.messages.get(i);
          if (message.media != null) {
            if (message.media.document != null) {
              result=getFileReference(message.media.document,requester.location,needReplacement,locationReplacement);
            }
 else             if (message.media.game != null) {
              result=getFileReference(message.media.game.document,requester.location,needReplacement,locationReplacement);
              if (result == null) {
                result=getFileReference(message.media.game.photo,requester.location,needReplacement,locationReplacement);
              }
            }
 else             if (message.media.photo != null) {
              result=getFileReference(message.media.photo,requester.location,needReplacement,locationReplacement);
            }
 else             if (message.media.webpage != null) {
              result=getFileReference(message.media.webpage,requester.location,needReplacement,locationReplacement);
            }
          }
 else           if (message.action instanceof TLRPC.TL_messageActionChatEditPhoto) {
            result=getFileReference(message.action.photo,requester.location,needReplacement,locationReplacement);
          }
          if (result != null) {
            if (cache) {
              if (message.to_id != null && message.to_id.channel_id != 0) {
                for (int a=0, N2=res.chats.size(); a < N2; a++) {
                  TLRPC.Chat chat=res.chats.get(a);
                  if (chat.id == message.to_id.channel_id) {
                    if (chat.megagroup) {
                      message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                    }
                    break;
                  }
                }
              }
              MessagesStorage.getInstance(currentAccount).replaceMessageIfExists(message,currentAccount,res.users,res.chats,false);
            }
            break;
          }
        }
        if (result == null) {
          MessagesStorage.getInstance(currentAccount).replaceMessageIfExists(res.messages.get(0),currentAccount,res.users,res.chats,true);
          if (BuildVars.DEBUG_VERSION) {
            FileLog.d("file ref not found in messages, replacing message");
          }
        }
      }
    }
 else     if (response instanceof TLRPC.WebPage) {
      result=getFileReference((TLRPC.WebPage)response,requester.location,needReplacement,locationReplacement);
    }
 else     if (response instanceof TLRPC.TL_account_wallPapers) {
      TLRPC.TL_account_wallPapers accountWallPapers=(TLRPC.TL_account_wallPapers)response;
      for (int i=0, size10=accountWallPapers.wallpapers.size(); i < size10; i++) {
        result=getFileReference(((TLRPC.TL_wallPaper)accountWallPapers.wallpapers.get(i)).document,requester.location,needReplacement,locationReplacement);
        if (result != null) {
          break;
        }
      }
      if (result != null && cache) {
        MessagesStorage.getInstance(currentAccount).putWallpapers(accountWallPapers.wallpapers,1);
      }
    }
 else     if (response instanceof TLRPC.TL_wallPaper) {
      TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)response;
      result=getFileReference(wallPaper.document,requester.location,needReplacement,locationReplacement);
      if (result != null && cache) {
        ArrayList<TLRPC.WallPaper> wallpapers=new ArrayList<>();
        wallpapers.add(wallPaper);
        MessagesStorage.getInstance(currentAccount).putWallpapers(wallpapers,0);
      }
    }
 else     if (response instanceof TLRPC.Vector) {
      TLRPC.Vector vector=(TLRPC.Vector)response;
      if (!vector.objects.isEmpty()) {
        for (int i=0, size10=vector.objects.size(); i < size10; i++) {
          Object object=vector.objects.get(i);
          if (object instanceof TLRPC.User) {
            TLRPC.User user=(TLRPC.User)object;
            result=getFileReference(user,requester.location,needReplacement,locationReplacement);
            if (cache && result != null) {
              ArrayList<TLRPC.User> arrayList1=new ArrayList<>();
              arrayList1.add(user);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(arrayList1,null,true,true);
              AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).putUser(user,false));
            }
          }
 else           if (object instanceof TLRPC.Chat) {
            TLRPC.Chat chat=(TLRPC.Chat)object;
            result=getFileReference(chat,requester.location,needReplacement,locationReplacement);
            if (cache && result != null) {
              ArrayList<TLRPC.Chat> arrayList1=new ArrayList<>();
              arrayList1.add(chat);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,arrayList1,true,true);
              AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).putChat(chat,false));
            }
          }
          if (result != null) {
            break;
          }
        }
      }
    }
 else     if (response instanceof TLRPC.TL_messages_chats) {
      TLRPC.TL_messages_chats res=(TLRPC.TL_messages_chats)response;
      if (!res.chats.isEmpty()) {
        for (int i=0, size10=res.chats.size(); i < size10; i++) {
          TLRPC.Chat chat=res.chats.get(i);
          result=getFileReference(chat,requester.location,needReplacement,locationReplacement);
          if (result != null) {
            if (cache) {
              ArrayList<TLRPC.Chat> arrayList1=new ArrayList<>();
              arrayList1.add(chat);
              MessagesStorage.getInstance(currentAccount).putUsersAndChats(null,arrayList1,true,true);
              AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).putChat(chat,false));
            }
            break;
          }
        }
      }
    }
 else     if (response instanceof TLRPC.TL_messages_savedGifs) {
      TLRPC.TL_messages_savedGifs savedGifs=(TLRPC.TL_messages_savedGifs)response;
      for (int b=0, size2=savedGifs.gifs.size(); b < size2; b++) {
        result=getFileReference(savedGifs.gifs.get(b),requester.location,needReplacement,locationReplacement);
        if (result != null) {
          break;
        }
      }
      if (cache) {
        DataQuery.getInstance(currentAccount).processLoadedRecentDocuments(DataQuery.TYPE_IMAGE,savedGifs.gifs,true,0,true);
      }
    }
 else     if (response instanceof TLRPC.TL_messages_stickerSet) {
      TLRPC.TL_messages_stickerSet stickerSet=(TLRPC.TL_messages_stickerSet)response;
      if (result == null) {
        for (int b=0, size2=stickerSet.documents.size(); b < size2; b++) {
          result=getFileReference(stickerSet.documents.get(b),requester.location,needReplacement,locationReplacement);
          if (result != null) {
            break;
          }
        }
      }
      if (cache) {
        AndroidUtilities.runOnUIThread(() -> DataQuery.getInstance(currentAccount).replaceStickerSet(stickerSet));
      }
    }
 else     if (response instanceof TLRPC.TL_messages_recentStickers) {
      TLRPC.TL_messages_recentStickers recentStickers=(TLRPC.TL_messages_recentStickers)response;
      for (int b=0, size2=recentStickers.stickers.size(); b < size2; b++) {
        result=getFileReference(recentStickers.stickers.get(b),requester.location,needReplacement,locationReplacement);
        if (result != null) {
          break;
        }
      }
      if (cache) {
        DataQuery.getInstance(currentAccount).processLoadedRecentDocuments(DataQuery.TYPE_IMAGE,recentStickers.stickers,false,0,true);
      }
    }
 else     if (response instanceof TLRPC.TL_messages_favedStickers) {
      TLRPC.TL_messages_favedStickers favedStickers=(TLRPC.TL_messages_favedStickers)response;
      for (int b=0, size2=favedStickers.stickers.size(); b < size2; b++) {
        result=getFileReference(favedStickers.stickers.get(b),requester.location,needReplacement,locationReplacement);
        if (result != null) {
          break;
        }
      }
      if (cache) {
        DataQuery.getInstance(currentAccount).processLoadedRecentDocuments(DataQuery.TYPE_FAVE,favedStickers.stickers,false,0,true);
      }
    }
 else     if (response instanceof TLRPC.photos_Photos) {
      TLRPC.photos_Photos res=(TLRPC.photos_Photos)response;
      for (int b=0, size=res.photos.size(); b < size; b++) {
        result=getFileReference(res.photos.get(b),requester.location,needReplacement,locationReplacement);
        if (result != null) {
          break;
        }
      }
    }
    if (result != null) {
      onUpdateObjectReference(requester,result,locationReplacement != null ? locationReplacement[0] : null);
      found=true;
    }
 else {
      sendErrorToObject(requester.args,1);
    }
  }
  locationRequester.remove(locationKey);
  if (found) {
    putReponseToCache(locationKey,response);
  }
  return found;
}
