@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.mediaDidLoad) {
    long uid=(Long)args[0];
    int guid=(Integer)args[3];
    if (guid == classGuid) {
      int type=(Integer)args[4];
      sharedMediaData[type].loading=false;
      sharedMediaData[type].totalCount=(Integer)args[1];
      ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[2];
      boolean enc=((int)dialog_id) == 0;
      int loadIndex=uid == dialog_id ? 0 : 1;
      RecyclerListView.Adapter adapter=null;
      if (type == 0) {
        adapter=photoVideoAdapter;
      }
 else       if (type == 1) {
        adapter=documentsAdapter;
      }
 else       if (type == 2) {
        adapter=voiceAdapter;
      }
 else       if (type == 3) {
        adapter=linksAdapter;
      }
 else       if (type == 4) {
        adapter=audioAdapter;
      }
      int oldItemCount;
      if (adapter != null) {
        oldItemCount=adapter.getItemCount();
        if (adapter instanceof RecyclerListView.SectionsAdapter) {
          RecyclerListView.SectionsAdapter sectionsAdapter=(RecyclerListView.SectionsAdapter)adapter;
          sectionsAdapter.notifySectionsChanged();
        }
      }
 else {
        oldItemCount=0;
      }
      for (int a=0; a < arr.size(); a++) {
        MessageObject message=arr.get(a);
        sharedMediaData[type].addMessage(message,loadIndex,false,enc);
      }
      sharedMediaData[type].endReached[loadIndex]=(Boolean)args[5];
      if (loadIndex == 0 && sharedMediaData[type].endReached[loadIndex] && mergeDialogId != 0) {
        sharedMediaData[type].loading=true;
        DataQuery.getInstance(currentAccount).loadMedia(mergeDialogId,50,sharedMediaData[type].max_id[1],type,1,classGuid);
      }
      if (adapter != null) {
        for (int a=0; a < mediaPages.length; a++) {
          if (mediaPages[a].listView.getAdapter() == adapter) {
            mediaPages[a].listView.stopScroll();
          }
        }
        int newItemCount=adapter.getItemCount();
        if (oldItemCount > 1) {
          adapter.notifyItemChanged(oldItemCount - 2);
        }
        if (newItemCount > oldItemCount) {
          adapter.notifyItemRangeInserted(oldItemCount,newItemCount);
        }
 else         if (newItemCount < oldItemCount) {
          adapter.notifyItemRangeRemoved(newItemCount,(oldItemCount - newItemCount));
        }
      }
      scrolling=true;
      for (int a=0; a < mediaPages.length; a++) {
        if (mediaPages[a].selectedType == type) {
          if (!sharedMediaData[type].loading) {
            if (mediaPages[a].progressView != null) {
              mediaPages[a].progressView.setVisibility(View.GONE);
            }
            if (mediaPages[a].selectedType == type && mediaPages[a].listView != null) {
              if (mediaPages[a].listView.getEmptyView() == null) {
                mediaPages[a].listView.setEmptyView(mediaPages[a].emptyView);
              }
            }
          }
        }
        if (oldItemCount == 0 && actionBar.getTranslationY() != 0 && mediaPages[a].listView.getAdapter() == adapter) {
          mediaPages[a].layoutManager.scrollToPositionWithOffset(0,(int)actionBar.getTranslationY());
        }
      }
    }
  }
 else   if (id == NotificationCenter.messagesDeleted) {
    TLRPC.Chat currentChat=null;
    if ((int)dialog_id < 0) {
      currentChat=MessagesController.getInstance(currentAccount).getChat(-(int)dialog_id);
    }
    int channelId=(Integer)args[1];
    int loadIndex=0;
    if (ChatObject.isChannel(currentChat)) {
      if (channelId == 0 && mergeDialogId != 0) {
        loadIndex=1;
      }
 else       if (channelId == currentChat.id) {
        loadIndex=0;
      }
 else {
        return;
      }
    }
 else     if (channelId != 0) {
      return;
    }
    ArrayList<Integer> markAsDeletedMessages=(ArrayList<Integer>)args[0];
    boolean updated=false;
    for (int a=0, N=markAsDeletedMessages.size(); a < N; a++) {
      for (int b=0; b < sharedMediaData.length; b++) {
        if (sharedMediaData[b].deleteMessage(markAsDeletedMessages.get(a),loadIndex)) {
          updated=true;
        }
      }
    }
    if (updated) {
      scrolling=true;
      if (photoVideoAdapter != null) {
        photoVideoAdapter.notifyDataSetChanged();
      }
      if (documentsAdapter != null) {
        documentsAdapter.notifyDataSetChanged();
      }
      if (voiceAdapter != null) {
        voiceAdapter.notifyDataSetChanged();
      }
      if (linksAdapter != null) {
        linksAdapter.notifyDataSetChanged();
      }
      if (audioAdapter != null) {
        audioAdapter.notifyDataSetChanged();
      }
    }
  }
 else   if (id == NotificationCenter.didReceiveNewMessages) {
    long uid=(Long)args[0];
    if (uid == dialog_id) {
      ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[1];
      boolean enc=((int)dialog_id) == 0;
      boolean updated=false;
      for (int a=0; a < arr.size(); a++) {
        MessageObject obj=arr.get(a);
        if (obj.messageOwner.media == null || obj.needDrawBluredPreview()) {
          continue;
        }
        int type=DataQuery.getMediaType(obj.messageOwner);
        if (type == -1) {
          return;
        }
        if (sharedMediaData[type].addMessage(obj,obj.getDialogId() == dialog_id ? 0 : 1,true,enc)) {
          updated=true;
          hasMedia[type]=1;
        }
      }
      if (updated) {
        scrolling=true;
        for (int a=0; a < mediaPages.length; a++) {
          RecyclerListView.Adapter adapter=null;
          if (mediaPages[a].selectedType == 0) {
            adapter=photoVideoAdapter;
          }
 else           if (mediaPages[a].selectedType == 1) {
            adapter=documentsAdapter;
          }
 else           if (mediaPages[a].selectedType == 2) {
            adapter=voiceAdapter;
          }
 else           if (mediaPages[a].selectedType == 3) {
            adapter=linksAdapter;
          }
 else           if (mediaPages[a].selectedType == 4) {
            adapter=audioAdapter;
          }
          if (adapter != null) {
            int count=adapter.getItemCount();
            photoVideoAdapter.notifyDataSetChanged();
            documentsAdapter.notifyDataSetChanged();
            voiceAdapter.notifyDataSetChanged();
            linksAdapter.notifyDataSetChanged();
            audioAdapter.notifyDataSetChanged();
            if (count == 0 && actionBar.getTranslationY() != 0) {
              mediaPages[a].layoutManager.scrollToPositionWithOffset(0,(int)actionBar.getTranslationY());
            }
          }
        }
        updateTabs();
      }
    }
  }
 else   if (id == NotificationCenter.messageReceivedByServer) {
    Integer msgId=(Integer)args[0];
    Integer newMsgId=(Integer)args[1];
    for (    SharedMediaData data : sharedMediaData) {
      data.replaceMid(msgId,newMsgId);
    }
  }
 else   if (id == NotificationCenter.messagePlayingDidStart || id == NotificationCenter.messagePlayingPlayStateChanged || id == NotificationCenter.messagePlayingDidReset) {
    if (id == NotificationCenter.messagePlayingDidReset || id == NotificationCenter.messagePlayingPlayStateChanged) {
      for (int b=0; b < mediaPages.length; b++) {
        int count=mediaPages[b].listView.getChildCount();
        for (int a=0; a < count; a++) {
          View view=mediaPages[b].listView.getChildAt(a);
          if (view instanceof SharedAudioCell) {
            SharedAudioCell cell=(SharedAudioCell)view;
            MessageObject messageObject=cell.getMessage();
            if (messageObject != null) {
              cell.updateButtonState(false,true);
            }
          }
        }
      }
    }
 else     if (id == NotificationCenter.messagePlayingDidStart) {
      MessageObject messageObject=(MessageObject)args[0];
      if (messageObject.eventId != 0) {
        return;
      }
      for (int b=0; b < mediaPages.length; b++) {
        int count=mediaPages[b].listView.getChildCount();
        for (int a=0; a < count; a++) {
          View view=mediaPages[b].listView.getChildAt(a);
          if (view instanceof SharedAudioCell) {
            SharedAudioCell cell=(SharedAudioCell)view;
            MessageObject messageObject1=cell.getMessage();
            if (messageObject1 != null) {
              cell.updateButtonState(false,true);
            }
          }
        }
      }
    }
  }
}
