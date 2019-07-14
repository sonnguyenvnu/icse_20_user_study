private void searchImages(boolean gif,final String query,final String offset,boolean searchUser){
  if (searching) {
    searching=false;
    if (imageReqId != 0) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(imageReqId,true);
      imageReqId=0;
    }
  }
  lastSearchImageString=query;
  searching=true;
  TLObject object=MessagesController.getInstance(currentAccount).getUserOrChat(gif ? MessagesController.getInstance(currentAccount).gifSearchBot : MessagesController.getInstance(currentAccount).imageSearchBot);
  if (!(object instanceof TLRPC.User)) {
    if (searchUser) {
      searchBotUser(gif);
    }
    return;
  }
  TLRPC.User user=(TLRPC.User)object;
  TLRPC.TL_messages_getInlineBotResults req=new TLRPC.TL_messages_getInlineBotResults();
  req.query=query == null ? "" : query;
  req.bot=MessagesController.getInstance(currentAccount).getInputUser(user);
  req.offset=offset;
  if (chatActivity != null) {
    long dialogId=chatActivity.getDialogId();
    int lower_id=(int)dialogId;
    int high_id=(int)(dialogId >> 32);
    if (lower_id != 0) {
      req.peer=MessagesController.getInstance(currentAccount).getInputPeer(lower_id);
    }
 else {
      req.peer=new TLRPC.TL_inputPeerEmpty();
    }
  }
 else {
    req.peer=new TLRPC.TL_inputPeerEmpty();
  }
  final int token=++lastSearchToken;
  imageReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (token != lastSearchToken) {
      return;
    }
    int addedCount=0;
    int oldCount=searchResult.size();
    if (response != null) {
      TLRPC.messages_BotResults res=(TLRPC.messages_BotResults)response;
      nextImagesSearchOffset=res.next_offset;
      for (int a=0, count=res.results.size(); a < count; a++) {
        TLRPC.BotInlineResult result=res.results.get(a);
        if (!gif && !"photo".equals(result.type) || gif && !"gif".equals(result.type)) {
          continue;
        }
        if (searchResultKeys.containsKey(result.id)) {
          continue;
        }
        MediaController.SearchImage image=new MediaController.SearchImage();
        if (gif && result.document != null) {
          for (int b=0; b < result.document.attributes.size(); b++) {
            TLRPC.DocumentAttribute attribute=result.document.attributes.get(b);
            if (attribute instanceof TLRPC.TL_documentAttributeImageSize || attribute instanceof TLRPC.TL_documentAttributeVideo) {
              image.width=attribute.w;
              image.height=attribute.h;
              break;
            }
          }
          image.document=result.document;
          image.size=0;
          if (result.photo != null && result.document != null) {
            TLRPC.PhotoSize size=FileLoader.getClosestPhotoSizeWithSize(result.photo.sizes,itemWidth,true);
            if (size != null) {
              result.document.thumbs.add(size);
              result.document.flags|=1;
            }
          }
        }
 else         if (!gif && result.photo != null) {
          TLRPC.PhotoSize size=FileLoader.getClosestPhotoSizeWithSize(result.photo.sizes,AndroidUtilities.getPhotoSize());
          TLRPC.PhotoSize size2=FileLoader.getClosestPhotoSizeWithSize(result.photo.sizes,320);
          if (size == null) {
            continue;
          }
          image.width=size.w;
          image.height=size.h;
          image.photoSize=size;
          image.photo=result.photo;
          image.size=size.size;
          image.thumbPhotoSize=size2;
        }
 else {
          if (result.content == null) {
            continue;
          }
          for (int b=0; b < result.content.attributes.size(); b++) {
            TLRPC.DocumentAttribute attribute=result.content.attributes.get(b);
            if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
              image.width=attribute.w;
              image.height=attribute.h;
              break;
            }
          }
          if (result.thumb != null) {
            image.thumbUrl=result.thumb.url;
          }
 else {
            image.thumbUrl=null;
          }
          image.imageUrl=result.content.url;
          image.size=gif ? 0 : result.content.size;
        }
        image.id=result.id;
        image.type=gif ? 1 : 0;
        image.localUrl="";
        searchResult.add(image);
        searchResultKeys.put(image.id,image);
        addedCount++;
      }
      imageSearchEndReached=oldCount == searchResult.size() || nextImagesSearchOffset == null;
    }
    searching=false;
    if (addedCount != 0) {
      listAdapter.notifyItemRangeInserted(oldCount,addedCount);
    }
 else     if (imageSearchEndReached) {
      listAdapter.notifyItemRemoved(searchResult.size() - 1);
    }
    if (searching && searchResult.isEmpty() || loadingRecent && lastSearchString == null) {
      emptyView.showProgress();
    }
 else {
      emptyView.showTextView();
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(imageReqId,classGuid);
}
