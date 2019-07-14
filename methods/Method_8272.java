@Override public void didReceivedNotification(int id,int account,final Object... args){
  if (id == NotificationCenter.messagesDidLoad) {
    int guid=(Integer)args[10];
    if (guid == classGuid) {
      setItemAnimationsEnabled(false);
      if (!openAnimationEnded) {
        NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.chatInfoDidLoad,NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats,NotificationCenter.botKeyboardDidLoad,NotificationCenter.userInfoDidLoad,NotificationCenter.needDeleteDialog});
      }
      int queryLoadIndex=(Integer)args[11];
      int index=waitingForLoad.indexOf(queryLoadIndex);
      int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
      if (index == -1) {
        return;
      }
 else {
        waitingForLoad.remove(index);
      }
      ArrayList<MessageObject> messArr=(ArrayList<MessageObject>)args[2];
      boolean createUnreadLoading=false;
      boolean showDateAfter=waitingForReplyMessageLoad;
      if (waitingForReplyMessageLoad) {
        if (!createUnreadMessageAfterIdLoading) {
          boolean found=false;
          for (int a=0; a < messArr.size(); a++) {
            MessageObject obj=messArr.get(a);
            if (obj.getId() == startLoadFromMessageId) {
              found=true;
              break;
            }
            if (a + 1 < messArr.size()) {
              MessageObject obj2=messArr.get(a + 1);
              if (obj.getId() >= startLoadFromMessageId && obj2.getId() < startLoadFromMessageId) {
                startLoadFromMessageId=obj.getId();
                found=true;
                break;
              }
            }
          }
          if (!found) {
            startLoadFromMessageId=0;
            return;
          }
        }
        int startLoadFrom=startLoadFromMessageId;
        boolean needSelect=needSelectFromMessageId;
        int unreadAfterId=createUnreadMessageAfterId;
        createUnreadLoading=createUnreadMessageAfterIdLoading;
        clearChatData();
        createUnreadMessageAfterId=unreadAfterId;
        startLoadFromMessageId=startLoadFrom;
        needSelectFromMessageId=needSelect;
      }
      loadsCount++;
      long did=(Long)args[0];
      int loadIndex=did == dialog_id ? 0 : 1;
      int count=(Integer)args[1];
      boolean isCache=(Boolean)args[3];
      int fnid=(Integer)args[4];
      int last_unread_date=(Integer)args[7];
      int load_type=(Integer)args[8];
      int loaded_max_id=(Integer)args[12];
      int loaded_mentions_count=(Integer)args[13];
      if (loaded_mentions_count < 0) {
        loaded_mentions_count*=-1;
        hasAllMentionsLocal=false;
      }
 else       if (first) {
        hasAllMentionsLocal=true;
      }
      if (load_type == 4) {
        startLoadFromMessageId=loaded_max_id;
        for (int a=messArr.size() - 1; a > 0; a--) {
          MessageObject obj=messArr.get(a);
          if (obj.type < 0 && obj.getId() == startLoadFromMessageId) {
            startLoadFromMessageId=messArr.get(a - 1).getId();
            break;
          }
        }
      }
      int unread_to_load=0;
      if (fnid != 0) {
        last_message_id=(Integer)args[5];
        if (load_type == 3) {
          if (loadingFromOldPosition) {
            unread_to_load=(Integer)args[6];
            if (unread_to_load != 0) {
              createUnreadMessageAfterId=fnid;
            }
            loadingFromOldPosition=false;
          }
          first_unread_id=0;
        }
 else {
          first_unread_id=fnid;
          unread_to_load=(Integer)args[6];
        }
      }
 else       if (startLoadFromMessageId != 0 && (load_type == 3 || load_type == 4)) {
        last_message_id=(Integer)args[5];
      }
      int newRowsCount=0;
      if (load_type != 0 && (startLoadFromMessageId != 0 || last_message_id != 0)) {
        forwardEndReached[loadIndex]=false;
      }
      if ((load_type == 1 || load_type == 3) && loadIndex == 1) {
        endReached[0]=cacheEndReached[0]=true;
        forwardEndReached[0]=false;
        minMessageId[0]=0;
      }
      if (loadsCount == 1 && messArr.size() > 20) {
        loadsCount++;
      }
      if (firstLoading) {
        if (!forwardEndReached[loadIndex]) {
          messages.clear();
          messagesByDays.clear();
          groupedMessagesMap.clear();
          for (int a=0; a < 2; a++) {
            messagesDict[a].clear();
            if (currentEncryptedChat == null) {
              maxMessageId[a]=Integer.MAX_VALUE;
              minMessageId[a]=Integer.MIN_VALUE;
            }
 else {
              maxMessageId[a]=Integer.MIN_VALUE;
              minMessageId[a]=Integer.MAX_VALUE;
            }
            maxDate[a]=Integer.MIN_VALUE;
            minDate[a]=0;
          }
        }
        firstLoading=false;
        AndroidUtilities.runOnUIThread(() -> {
          if (parentLayout != null) {
            parentLayout.resumeDelayedFragmentAnimation();
          }
        }
);
      }
      if (load_type == 1) {
        Collections.reverse(messArr);
      }
      if (currentEncryptedChat == null) {
        DataQuery.getInstance(currentAccount).loadReplyMessagesForMessages(messArr,dialog_id);
      }
      int approximateHeightSum=0;
      if ((load_type == 2 || load_type == 1) && messArr.isEmpty() && !isCache) {
        forwardEndReached[0]=true;
      }
      LongSparseArray<MessageObject.GroupedMessages> newGroups=null;
      LongSparseArray<MessageObject.GroupedMessages> changedGroups=null;
      MediaController mediaController=MediaController.getInstance();
      TLRPC.MessageAction dropPhotoAction=null;
      boolean createdWas=false;
      for (int a=0, N=messArr.size(); a < N; a++) {
        MessageObject obj=messArr.get(N - a - 1);
        TLRPC.MessageAction action=obj.messageOwner.action;
        if (a == 0 && action instanceof TLRPC.TL_messageActionChatCreate) {
          createdWas=true;
        }
 else         if (!createdWas) {
          break;
        }
 else         if (a < 2 && action instanceof TLRPC.TL_messageActionChatEditPhoto) {
          dropPhotoAction=action;
        }
      }
      for (int a=0; a < messArr.size(); a++) {
        MessageObject obj=messArr.get(a);
        approximateHeightSum+=obj.getApproximateHeight();
        if (currentUser != null) {
          if (currentUser.self) {
            obj.messageOwner.out=true;
          }
          if (currentUser.bot && obj.isOut() || currentUser.id == currentUserId) {
            obj.setIsRead();
          }
        }
        if (messagesDict[loadIndex].indexOfKey(obj.getId()) >= 0) {
          continue;
        }
        addToPolls(obj,null);
        if (isSecretChat()) {
          checkSecretMessageForLocation(obj);
        }
        if (mediaController.isPlayingMessage(obj)) {
          MessageObject player=mediaController.getPlayingMessageObject();
          obj.audioProgress=player.audioProgress;
          obj.audioProgressSec=player.audioProgressSec;
          obj.audioPlayerDuration=player.audioPlayerDuration;
        }
        if (loadIndex == 0 && ChatObject.isChannel(currentChat) && obj.getId() == 1) {
          endReached[loadIndex]=true;
          cacheEndReached[loadIndex]=true;
        }
        if (obj.getId() > 0) {
          maxMessageId[loadIndex]=Math.min(obj.getId(),maxMessageId[loadIndex]);
          minMessageId[loadIndex]=Math.max(obj.getId(),minMessageId[loadIndex]);
        }
 else         if (currentEncryptedChat != null) {
          maxMessageId[loadIndex]=Math.max(obj.getId(),maxMessageId[loadIndex]);
          minMessageId[loadIndex]=Math.min(obj.getId(),minMessageId[loadIndex]);
        }
        if (obj.messageOwner.date != 0) {
          maxDate[loadIndex]=Math.max(maxDate[loadIndex],obj.messageOwner.date);
          if (minDate[loadIndex] == 0 || obj.messageOwner.date < minDate[loadIndex]) {
            minDate[loadIndex]=obj.messageOwner.date;
          }
        }
        if (obj.getId() == last_message_id) {
          forwardEndReached[loadIndex]=true;
        }
        TLRPC.MessageAction action=obj.messageOwner.action;
        if (obj.type < 0 || loadIndex == 1 && action instanceof TLRPC.TL_messageActionChatMigrateTo) {
          continue;
        }
        if (currentChat != null && currentChat.creator && (action instanceof TLRPC.TL_messageActionChatCreate || dropPhotoAction != null && action == dropPhotoAction)) {
          continue;
        }
        if (obj.messageOwner.action instanceof TLRPC.TL_messageActionChannelMigrateFrom) {
          continue;
        }
        if (needAnimateToMessage != null && needAnimateToMessage.getId() == obj.getId() && obj.getId() < 0 && obj.type == 5) {
          obj=needAnimateToMessage;
          animatingMessageObjects.add(obj);
          needAnimateToMessage=null;
        }
        messagesDict[loadIndex].put(obj.getId(),obj);
        ArrayList<MessageObject> dayArray=messagesByDays.get(obj.dateKey);
        if (dayArray == null) {
          dayArray=new ArrayList<>();
          messagesByDays.put(obj.dateKey,dayArray);
          TLRPC.Message dateMsg=new TLRPC.TL_message();
          dateMsg.message=LocaleController.formatDateChat(obj.messageOwner.date);
          dateMsg.id=0;
          dateMsg.date=obj.messageOwner.date;
          MessageObject dateObj=new MessageObject(currentAccount,dateMsg,false);
          dateObj.type=10;
          dateObj.contentType=1;
          dateObj.isDateObject=true;
          if (load_type == 1) {
            messages.add(0,dateObj);
          }
 else {
            messages.add(dateObj);
          }
          newRowsCount++;
        }
        if (obj.hasValidGroupId()) {
          MessageObject.GroupedMessages groupedMessages=groupedMessagesMap.get(obj.getGroupIdForUse());
          if (groupedMessages != null) {
            if (messages.size() > 1) {
              MessageObject previous;
              if (load_type == 1) {
                previous=messages.get(0);
              }
 else {
                previous=messages.get(messages.size() - 2);
              }
              if (previous.getGroupIdForUse() == obj.getGroupIdForUse()) {
                if (previous.localGroupId != 0) {
                  obj.localGroupId=previous.localGroupId;
                  groupedMessages=groupedMessagesMap.get(previous.localGroupId);
                }
              }
 else               if (previous.getGroupIdForUse() != obj.getGroupIdForUse()) {
                obj.localGroupId=Utilities.random.nextLong();
                groupedMessages=null;
              }
            }
          }
          if (groupedMessages == null) {
            groupedMessages=new MessageObject.GroupedMessages();
            groupedMessages.groupId=obj.getGroupId();
            groupedMessagesMap.put(groupedMessages.groupId,groupedMessages);
          }
 else           if (newGroups == null || newGroups.indexOfKey(obj.getGroupId()) < 0) {
            if (changedGroups == null) {
              changedGroups=new LongSparseArray<>();
            }
            changedGroups.put(obj.getGroupId(),groupedMessages);
          }
          if (newGroups == null) {
            newGroups=new LongSparseArray<>();
          }
          newGroups.put(groupedMessages.groupId,groupedMessages);
          if (load_type == 1) {
            groupedMessages.messages.add(obj);
          }
 else {
            groupedMessages.messages.add(0,obj);
          }
        }
 else         if (obj.getGroupIdForUse() != 0) {
          obj.messageOwner.grouped_id=0;
          obj.localSentGroupId=0;
        }
        newRowsCount++;
        dayArray.add(obj);
        if (load_type == 1) {
          messages.add(0,obj);
        }
 else {
          messages.add(messages.size() - 1,obj);
        }
        MessageObject prevObj;
        if (currentEncryptedChat == null) {
          if (createUnreadMessageAfterId != 0 && load_type != 1 && a + 1 < messArr.size()) {
            prevObj=messArr.get(a + 1);
            if (obj.isOut() || prevObj.getId() >= createUnreadMessageAfterId) {
              prevObj=null;
            }
          }
 else {
            prevObj=null;
          }
        }
 else {
          if (createUnreadMessageAfterId != 0 && load_type != 1 && a - 1 >= 0) {
            prevObj=messArr.get(a - 1);
            if (obj.isOut() || prevObj.getId() >= createUnreadMessageAfterId) {
              prevObj=null;
            }
          }
 else {
            prevObj=null;
          }
        }
        if (load_type == 2 && obj.getId() == first_unread_id) {
          if (approximateHeightSum > AndroidUtilities.displaySize.y / 2 || !forwardEndReached[0]) {
            TLRPC.Message dateMsg=new TLRPC.TL_message();
            dateMsg.message="";
            dateMsg.id=0;
            MessageObject dateObj=new MessageObject(currentAccount,dateMsg,false);
            dateObj.type=6;
            dateObj.contentType=2;
            messages.add(messages.size() - 1,dateObj);
            unreadMessageObject=dateObj;
            scrollToMessage=unreadMessageObject;
            scrollToMessagePosition=-10000;
            newRowsCount++;
          }
        }
 else         if ((load_type == 3 || load_type == 4) && obj.getId() == startLoadFromMessageId) {
          removeSelectedMessageHighlight();
          if (needSelectFromMessageId) {
            highlightMessageId=obj.getId();
          }
          scrollToMessage=obj;
          startLoadFromMessageId=0;
          if (scrollToMessagePosition == -10000) {
            scrollToMessagePosition=-9000;
          }
        }
        if (load_type != 2 && unreadMessageObject == null && createUnreadMessageAfterId != 0 && (currentEncryptedChat == null && !obj.isOut() && obj.getId() >= createUnreadMessageAfterId || currentEncryptedChat != null && !obj.isOut() && obj.getId() <= createUnreadMessageAfterId) && (load_type == 1 || prevObj != null || prevObj == null && createUnreadLoading && a == messArr.size() - 1)) {
          TLRPC.Message dateMsg=new TLRPC.TL_message();
          dateMsg.message="";
          dateMsg.id=0;
          MessageObject dateObj=new MessageObject(currentAccount,dateMsg,false);
          dateObj.type=6;
          dateObj.contentType=2;
          if (load_type == 1) {
            messages.add(1,dateObj);
          }
 else {
            messages.add(messages.size() - 1,dateObj);
          }
          unreadMessageObject=dateObj;
          if (load_type == 3) {
            scrollToMessage=unreadMessageObject;
            startLoadFromMessageId=0;
            scrollToMessagePosition=-9000;
          }
          newRowsCount++;
        }
      }
      if (createUnreadLoading) {
        createUnreadMessageAfterId=0;
      }
      if (load_type == 0 && newRowsCount == 0) {
        loadsCount--;
      }
      if (forwardEndReached[loadIndex] && loadIndex != 1) {
        first_unread_id=0;
        last_message_id=0;
        createUnreadMessageAfterId=0;
      }
      if (load_type == 1) {
        int rowsRemoved=0;
        if (messArr.size() != count && (!isCache || currentEncryptedChat != null || forwardEndReached[loadIndex])) {
          forwardEndReached[loadIndex]=true;
          if (loadIndex != 1) {
            first_unread_id=0;
            last_message_id=0;
            createUnreadMessageAfterId=0;
            chatAdapter.notifyItemRemoved(chatAdapter.loadingDownRow);
            rowsRemoved++;
          }
          startLoadFromMessageId=0;
        }
        if (newRowsCount > 0) {
          int firstVisPos=chatLayoutManager.findFirstVisibleItemPosition();
          if (firstVisPos == 0) {
            firstVisPos++;
          }
          View firstVisView=chatLayoutManager.findViewByPosition(firstVisPos);
          int testingPosition=firstVisPos;
          View testingView=firstVisView;
          View goodView=null;
          int goodPosition=-1;
          if (testingView != null) {
            while (goodView == null) {
              if (testingView instanceof ChatMessageCell) {
                MessageObject messageObject=((ChatMessageCell)testingView).getMessageObject();
                if (messageObject.hasValidGroupId()) {
                  testingPosition++;
                  testingView=chatLayoutManager.findViewByPosition(testingPosition);
                  if (testingView == null) {
                    goodPosition=firstVisPos;
                    goodView=firstVisView;
                    break;
                  }
                }
 else {
                  goodPosition=testingPosition;
                  goodView=testingView;
                  break;
                }
              }
 else {
                goodPosition=testingPosition;
                goodView=testingView;
                break;
              }
            }
          }
          int top=((goodView == null) ? 0 : chatListView.getMeasuredHeight() - goodView.getBottom() - chatListView.getPaddingBottom());
          chatAdapter.notifyItemRangeInserted(1,newRowsCount);
          if (goodPosition != RecyclerView.NO_POSITION) {
            chatLayoutManager.scrollToPositionWithOffset(goodPosition + newRowsCount - rowsRemoved,top);
          }
        }
        loadingForward=false;
      }
 else {
        if (messArr.size() < count && load_type != 3 && load_type != 4) {
          if (isCache) {
            if (currentEncryptedChat != null || isBroadcast) {
              endReached[loadIndex]=true;
            }
            if (load_type != 2) {
              cacheEndReached[loadIndex]=true;
            }
          }
 else           if (load_type != 2 || messArr.size() == 0 && messages.isEmpty()) {
            endReached[loadIndex]=true;
          }
        }
        loading=false;
        if (chatListView != null) {
          if (first || scrollToTopOnResume || forceScrollToTop) {
            forceScrollToTop=false;
            chatAdapter.notifyDataSetChanged();
            if (scrollToMessage != null) {
              int yOffset;
              boolean bottom=true;
              if (startLoadFromMessageOffset != Integer.MAX_VALUE) {
                yOffset=-startLoadFromMessageOffset - chatListView.getPaddingBottom();
                startLoadFromMessageOffset=Integer.MAX_VALUE;
              }
 else               if (scrollToMessagePosition == -9000) {
                yOffset=getScrollOffsetForMessage(scrollToMessage);
                bottom=false;
              }
 else               if (scrollToMessagePosition == -10000) {
                yOffset=-AndroidUtilities.dp(11);
                bottom=false;
              }
 else {
                yOffset=scrollToMessagePosition;
              }
              if (!messages.isEmpty()) {
                if (chatAdapter.loadingUpRow != -1 && !messages.isEmpty() && (messages.get(messages.size() - 1) == scrollToMessage || messages.get(messages.size() - 2) == scrollToMessage)) {
                  chatLayoutManager.scrollToPositionWithOffset(chatAdapter.loadingUpRow,yOffset,bottom);
                }
 else {
                  chatLayoutManager.scrollToPositionWithOffset(chatAdapter.messagesStartRow + messages.indexOf(scrollToMessage),yOffset,bottom);
                }
              }
              chatListView.invalidate();
              if (scrollToMessagePosition == -10000 || scrollToMessagePosition == -9000) {
                showPagedownButton(true,true);
                if (unread_to_load != 0) {
                  if (pagedownButtonCounter != null) {
                    pagedownButtonCounter.setVisibility(View.VISIBLE);
                    if (prevSetUnreadCount != newUnreadMessageCount) {
                      pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount=unread_to_load));
                      prevSetUnreadCount=newUnreadMessageCount;
                    }
                  }
                }
              }
              scrollToMessagePosition=-10000;
              scrollToMessage=null;
            }
 else {
              moveScrollToLastMessage();
            }
            if (loaded_mentions_count != 0) {
              showMentionDownButton(true,true);
              if (mentiondownButtonCounter != null) {
                mentiondownButtonCounter.setVisibility(View.VISIBLE);
                mentiondownButtonCounter.setText(String.format("%d",newMentionsCount=loaded_mentions_count));
              }
            }
          }
 else {
            if (newRowsCount != 0) {
              boolean end=false;
              if (endReached[loadIndex] && (loadIndex == 0 && mergeDialogId == 0 || loadIndex == 1)) {
                end=true;
                chatAdapter.notifyItemRangeChanged(chatAdapter.loadingUpRow - 1,2);
                chatAdapter.updateRows();
              }
              int firstVisPos=chatLayoutManager.findFirstVisibleItemPosition();
              View firstVisView=chatLayoutManager.findViewByPosition(firstVisPos);
              int testingPosition=firstVisPos;
              View testingView=firstVisView;
              View goodView=null;
              int goodPosition=-1;
              if (testingView != null) {
                while (goodView == null) {
                  if (testingView instanceof ChatMessageCell) {
                    MessageObject messageObject=((ChatMessageCell)testingView).getMessageObject();
                    if (messageObject.hasValidGroupId()) {
                      testingPosition++;
                      testingView=chatLayoutManager.findViewByPosition(testingPosition);
                      if (testingView == null) {
                        goodPosition=firstVisPos;
                        goodView=firstVisView;
                        break;
                      }
                    }
 else {
                      goodPosition=testingPosition;
                      goodView=testingView;
                      break;
                    }
                  }
 else {
                    goodPosition=testingPosition;
                    goodView=testingView;
                    break;
                  }
                }
              }
              int top=((goodView == null) ? 0 : chatListView.getMeasuredHeight() - goodView.getBottom() - chatListView.getPaddingBottom());
              if (newRowsCount - (end ? 1 : 0) > 0) {
                int insertStart=chatAdapter.messagesEndRow;
                chatAdapter.notifyItemChanged(chatAdapter.loadingUpRow);
                chatAdapter.notifyItemRangeInserted(insertStart,newRowsCount - (end ? 1 : 0));
              }
              if (goodPosition != -1) {
                chatLayoutManager.scrollToPositionWithOffset(goodPosition,top);
              }
            }
 else             if (endReached[loadIndex] && (loadIndex == 0 && mergeDialogId == 0 || loadIndex == 1)) {
              chatAdapter.notifyItemRemoved(chatAdapter.loadingUpRow);
            }
          }
          if (paused) {
            scrollToTopOnResume=true;
            if (scrollToMessage != null) {
              scrollToTopUnReadOnResume=true;
            }
          }
          if (first) {
            if (chatListView != null) {
              chatListView.setEmptyView(emptyViewContainer);
            }
          }
        }
 else {
          scrollToTopOnResume=true;
          if (scrollToMessage != null) {
            scrollToTopUnReadOnResume=true;
          }
        }
      }
      if (newGroups != null) {
        for (int a=0; a < newGroups.size(); a++) {
          MessageObject.GroupedMessages groupedMessages=newGroups.valueAt(a);
          groupedMessages.calculate();
          if (chatAdapter != null && changedGroups != null && changedGroups.indexOfKey(newGroups.keyAt(a)) >= 0) {
            MessageObject messageObject=groupedMessages.messages.get(groupedMessages.messages.size() - 1);
            int idx=messages.indexOf(messageObject);
            if (idx >= 0) {
              chatAdapter.notifyItemRangeChanged(idx + chatAdapter.messagesStartRow,groupedMessages.messages.size());
            }
          }
        }
      }
      if (first && messages.size() > 0) {
        first=false;
      }
      if (messages.isEmpty() && currentEncryptedChat == null && currentUser != null && currentUser.bot && botUser == null) {
        botUser="";
        updateBottomOverlay();
      }
      if (newRowsCount == 0 && (mergeDialogId != 0 && loadIndex == 0 || currentEncryptedChat != null && !endReached[0])) {
        first=true;
        if (chatListView != null) {
          chatListView.setEmptyView(null);
        }
        if (emptyViewContainer != null) {
          emptyViewContainer.setVisibility(View.INVISIBLE);
        }
      }
 else {
        if (progressView != null) {
          progressView.setVisibility(View.INVISIBLE);
        }
      }
      if (newRowsCount == 0 && mergeDialogId != 0 && loadIndex == 0) {
        NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.chatInfoDidLoad,NotificationCenter.dialogsNeedReload,NotificationCenter.closeChats,NotificationCenter.messagesDidLoad,NotificationCenter.botKeyboardDidLoad,NotificationCenter.userInfoDidLoad,NotificationCenter.needDeleteDialog});
      }
      if (showDateAfter) {
        showFloatingDateView(false);
      }
      checkScrollForLoad(false);
      setItemAnimationsEnabled(true);
    }
  }
 else   if (id == NotificationCenter.emojiDidLoad) {
    if (chatListView != null) {
      chatListView.invalidateViews();
    }
    if (replyObjectTextView != null) {
      replyObjectTextView.invalidate();
    }
    if (alertTextView != null) {
      alertTextView.invalidate();
    }
    if (pinnedMessageTextView != null) {
      pinnedMessageTextView.invalidate();
    }
    if (mentionListView != null) {
      mentionListView.invalidateViews();
    }
    if (stickersListView != null) {
      stickersListView.invalidateViews();
    }
  }
 else   if (id == NotificationCenter.didUpdateConnectionState) {
    int state=ConnectionsManager.getInstance(account).getConnectionState();
    if (state == ConnectionsManager.ConnectionStateConnected) {
      checkAutoDownloadMessages(false);
    }
  }
 else   if (id == NotificationCenter.chatOnlineCountDidLoad) {
    Integer chatId=(Integer)args[0];
    if (chatInfo == null || currentChat == null || currentChat.id != chatId) {
      return;
    }
    chatInfo.online_count=(Integer)args[1];
    if (avatarContainer != null) {
      avatarContainer.updateOnlineCount();
      avatarContainer.updateSubtitle();
    }
  }
 else   if (id == NotificationCenter.updateInterfaces) {
    int updateMask=(Integer)args[0];
    if ((updateMask & MessagesController.UPDATE_MASK_NAME) != 0 || (updateMask & MessagesController.UPDATE_MASK_CHAT_NAME) != 0) {
      if (currentChat != null) {
        TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(currentChat.id);
        if (chat != null) {
          currentChat=chat;
        }
      }
 else       if (currentUser != null) {
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(currentUser.id);
        if (user != null) {
          currentUser=user;
        }
      }
      updateTitle();
    }
    boolean updateSubtitle=false;
    if ((updateMask & MessagesController.UPDATE_MASK_CHAT_MEMBERS) != 0 || (updateMask & MessagesController.UPDATE_MASK_STATUS) != 0) {
      if (currentChat != null && avatarContainer != null) {
        avatarContainer.updateOnlineCount();
      }
      updateSubtitle=true;
    }
    if ((updateMask & MessagesController.UPDATE_MASK_AVATAR) != 0 || (updateMask & MessagesController.UPDATE_MASK_CHAT_AVATAR) != 0 || (updateMask & MessagesController.UPDATE_MASK_NAME) != 0) {
      checkAndUpdateAvatar();
      updateVisibleRows();
    }
    if ((updateMask & MessagesController.UPDATE_MASK_USER_PRINT) != 0) {
      updateSubtitle=true;
    }
    if ((updateMask & MessagesController.UPDATE_MASK_CHAT) != 0 && currentChat != null) {
      TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(currentChat.id);
      if (chat == null) {
        return;
      }
      currentChat=chat;
      updateSubtitle=true;
      updateBottomOverlay();
      if (chatActivityEnterView != null) {
        chatActivityEnterView.setDialogId(dialog_id,currentAccount);
      }
    }
    if ((updateMask & MessagesController.UPDATE_MASK_READ_DIALOG_MESSAGE) != 0) {
      if (bottomOverlayChatText2 != null && chatInfo != null && ChatObject.isChannel(currentChat) && !currentChat.megagroup && -chatInfo.linked_chat_id != 0) {
        bottomOverlayChatText2.updateCounter();
      }
    }
    if (avatarContainer != null && updateSubtitle) {
      avatarContainer.updateSubtitle();
    }
    if ((updateMask & MessagesController.UPDATE_MASK_USER_PHONE) != 0) {
      updateContactStatus();
    }
  }
 else   if (id == NotificationCenter.didReceiveNewMessages) {
    long did=(Long)args[0];
    if (did == dialog_id) {
      int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
      boolean updateChat=false;
      boolean hasFromMe=false;
      ArrayList<MessageObject> arr=(ArrayList<MessageObject>)args[1];
      if (currentEncryptedChat != null && arr.size() == 1) {
        MessageObject obj=arr.get(0);
        if (currentEncryptedChat != null && obj.isOut() && obj.messageOwner.action instanceof TLRPC.TL_messageEncryptedAction && obj.messageOwner.action.encryptedAction instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL && getParentActivity() != null) {
          if (AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) < 17 && currentEncryptedChat.ttl > 0 && currentEncryptedChat.ttl <= 60) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
            builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
            builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
            builder.setMessage(LocaleController.formatString("CompatibilityChat",R.string.CompatibilityChat,currentUser.first_name,currentUser.first_name));
            showDialog(builder.create());
          }
        }
      }
      boolean notifiedSearch=false;
      for (int a=0; a < arr.size(); a++) {
        MessageObject messageObject=arr.get(a);
        if (!notifiedSearch && messageObject.isOut()) {
          notifiedSearch=true;
          NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.closeSearchByActiveAction);
        }
        if (currentChat != null) {
          if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatDeleteUser && messageObject.messageOwner.action.user_id == currentUserId || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatAddUser && messageObject.messageOwner.action.users.contains(currentUserId)) {
            TLRPC.Chat newChat=MessagesController.getInstance(currentAccount).getChat(currentChat.id);
            if (newChat != null) {
              currentChat=newChat;
              checkActionBarMenu();
              updateBottomOverlay();
              if (avatarContainer != null) {
                avatarContainer.updateSubtitle();
              }
            }
          }
        }
 else         if (inlineReturn != 0) {
          if (messageObject.messageOwner.reply_markup != null) {
            for (int b=0; b < messageObject.messageOwner.reply_markup.rows.size(); b++) {
              TLRPC.TL_keyboardButtonRow row=messageObject.messageOwner.reply_markup.rows.get(b);
              for (int c=0; c < row.buttons.size(); c++) {
                TLRPC.KeyboardButton button=row.buttons.get(c);
                if (button instanceof TLRPC.TL_keyboardButtonSwitchInline) {
                  processSwitchButton((TLRPC.TL_keyboardButtonSwitchInline)button);
                  break;
                }
              }
            }
          }
        }
        if (messageObject.messageOwner.reply_to_msg_id != 0 && messageObject.replyMessageObject == null) {
          messageObject.replyMessageObject=messagesDict[0].get(messageObject.messageOwner.reply_to_msg_id);
          if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
            messageObject.generatePinMessageText(null,null);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionGameScore) {
            messageObject.generateGameMessageText(null);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPaymentSent) {
            messageObject.generatePaymentSentMessageText(null);
          }
          if (messageObject.isMegagroup() && messageObject.replyMessageObject != null && messageObject.replyMessageObject.messageOwner != null) {
            messageObject.replyMessageObject.messageOwner.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
          }
        }
      }
      boolean reloadMegagroup=false;
      if (!forwardEndReached[0]) {
        int currentMaxDate=Integer.MIN_VALUE;
        int currentMinMsgId=Integer.MIN_VALUE;
        if (currentEncryptedChat != null) {
          currentMinMsgId=Integer.MAX_VALUE;
        }
        for (int a=0; a < arr.size(); a++) {
          MessageObject obj=arr.get(a);
          if (currentUser != null && (currentUser.bot && obj.isOut() || currentUser.id == currentUserId)) {
            obj.setIsRead();
          }
          TLRPC.MessageAction action=obj.messageOwner.action;
          if (avatarContainer != null && currentEncryptedChat != null && action instanceof TLRPC.TL_messageEncryptedAction && action.encryptedAction instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) {
            avatarContainer.setTime(((TLRPC.TL_decryptedMessageActionSetMessageTTL)action.encryptedAction).ttl_seconds);
          }
          if (action instanceof TLRPC.TL_messageActionChatMigrateTo) {
            migrateToNewChat(obj);
            return;
          }
 else           if (currentChat != null && currentChat.megagroup && (action instanceof TLRPC.TL_messageActionChatAddUser || action instanceof TLRPC.TL_messageActionChatDeleteUser)) {
            reloadMegagroup=true;
          }
          if (a == 0 && obj.messageOwner.id < 0 && obj.type == 5) {
            needAnimateToMessage=obj;
          }
          if (obj.isOut() && obj.isSending()) {
            scrollToLastMessage(false);
            return;
          }
          if (obj.type < 0 || messagesDict[0].indexOfKey(obj.getId()) >= 0) {
            continue;
          }
          if (currentChat != null && currentChat.creator && (action instanceof TLRPC.TL_messageActionChatCreate || action instanceof TLRPC.TL_messageActionChatEditPhoto && messages.size() < 4)) {
            continue;
          }
          if (action instanceof TLRPC.TL_messageActionChannelMigrateFrom) {
            continue;
          }
          addToPolls(obj,null);
          obj.checkLayout();
          currentMaxDate=Math.max(currentMaxDate,obj.messageOwner.date);
          if (obj.getId() > 0) {
            currentMinMsgId=Math.max(obj.getId(),currentMinMsgId);
            last_message_id=Math.max(last_message_id,obj.getId());
          }
 else           if (currentEncryptedChat != null) {
            currentMinMsgId=Math.min(obj.getId(),currentMinMsgId);
            last_message_id=Math.min(last_message_id,obj.getId());
          }
          if (obj.messageOwner.mentioned && obj.isContentUnread()) {
            newMentionsCount++;
          }
          newUnreadMessageCount++;
          if (obj.type == 10 || obj.type == 11) {
            updateChat=true;
          }
        }
        if (newUnreadMessageCount != 0 && pagedownButtonCounter != null) {
          pagedownButtonCounter.setVisibility(View.VISIBLE);
          if (prevSetUnreadCount != newUnreadMessageCount) {
            prevSetUnreadCount=newUnreadMessageCount;
            pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount));
          }
        }
        if (newMentionsCount != 0 && mentiondownButtonCounter != null) {
          mentiondownButtonCounter.setVisibility(View.VISIBLE);
          mentiondownButtonCounter.setText(String.format("%d",newMentionsCount));
          showMentionDownButton(true,true);
        }
        updateVisibleRows();
      }
 else {
        LongSparseArray<MessageObject.GroupedMessages> newGroups=null;
        HashMap<String,ArrayList<MessageObject>> webpagesToReload=null;
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("received new messages " + arr.size() + " in dialog " + dialog_id);
        }
        for (int a=0; a < arr.size(); a++) {
          int placeToPaste=-1;
          MessageObject obj=arr.get(a);
          if (isSecretChat()) {
            checkSecretMessageForLocation(obj);
          }
          if (currentUser != null && (currentUser.bot && obj.isOut() || currentUser.id == currentUserId)) {
            obj.setIsRead();
          }
          TLRPC.MessageAction action=obj.messageOwner.action;
          if (avatarContainer != null && currentEncryptedChat != null && action instanceof TLRPC.TL_messageEncryptedAction && action.encryptedAction instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) {
            avatarContainer.setTime(((TLRPC.TL_decryptedMessageActionSetMessageTTL)action.encryptedAction).ttl_seconds);
          }
          if (obj.type < 0 || messagesDict[0].indexOfKey(obj.getId()) >= 0) {
            continue;
          }
          if (currentChat != null && currentChat.creator && (action instanceof TLRPC.TL_messageActionChatCreate || action instanceof TLRPC.TL_messageActionChatEditPhoto && messages.size() < 4)) {
            continue;
          }
          if (action instanceof TLRPC.TL_messageActionChannelMigrateFrom) {
            continue;
          }
          addToPolls(obj,null);
          if (a == 0 && obj.messageOwner.id < 0 && obj.type == 5) {
            animatingMessageObjects.add(obj);
          }
          MessageObject.GroupedMessages groupedMessages;
          if (obj.hasValidGroupId()) {
            groupedMessages=groupedMessagesMap.get(obj.getGroupId());
            if (groupedMessages == null) {
              groupedMessages=new MessageObject.GroupedMessages();
              groupedMessages.groupId=obj.getGroupId();
              groupedMessagesMap.put(groupedMessages.groupId,groupedMessages);
            }
            if (newGroups == null) {
              newGroups=new LongSparseArray<>();
            }
            newGroups.put(groupedMessages.groupId,groupedMessages);
            groupedMessages.messages.add(obj);
          }
 else {
            groupedMessages=null;
          }
          if (groupedMessages != null) {
            int size=groupedMessages.messages.size();
            MessageObject messageObject=size > 1 ? groupedMessages.messages.get(groupedMessages.messages.size() - 2) : null;
            if (messageObject != null) {
              placeToPaste=messages.indexOf(messageObject);
            }
          }
          if (placeToPaste == -1) {
            if (obj.messageOwner.id < 0 || messages.isEmpty()) {
              placeToPaste=0;
            }
 else {
              int size=messages.size();
              for (int b=0; b < size; b++) {
                MessageObject lastMessage=messages.get(b);
                if (lastMessage.type >= 0 && lastMessage.messageOwner.date > 0) {
                  if (lastMessage.messageOwner.id > 0 && obj.messageOwner.id > 0 && lastMessage.messageOwner.id < obj.messageOwner.id || lastMessage.messageOwner.date < obj.messageOwner.date) {
                    MessageObject.GroupedMessages lastGroupedMessages;
                    if (lastMessage.getGroupId() != 0) {
                      lastGroupedMessages=groupedMessagesMap.get(lastMessage.getGroupId());
                      if (lastGroupedMessages != null && lastGroupedMessages.messages.size() == 0) {
                        lastGroupedMessages=null;
                      }
                    }
 else {
                      lastGroupedMessages=null;
                    }
                    if (lastGroupedMessages == null) {
                      placeToPaste=b;
                    }
 else {
                      placeToPaste=messages.indexOf(lastGroupedMessages.messages.get(lastGroupedMessages.messages.size() - 1));
                    }
                    break;
                  }
                }
              }
              if (placeToPaste == -1 || placeToPaste > messages.size()) {
                placeToPaste=messages.size();
              }
            }
          }
          if (currentEncryptedChat != null && obj.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && obj.messageOwner.media.webpage instanceof TLRPC.TL_webPageUrlPending) {
            if (webpagesToReload == null) {
              webpagesToReload=new HashMap<>();
            }
            ArrayList<MessageObject> arrayList=webpagesToReload.get(obj.messageOwner.media.webpage.url);
            if (arrayList == null) {
              arrayList=new ArrayList<>();
              webpagesToReload.put(obj.messageOwner.media.webpage.url,arrayList);
            }
            arrayList.add(obj);
          }
          obj.checkLayout();
          if (action instanceof TLRPC.TL_messageActionChatMigrateTo) {
            migrateToNewChat(obj);
            if (newGroups != null) {
              for (int b=0; b < newGroups.size(); b++) {
                newGroups.valueAt(b).calculate();
              }
            }
            return;
          }
 else           if (currentChat != null && currentChat.megagroup && (action instanceof TLRPC.TL_messageActionChatAddUser || action instanceof TLRPC.TL_messageActionChatDeleteUser)) {
            reloadMegagroup=true;
          }
          if (minDate[0] == 0 || obj.messageOwner.date < minDate[0]) {
            minDate[0]=obj.messageOwner.date;
          }
          if (obj.isOut()) {
            removeUnreadPlane(true);
            hasFromMe=true;
          }
          if (obj.getId() > 0) {
            maxMessageId[0]=Math.min(obj.getId(),maxMessageId[0]);
            minMessageId[0]=Math.max(obj.getId(),minMessageId[0]);
          }
 else           if (currentEncryptedChat != null) {
            maxMessageId[0]=Math.max(obj.getId(),maxMessageId[0]);
            minMessageId[0]=Math.min(obj.getId(),minMessageId[0]);
          }
          maxDate[0]=Math.max(maxDate[0],obj.messageOwner.date);
          messagesDict[0].put(obj.getId(),obj);
          ArrayList<MessageObject> dayArray=messagesByDays.get(obj.dateKey);
          if (placeToPaste > messages.size()) {
            placeToPaste=messages.size();
          }
          if (dayArray == null) {
            dayArray=new ArrayList<>();
            messagesByDays.put(obj.dateKey,dayArray);
            TLRPC.Message dateMsg=new TLRPC.TL_message();
            dateMsg.message=LocaleController.formatDateChat(obj.messageOwner.date);
            dateMsg.id=0;
            dateMsg.date=obj.messageOwner.date;
            MessageObject dateObj=new MessageObject(currentAccount,dateMsg,false);
            dateObj.type=10;
            dateObj.contentType=1;
            dateObj.isDateObject=true;
            messages.add(placeToPaste,dateObj);
            if (chatAdapter != null) {
              chatAdapter.notifyItemInserted(placeToPaste);
            }
          }
          if (!obj.isOut()) {
            if (paused && placeToPaste == 0) {
              if (!scrollToTopUnReadOnResume && unreadMessageObject != null) {
                removeMessageObject(unreadMessageObject);
                if (placeToPaste > 0) {
                  placeToPaste--;
                }
                unreadMessageObject=null;
              }
              if (unreadMessageObject == null) {
                TLRPC.Message dateMsg=new TLRPC.TL_message();
                dateMsg.message="";
                dateMsg.id=0;
                MessageObject dateObj=new MessageObject(currentAccount,dateMsg,false);
                dateObj.type=6;
                dateObj.contentType=2;
                messages.add(0,dateObj);
                if (chatAdapter != null) {
                  chatAdapter.notifyItemInserted(0);
                }
                unreadMessageObject=dateObj;
                scrollToMessage=unreadMessageObject;
                scrollToMessagePosition=-10000;
                scrollToTopUnReadOnResume=true;
              }
            }
          }
          dayArray.add(0,obj);
          messages.add(placeToPaste,obj);
          if (chatAdapter != null) {
            chatAdapter.notifyItemChanged(placeToPaste);
            chatAdapter.notifyItemInserted(placeToPaste);
          }
          if (!obj.isOut() && obj.messageOwner.mentioned && obj.isContentUnread()) {
            newMentionsCount++;
          }
          newUnreadMessageCount++;
          if (obj.type == 10 || obj.type == 11) {
            updateChat=true;
          }
        }
        if (webpagesToReload != null) {
          MessagesController.getInstance(currentAccount).reloadWebPages(dialog_id,webpagesToReload);
        }
        if (newGroups != null) {
          for (int a=0; a < newGroups.size(); a++) {
            MessageObject.GroupedMessages groupedMessages=newGroups.valueAt(a);
            int oldCount=groupedMessages.posArray.size();
            groupedMessages.calculate();
            int newCount=groupedMessages.posArray.size();
            if (newCount - oldCount > 0 && chatAdapter != null) {
              int index=messages.indexOf(groupedMessages.messages.get(groupedMessages.messages.size() - 1));
              if (index >= 0) {
                chatAdapter.notifyItemRangeChanged(index,newCount);
              }
            }
          }
        }
        if (progressView != null) {
          progressView.setVisibility(View.INVISIBLE);
        }
        if (chatAdapter == null) {
          scrollToTopOnResume=true;
        }
        if (chatListView != null && chatAdapter != null) {
          int lastVisible=chatLayoutManager.findFirstVisibleItemPosition();
          if (lastVisible == RecyclerView.NO_POSITION) {
            lastVisible=0;
          }
          View child=chatLayoutManager.findViewByPosition(lastVisible);
          int diff;
          if (child != null) {
            diff=child.getBottom() - chatListView.getMeasuredHeight();
          }
 else {
            diff=0;
          }
          if (lastVisible == 0 && diff <= AndroidUtilities.dp(5) || hasFromMe) {
            newUnreadMessageCount=0;
            if (!firstLoading) {
              if (paused) {
                scrollToTopOnResume=true;
              }
 else {
                forceScrollToTop=true;
                moveScrollToLastMessage();
              }
            }
          }
 else {
            if (newUnreadMessageCount != 0 && pagedownButtonCounter != null) {
              pagedownButtonCounter.setVisibility(View.VISIBLE);
              if (prevSetUnreadCount != newUnreadMessageCount) {
                prevSetUnreadCount=newUnreadMessageCount;
                pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount));
              }
            }
            showPagedownButton(true,true);
          }
          if (newMentionsCount != 0 && mentiondownButtonCounter != null) {
            mentiondownButtonCounter.setVisibility(View.VISIBLE);
            mentiondownButtonCounter.setText(String.format("%d",newMentionsCount));
            showMentionDownButton(true,true);
          }
        }
 else {
          scrollToTopOnResume=true;
        }
      }
      if (!messages.isEmpty() && botUser != null && botUser.length() == 0) {
        botUser=null;
        updateBottomOverlay();
      }
      if (updateChat) {
        updateTitle();
        checkAndUpdateAvatar();
      }
      if (reloadMegagroup) {
        MessagesController.getInstance(currentAccount).loadFullChat(currentChat.id,0,true);
      }
    }
  }
 else   if (id == NotificationCenter.closeChats) {
    if (args != null && args.length > 0) {
      long did=(Long)args[0];
      if (did == dialog_id) {
        finishFragment();
      }
    }
 else {
      removeSelfFromStack();
    }
  }
 else   if (id == NotificationCenter.messagesRead) {
    SparseLongArray inbox=(SparseLongArray)args[0];
    SparseLongArray outbox=(SparseLongArray)args[1];
    boolean updated=false;
    if (inbox != null) {
      if (bottomOverlayChatText2 != null && chatInfo != null && ChatObject.isChannel(currentChat) && !currentChat.megagroup && -chatInfo.linked_chat_id != 0) {
        bottomOverlayChatText2.updateCounter();
      }
      for (int b=0, size=inbox.size(); b < size; b++) {
        int key=inbox.keyAt(b);
        long messageId=inbox.get(key);
        if (key != dialog_id) {
          continue;
        }
        for (int a=0, size2=messages.size(); a < size2; a++) {
          MessageObject obj=messages.get(a);
          if (!obj.isOut() && obj.getId() > 0 && obj.getId() <= (int)messageId) {
            if (!obj.isUnread()) {
              break;
            }
            obj.setIsRead();
            updated=true;
            newUnreadMessageCount--;
          }
        }
        removeUnreadPlane(false);
        break;
      }
    }
    if (updated) {
      if (newUnreadMessageCount < 0) {
        newUnreadMessageCount=0;
      }
      if (pagedownButtonCounter != null) {
        if (prevSetUnreadCount != newUnreadMessageCount) {
          prevSetUnreadCount=newUnreadMessageCount;
          pagedownButtonCounter.setText(String.format("%d",newUnreadMessageCount));
        }
        if (newUnreadMessageCount <= 0) {
          if (pagedownButtonCounter.getVisibility() != View.INVISIBLE) {
            pagedownButtonCounter.setVisibility(View.INVISIBLE);
          }
        }
 else {
          if (pagedownButtonCounter.getVisibility() != View.VISIBLE) {
            pagedownButtonCounter.setVisibility(View.VISIBLE);
          }
        }
      }
    }
    if (outbox != null) {
      for (int b=0, size=outbox.size(); b < size; b++) {
        int key=outbox.keyAt(b);
        int messageId=(int)((long)outbox.get(key));
        if (key != dialog_id) {
          continue;
        }
        for (int a=0, size2=messages.size(); a < size2; a++) {
          MessageObject obj=messages.get(a);
          if (obj.isOut() && obj.getId() > 0 && obj.getId() <= messageId) {
            if (!obj.isUnread()) {
              break;
            }
            obj.setIsRead();
            updated=true;
          }
        }
        break;
      }
    }
    if (updated) {
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.historyCleared) {
    long did=(Long)args[0];
    if (did != dialog_id) {
      return;
    }
    int max_id=(Integer)args[1];
    boolean updated=false;
    for (int b=0; b < messages.size(); b++) {
      MessageObject obj=messages.get(b);
      int mid=obj.getId();
      if (mid <= 0 || mid > max_id) {
        continue;
      }
      if (chatInfo != null && chatInfo.pinned_msg_id == mid) {
        pinnedMessageObject=null;
        chatInfo.pinned_msg_id=0;
        MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(chatInfo.id,0);
        updatePinnedMessageView(true);
      }
 else       if (userInfo != null && userInfo.pinned_msg_id == mid) {
        pinnedMessageObject=null;
        userInfo.pinned_msg_id=0;
        MessagesStorage.getInstance(currentAccount).updateUserPinnedMessage(chatInfo.id,0);
        updatePinnedMessageView(true);
      }
      messages.remove(b);
      b--;
      messagesDict[0].remove(mid);
      ArrayList<MessageObject> dayArr=messagesByDays.get(obj.dateKey);
      if (dayArr != null) {
        dayArr.remove(obj);
        if (dayArr.isEmpty()) {
          messagesByDays.remove(obj.dateKey);
          if (b >= 0 && b < messages.size()) {
            messages.remove(b);
            b--;
          }
        }
      }
      updated=true;
    }
    if (messages.isEmpty()) {
      if (!endReached[0] && !loading) {
        if (progressView != null) {
          progressView.setVisibility(View.INVISIBLE);
        }
        if (chatListView != null) {
          chatListView.setEmptyView(null);
        }
        if (currentEncryptedChat == null) {
          maxMessageId[0]=maxMessageId[1]=Integer.MAX_VALUE;
          minMessageId[0]=minMessageId[1]=Integer.MIN_VALUE;
        }
 else {
          maxMessageId[0]=maxMessageId[1]=Integer.MIN_VALUE;
          minMessageId[0]=minMessageId[1]=Integer.MAX_VALUE;
        }
        maxDate[0]=maxDate[1]=Integer.MIN_VALUE;
        minDate[0]=minDate[1]=0;
        waitingForLoad.add(lastLoadIndex);
        MessagesController.getInstance(currentAccount).loadMessages(dialog_id,30,0,0,!cacheEndReached[0],minDate[0],classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
        loading=true;
      }
 else {
        if (botButtons != null) {
          botButtons=null;
          if (chatActivityEnterView != null) {
            chatActivityEnterView.setButtons(null,false);
          }
        }
        if (currentEncryptedChat == null && currentUser != null && currentUser.bot && botUser == null) {
          botUser="";
          updateBottomOverlay();
        }
      }
    }
    showPagedownButton(false,true);
    showMentionDownButton(false,true);
    if (updated && chatAdapter != null) {
      removeUnreadPlane(true);
      chatAdapter.notifyDataSetChanged();
    }
  }
 else   if (id == NotificationCenter.messagesDeleted) {
    ArrayList<Integer> markAsDeletedMessages=(ArrayList<Integer>)args[0];
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
    boolean updated=false;
    LongSparseArray<MessageObject.GroupedMessages> newGroups=null;
    int size=markAsDeletedMessages.size();
    boolean updatedSelected=false;
    boolean updatedSelectedLast=false;
    for (int a=0; a < size; a++) {
      Integer ids=markAsDeletedMessages.get(a);
      MessageObject obj=messagesDict[loadIndex].get(ids);
      if (loadIndex == 0 && (chatInfo != null && chatInfo.pinned_msg_id == ids || userInfo != null && userInfo.pinned_msg_id == ids)) {
        pinnedMessageObject=null;
        if (chatInfo != null) {
          chatInfo.pinned_msg_id=0;
        }
 else         if (userInfo != null) {
          userInfo.pinned_msg_id=0;
        }
        MessagesStorage.getInstance(currentAccount).updateChatPinnedMessage(channelId,0);
        updatePinnedMessageView(true);
      }
      if (obj != null) {
        if (editingMessageObject == obj) {
          hideFieldPanel(true);
        }
        int index=messages.indexOf(obj);
        if (index != -1) {
          removeUnreadPlane(false);
          if (selectedMessagesIds[loadIndex].indexOfKey(ids) >= 0) {
            updatedSelected=true;
            addToSelectedMessages(obj,false,updatedSelectedLast=(a == size - 1));
          }
          MessageObject removed=messages.remove(index);
          if (removed.getGroupId() != 0) {
            MessageObject.GroupedMessages groupedMessages=groupedMessagesMap.get(removed.getGroupId());
            if (groupedMessages != null) {
              if (newGroups == null) {
                newGroups=new LongSparseArray<>();
              }
              newGroups.put(groupedMessages.groupId,groupedMessages);
              groupedMessages.messages.remove(obj);
            }
          }
          messagesDict[loadIndex].remove(ids);
          ArrayList<MessageObject> dayArr=messagesByDays.get(obj.dateKey);
          if (dayArr != null) {
            dayArr.remove(obj);
            if (dayArr.isEmpty()) {
              messagesByDays.remove(obj.dateKey);
              if (index >= 0 && index < messages.size()) {
                messages.remove(index);
              }
            }
          }
          updated=true;
        }
      }
    }
    if (updatedSelected) {
      if (!updatedSelectedLast) {
        addToSelectedMessages(null,false,true);
      }
      updateActionModeTitle();
    }
    if (newGroups != null) {
      for (int a=0; a < newGroups.size(); a++) {
        MessageObject.GroupedMessages groupedMessages=newGroups.valueAt(a);
        if (groupedMessages.messages.isEmpty()) {
          groupedMessagesMap.remove(groupedMessages.groupId);
        }
 else {
          groupedMessages.calculate();
          MessageObject messageObject=groupedMessages.messages.get(groupedMessages.messages.size() - 1);
          int index=messages.indexOf(messageObject);
          if (index >= 0) {
            if (chatAdapter != null) {
              chatAdapter.notifyItemRangeChanged(index + chatAdapter.messagesStartRow,groupedMessages.messages.size());
            }
          }
        }
      }
    }
    if (messages.isEmpty()) {
      if (!endReached[0] && !loading) {
        if (progressView != null) {
          progressView.setVisibility(View.INVISIBLE);
        }
        if (chatListView != null) {
          chatListView.setEmptyView(null);
        }
        if (currentEncryptedChat == null) {
          maxMessageId[0]=maxMessageId[1]=Integer.MAX_VALUE;
          minMessageId[0]=minMessageId[1]=Integer.MIN_VALUE;
        }
 else {
          maxMessageId[0]=maxMessageId[1]=Integer.MIN_VALUE;
          minMessageId[0]=minMessageId[1]=Integer.MAX_VALUE;
        }
        maxDate[0]=maxDate[1]=Integer.MIN_VALUE;
        minDate[0]=minDate[1]=0;
        waitingForLoad.add(lastLoadIndex);
        MessagesController.getInstance(currentAccount).loadMessages(dialog_id,30,0,0,!cacheEndReached[0],minDate[0],classGuid,0,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
        loading=true;
      }
 else {
        if (botButtons != null) {
          botButtons=null;
          if (chatActivityEnterView != null) {
            chatActivityEnterView.setButtons(null,false);
          }
        }
        if (currentEncryptedChat == null && currentUser != null && currentUser.bot && botUser == null) {
          botUser="";
          updateBottomOverlay();
        }
      }
      showPagedownButton(false,true);
      showMentionDownButton(false,true);
    }
    if (chatAdapter != null) {
      if (updated) {
        int count=chatListView.getChildCount();
        int position=-1;
        int bottom=0;
        for (int a=0; a < count; a++) {
          View child=chatListView.getChildAt(a);
          MessageObject messageObject=null;
          if (child instanceof ChatMessageCell) {
            messageObject=((ChatMessageCell)child).getMessageObject();
          }
 else           if (child instanceof ChatActionCell) {
            messageObject=((ChatActionCell)child).getMessageObject();
          }
          if (messageObject != null) {
            int idx=messages.indexOf(messageObject);
            if (idx < 0) {
              continue;
            }
            position=chatAdapter.messagesStartRow + idx;
            bottom=child.getBottom();
            break;
          }
        }
        chatAdapter.notifyDataSetChanged();
        if (position != -1) {
          chatLayoutManager.scrollToPositionWithOffset(position,chatListView.getMeasuredHeight() - bottom - chatListView.getPaddingBottom());
        }
      }
 else {
        first_unread_id=0;
        last_message_id=0;
        createUnreadMessageAfterId=0;
        removeMessageObject(unreadMessageObject);
        unreadMessageObject=null;
        if (pagedownButtonCounter != null) {
          pagedownButtonCounter.setVisibility(View.INVISIBLE);
        }
      }
    }
  }
 else   if (id == NotificationCenter.messageReceivedByServer) {
    Integer msgId=(Integer)args[0];
    MessageObject obj=messagesDict[0].get(msgId);
    if (obj != null) {
      Integer newMsgId=(Integer)args[1];
      if (!newMsgId.equals(msgId) && messagesDict[0].indexOfKey(newMsgId) >= 0) {
        MessageObject removed=messagesDict[0].get(msgId);
        messagesDict[0].remove(msgId);
        if (removed != null) {
          int index=messages.indexOf(removed);
          messages.remove(index);
          ArrayList<MessageObject> dayArr=messagesByDays.get(removed.dateKey);
          dayArr.remove(obj);
          if (dayArr.isEmpty()) {
            messagesByDays.remove(obj.dateKey);
            if (index >= 0 && index < messages.size()) {
              messages.remove(index);
            }
          }
          if (chatAdapter != null) {
            chatAdapter.notifyDataSetChanged();
          }
        }
        return;
      }
      TLRPC.Message newMsgObj=(TLRPC.Message)args[2];
      Long grouped_id;
      if (args.length >= 4) {
        grouped_id=(Long)args[4];
      }
 else {
        grouped_id=0L;
      }
      boolean mediaUpdated=false;
      boolean updatedForward=false;
      if (newMsgObj != null) {
        try {
          updatedForward=obj.isForwarded() && (obj.messageOwner.reply_markup == null && newMsgObj.reply_markup != null || !obj.messageOwner.message.equals(newMsgObj.message));
          mediaUpdated=updatedForward || obj.messageOwner.params != null && obj.messageOwner.params.containsKey("query_id") || newMsgObj.media != null && obj.messageOwner.media != null && !newMsgObj.media.getClass().equals(obj.messageOwner.media.getClass());
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        if (obj.getGroupId() != 0 && newMsgObj.grouped_id != 0) {
          MessageObject.GroupedMessages oldGroup=groupedMessagesMap.get(obj.getGroupId());
          if (oldGroup != null) {
            groupedMessagesMap.put(newMsgObj.grouped_id,oldGroup);
          }
          obj.localSentGroupId=obj.messageOwner.grouped_id;
          obj.messageOwner.grouped_id=grouped_id;
        }
        TLRPC.MessageFwdHeader fwdHeader=obj.messageOwner.fwd_from;
        obj.messageOwner=newMsgObj;
        if (fwdHeader != null && newMsgObj.fwd_from != null && !TextUtils.isEmpty(newMsgObj.fwd_from.from_name)) {
          obj.messageOwner.fwd_from=fwdHeader;
        }
        obj.generateThumbs(true);
        obj.setType();
        if (newMsgObj.media instanceof TLRPC.TL_messageMediaGame) {
          obj.applyNewText();
        }
      }
      if (updatedForward) {
        obj.measureInlineBotButtons();
      }
      messagesDict[0].remove(msgId);
      messagesDict[0].put(newMsgId,obj);
      obj.messageOwner.id=newMsgId;
      obj.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
      obj.forceUpdate=mediaUpdated;
      if (args.length >= 6) {
        obj.applyMediaExistanceFlags((Integer)args[5]);
      }
      addToPolls(obj,null);
      ArrayList<MessageObject> messArr=new ArrayList<>();
      messArr.add(obj);
      if (currentEncryptedChat == null) {
        DataQuery.getInstance(currentAccount).loadReplyMessagesForMessages(messArr,dialog_id);
      }
      if (chatAdapter != null) {
        chatAdapter.updateRowWithMessageObject(obj,true);
      }
      if (chatLayoutManager != null) {
        if (mediaUpdated && chatLayoutManager.findFirstVisibleItemPosition() == 0) {
          moveScrollToLastMessage();
        }
      }
      NotificationsController.getInstance(currentAccount).playOutChatSound();
    }
  }
 else   if (id == NotificationCenter.messageReceivedByAck) {
    Integer msgId=(Integer)args[0];
    MessageObject obj=messagesDict[0].get(msgId);
    if (obj != null) {
      obj.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
      if (chatAdapter != null) {
        chatAdapter.updateRowWithMessageObject(obj,true);
      }
    }
  }
 else   if (id == NotificationCenter.messageSendError) {
    Integer msgId=(Integer)args[0];
    MessageObject obj=messagesDict[0].get(msgId);
    if (obj != null) {
      obj.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SEND_ERROR;
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.chatInfoDidLoad) {
    TLRPC.ChatFull chatFull=(TLRPC.ChatFull)args[0];
    if (currentChat != null && chatFull.id == currentChat.id) {
      if (chatFull instanceof TLRPC.TL_channelFull) {
        if (currentChat.megagroup) {
          int lastDate=0;
          if (chatFull.participants != null) {
            for (int a=0; a < chatFull.participants.participants.size(); a++) {
              lastDate=Math.max(chatFull.participants.participants.get(a).date,lastDate);
            }
          }
          if (lastDate == 0 || Math.abs(System.currentTimeMillis() / 1000 - lastDate) > 60 * 60) {
            MessagesController.getInstance(currentAccount).loadChannelParticipants(currentChat.id);
          }
        }
        if (chatFull.participants == null && chatInfo != null) {
          chatFull.participants=chatInfo.participants;
        }
      }
      chatInfo=chatFull;
      if (chatActivityEnterView != null) {
        chatActivityEnterView.setChatInfo(chatInfo);
      }
      if (mentionsAdapter != null) {
        mentionsAdapter.setChatInfo(chatInfo);
      }
      if (args[3] instanceof MessageObject) {
        pinnedMessageObject=(MessageObject)args[3];
        updatePinnedMessageView(false);
      }
 else {
        updatePinnedMessageView(true);
      }
      if (avatarContainer != null) {
        avatarContainer.updateOnlineCount();
        avatarContainer.updateSubtitle();
      }
      if (isBroadcast) {
        SendMessagesHelper.getInstance(currentAccount).setCurrentChatInfo(chatInfo);
      }
      if (chatInfo instanceof TLRPC.TL_chatFull) {
        hasBotsCommands=false;
        botInfo.clear();
        botsCount=0;
        URLSpanBotCommand.enabled=false;
        for (int a=0; a < chatInfo.participants.participants.size(); a++) {
          TLRPC.ChatParticipant participant=chatInfo.participants.participants.get(a);
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(participant.user_id);
          if (user != null && user.bot) {
            URLSpanBotCommand.enabled=true;
            botsCount++;
            DataQuery.getInstance(currentAccount).loadBotInfo(user.id,true,classGuid);
          }
        }
        if (chatListView != null) {
          chatListView.invalidateViews();
        }
      }
 else       if (chatInfo instanceof TLRPC.TL_channelFull) {
        hasBotsCommands=false;
        botInfo.clear();
        botsCount=0;
        URLSpanBotCommand.enabled=!chatInfo.bot_info.isEmpty() && currentChat != null && currentChat.megagroup;
        botsCount=chatInfo.bot_info.size();
        for (int a=0; a < chatInfo.bot_info.size(); a++) {
          TLRPC.BotInfo bot=chatInfo.bot_info.get(a);
          if (!bot.commands.isEmpty() && (!ChatObject.isChannel(currentChat) || currentChat != null && currentChat.megagroup)) {
            hasBotsCommands=true;
          }
          botInfo.put(bot.user_id,bot);
        }
        if (chatListView != null) {
          chatListView.invalidateViews();
        }
        if (mentionsAdapter != null && (!ChatObject.isChannel(currentChat) || currentChat != null && currentChat.megagroup)) {
          mentionsAdapter.setBotInfo(botInfo);
        }
        if (bottomOverlayChatText2 != null && ChatObject.isChannel(currentChat) && !currentChat.megagroup && -chatInfo.linked_chat_id != 0) {
          bottomOverlayChatText2.updateCounter();
        }
      }
      if (chatActivityEnterView != null) {
        chatActivityEnterView.setBotsCount(botsCount,hasBotsCommands);
      }
      if (mentionsAdapter != null) {
        mentionsAdapter.setBotsCount(botsCount);
      }
      if (ChatObject.isChannel(currentChat) && mergeDialogId == 0 && chatInfo.migrated_from_chat_id != 0) {
        mergeDialogId=-chatInfo.migrated_from_chat_id;
        maxMessageId[1]=chatInfo.migrated_from_max_id;
        if (chatAdapter != null) {
          chatAdapter.notifyDataSetChanged();
        }
        if (mergeDialogId != 0 && endReached[0]) {
          checkScrollForLoad(false);
        }
      }
    }
  }
 else   if (id == NotificationCenter.chatInfoCantLoad) {
    int chatId=(Integer)args[0];
    if (currentChat != null && currentChat.id == chatId) {
      int reason=(Integer)args[1];
      if (getParentActivity() == null || closeChatDialog != null) {
        return;
      }
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      if (reason == 0) {
        builder.setMessage(LocaleController.getString("ChannelCantOpenPrivate",R.string.ChannelCantOpenPrivate));
      }
 else       if (reason == 1) {
        builder.setMessage(LocaleController.getString("ChannelCantOpenNa",R.string.ChannelCantOpenNa));
      }
 else       if (reason == 2) {
        builder.setMessage(LocaleController.getString("ChannelCantOpenBanned",R.string.ChannelCantOpenBanned));
      }
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      showDialog(closeChatDialog=builder.create());
      loading=false;
      if (progressView != null) {
        progressView.setVisibility(View.INVISIBLE);
      }
      if (chatAdapter != null) {
        chatAdapter.notifyDataSetChanged();
      }
    }
  }
 else   if (id == NotificationCenter.contactsDidLoad) {
    updateContactStatus();
    if (currentEncryptedChat != null) {
      updateSpamView();
    }
    if (avatarContainer != null) {
      avatarContainer.updateSubtitle();
    }
  }
 else   if (id == NotificationCenter.encryptedChatUpdated) {
    TLRPC.EncryptedChat chat=(TLRPC.EncryptedChat)args[0];
    if (currentEncryptedChat != null && chat.id == currentEncryptedChat.id) {
      currentEncryptedChat=chat;
      updateContactStatus();
      updateSecretStatus();
      initStickers();
      if (chatActivityEnterView != null) {
        chatActivityEnterView.setAllowStickersAndGifs(currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 23,currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 46);
        chatActivityEnterView.checkRoundVideo();
      }
      if (mentionsAdapter != null) {
        mentionsAdapter.setNeedBotContext(!chatActivityEnterView.isEditingMessage() && (currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 46));
      }
    }
  }
 else   if (id == NotificationCenter.messagesReadEncrypted) {
    int encId=(Integer)args[0];
    if (currentEncryptedChat != null && currentEncryptedChat.id == encId) {
      int date=(Integer)args[1];
      for (      MessageObject obj : messages) {
        if (!obj.isOut()) {
          continue;
        }
 else         if (obj.isOut() && !obj.isUnread()) {
          break;
        }
        if (obj.messageOwner.date - 1 <= date) {
          obj.setIsRead();
        }
      }
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.removeAllMessagesFromDialog) {
    long did=(Long)args[0];
    if (dialog_id == did) {
      clearHistory((Boolean)args[1]);
    }
  }
 else   if (id == NotificationCenter.screenshotTook) {
    updateInformationForScreenshotDetector();
  }
 else   if (id == NotificationCenter.blockedUsersDidLoad) {
    if (currentUser != null) {
      boolean oldValue=userBlocked;
      userBlocked=MessagesController.getInstance(currentAccount).blockedUsers.indexOfKey(currentUser.id) >= 0;
      if (oldValue != userBlocked) {
        updateBottomOverlay();
      }
    }
  }
 else   if (id == NotificationCenter.fileNewChunkAvailable) {
    MessageObject messageObject=(MessageObject)args[0];
    long finalSize=(Long)args[3];
    if (finalSize != 0 && dialog_id == messageObject.getDialogId()) {
      MessageObject currentObject=messagesDict[0].get(messageObject.getId());
      if (currentObject != null && currentObject.messageOwner.media.document != null) {
        currentObject.messageOwner.media.document.size=(int)finalSize;
        updateVisibleRows();
      }
    }
  }
 else   if (id == NotificationCenter.didCreatedNewDeleteTask) {
    SparseArray<ArrayList<Long>> mids=(SparseArray<ArrayList<Long>>)args[0];
    boolean changed=false;
    for (int i=0; i < mids.size(); i++) {
      int key=mids.keyAt(i);
      ArrayList<Long> arr=mids.get(key);
      for (int a=0; a < arr.size(); a++) {
        long mid=arr.get(a);
        if (a == 0) {
          int channelId=(int)(mid >> 32);
          if (channelId < 0) {
            channelId=0;
          }
          if (channelId != (ChatObject.isChannel(currentChat) ? currentChat.id : 0)) {
            return;
          }
        }
        MessageObject messageObject=messagesDict[0].get((int)mid);
        if (messageObject != null) {
          messageObject.messageOwner.destroyTime=key;
          changed=true;
        }
      }
    }
    if (changed) {
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.messagePlayingDidStart) {
    MessageObject messageObject=(MessageObject)args[0];
    if (messageObject.eventId != 0) {
      return;
    }
    sendSecretMessageRead(messageObject);
    if ((messageObject.isRoundVideo() || messageObject.isVideo()) && fragmentView != null && fragmentView.getParent() != null) {
      MediaController.getInstance().setTextureView(createTextureView(true),aspectRatioFrameLayout,videoPlayerContainer,true);
      updateTextureViewPosition(true);
    }
    if (chatListView != null) {
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=chatListView.getChildAt(a);
        if (view instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)view;
          MessageObject messageObject1=cell.getMessageObject();
          if (messageObject1 != null) {
            boolean isVideo=messageObject1.isVideo();
            if (messageObject1.isRoundVideo() || isVideo) {
              cell.checkVideoPlayback(false);
              if (!MediaController.getInstance().isPlayingMessage(messageObject1)) {
                if (isVideo && !MediaController.getInstance().isGoingToShowMessageObject(messageObject1)) {
                  AnimatedFileDrawable animation=cell.getPhotoImage().getAnimation();
                  if (animation != null) {
                    animation.start();
                  }
                }
                if (messageObject1.audioProgress != 0) {
                  messageObject1.resetPlayingProgress();
                  cell.invalidate();
                }
              }
 else               if (isVideo) {
                cell.updateButtonState(false,true,false);
              }
            }
 else             if (messageObject1.isVoice() || messageObject1.isMusic()) {
              cell.updateButtonState(false,true,false);
            }
          }
        }
      }
      count=mentionListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=mentionListView.getChildAt(a);
        if (view instanceof ContextLinkCell) {
          ContextLinkCell cell=(ContextLinkCell)view;
          MessageObject messageObject1=cell.getMessageObject();
          if (messageObject1 != null && (messageObject1.isVoice() || messageObject1.isMusic())) {
            cell.updateButtonState(false,true);
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.messagePlayingGoingToStop) {
    boolean injecting=(Boolean)args[1];
    if (injecting) {
      contentView.removeView(videoPlayerContainer);
      videoPlayerContainer=null;
      videoTextureView=null;
      aspectRatioFrameLayout=null;
    }
 else {
      if (chatListView != null && videoPlayerContainer != null && videoPlayerContainer.getTag() != null) {
        MessageObject messageObject=(MessageObject)args[0];
        int count=chatListView.getChildCount();
        for (int a=0; a < count; a++) {
          View view=chatListView.getChildAt(a);
          if (view instanceof ChatMessageCell) {
            ChatMessageCell cell=(ChatMessageCell)view;
            MessageObject messageObject1=cell.getMessageObject();
            if (messageObject == messageObject1) {
              AnimatedFileDrawable animation=cell.getPhotoImage().getAnimation();
              if (animation != null) {
                Bitmap bitmap=animation.getAnimatedBitmap();
                if (bitmap != null) {
                  try {
                    Bitmap src=videoTextureView.getBitmap(bitmap.getWidth(),bitmap.getHeight());
                    Canvas canvas=new Canvas(bitmap);
                    canvas.drawBitmap(src,0,0,null);
                    src.recycle();
                  }
 catch (                  Throwable e) {
                    FileLog.e(e);
                  }
                }
                animation.seekTo(messageObject.audioProgressMs,!FileLoader.getInstance(currentAccount).isLoadingVideo(messageObject.getDocument(),true));
              }
              break;
            }
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.messagePlayingDidReset || id == NotificationCenter.messagePlayingPlayStateChanged) {
    if (id == NotificationCenter.messagePlayingDidReset) {
      destroyTextureView();
    }
    if (chatListView != null) {
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=chatListView.getChildAt(a);
        if (view instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)view;
          MessageObject messageObject=cell.getMessageObject();
          if (messageObject != null) {
            if (messageObject.isVoice() || messageObject.isMusic()) {
              cell.updateButtonState(false,true,false);
            }
 else             if (messageObject.isVideo()) {
              cell.updateButtonState(false,true,false);
              if (!MediaController.getInstance().isPlayingMessage(messageObject) && !MediaController.getInstance().isGoingToShowMessageObject(messageObject)) {
                AnimatedFileDrawable animation=cell.getPhotoImage().getAnimation();
                if (animation != null) {
                  animation.start();
                }
              }
            }
 else             if (messageObject.isRoundVideo()) {
              if (!MediaController.getInstance().isPlayingMessage(messageObject)) {
                cell.checkVideoPlayback(true);
              }
            }
          }
        }
      }
      count=mentionListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=mentionListView.getChildAt(a);
        if (view instanceof ContextLinkCell) {
          ContextLinkCell cell=(ContextLinkCell)view;
          MessageObject messageObject=cell.getMessageObject();
          if (messageObject != null && (messageObject.isVoice() || messageObject.isMusic())) {
            cell.updateButtonState(false,true);
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.messagePlayingProgressDidChanged) {
    Integer mid=(Integer)args[0];
    if (chatListView != null) {
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View view=chatListView.getChildAt(a);
        if (view instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)view;
          MessageObject playing=cell.getMessageObject();
          if (playing != null && playing.getId() == mid) {
            MessageObject player=MediaController.getInstance().getPlayingMessageObject();
            if (player != null) {
              playing.audioProgress=player.audioProgress;
              playing.audioProgressSec=player.audioProgressSec;
              playing.audioPlayerDuration=player.audioPlayerDuration;
              cell.updatePlayingMessageProgress();
              if (drawLaterRoundProgressCell == cell) {
                fragmentView.invalidate();
              }
            }
            break;
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.didUpdatePollResults) {
    long pollId=(Long)args[0];
    ArrayList<MessageObject> arrayList=polls.get(pollId);
    if (arrayList != null) {
      TLRPC.TL_poll poll=(TLRPC.TL_poll)args[1];
      TLRPC.TL_pollResults results=(TLRPC.TL_pollResults)args[2];
      for (int a=0, N=arrayList.size(); a < N; a++) {
        MessageObject object=arrayList.get(a);
        TLRPC.TL_messageMediaPoll media=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
        if (poll != null) {
          media.poll=poll;
        }
        MessageObject.updatePollResults(media,results);
        if (chatAdapter != null) {
          chatAdapter.updateRowWithMessageObject(object,true);
        }
      }
    }
  }
 else   if (id == NotificationCenter.updateMessageMedia) {
    TLRPC.Message message=(TLRPC.Message)args[0];
    MessageObject existMessageObject=messagesDict[0].get(message.id);
    if (existMessageObject != null) {
      existMessageObject.messageOwner.media=message.media;
      existMessageObject.messageOwner.attachPath=message.attachPath;
      existMessageObject.generateThumbs(false);
      if (existMessageObject.getGroupId() != 0 && (existMessageObject.photoThumbs == null || existMessageObject.photoThumbs.isEmpty())) {
        MessageObject.GroupedMessages groupedMessages=groupedMessagesMap.get(existMessageObject.getGroupId());
        if (groupedMessages != null) {
          int idx=groupedMessages.messages.indexOf(existMessageObject);
          if (idx >= 0) {
            int updateCount=groupedMessages.messages.size();
            MessageObject messageObject=null;
            if (idx > 0 && idx < groupedMessages.messages.size() - 1) {
              MessageObject.GroupedMessages slicedGroup=new MessageObject.GroupedMessages();
              slicedGroup.groupId=Utilities.random.nextLong();
              slicedGroup.messages.addAll(groupedMessages.messages.subList(idx + 1,groupedMessages.messages.size()));
              for (int b=0; b < slicedGroup.messages.size(); b++) {
                slicedGroup.messages.get(b).localGroupId=slicedGroup.groupId;
                groupedMessages.messages.remove(idx + 1);
              }
              groupedMessagesMap.put(slicedGroup.groupId,slicedGroup);
              messageObject=slicedGroup.messages.get(slicedGroup.messages.size() - 1);
              slicedGroup.calculate();
            }
            groupedMessages.messages.remove(idx);
            if (groupedMessages.messages.isEmpty()) {
              groupedMessagesMap.remove(groupedMessages.groupId);
            }
 else {
              if (messageObject == null) {
                messageObject=groupedMessages.messages.get(groupedMessages.messages.size() - 1);
              }
              groupedMessages.calculate();
              int index=messages.indexOf(messageObject);
              if (index >= 0) {
                if (chatAdapter != null) {
                  chatAdapter.notifyItemRangeChanged(index + chatAdapter.messagesStartRow,updateCount);
                }
              }
            }
          }
        }
      }
      if (message.media.ttl_seconds != 0 && (message.media.photo instanceof TLRPC.TL_photoEmpty || message.media.document instanceof TLRPC.TL_documentEmpty)) {
        existMessageObject.setType();
        if (chatAdapter != null) {
          chatAdapter.updateRowWithMessageObject(existMessageObject,false);
        }
      }
 else {
        updateVisibleRows();
      }
    }
  }
 else   if (id == NotificationCenter.replaceMessagesObjects) {
    long did=(long)args[0];
    if (did != dialog_id && did != mergeDialogId) {
      return;
    }
    int loadIndex=did == dialog_id ? 0 : 1;
    ArrayList<MessageObject> messageObjects=(ArrayList<MessageObject>)args[1];
    LongSparseArray<MessageObject.GroupedMessages> newGroups=null;
    for (int a=0; a < messageObjects.size(); a++) {
      MessageObject messageObject=messageObjects.get(a);
      MessageObject old=messagesDict[loadIndex].get(messageObject.getId());
      if (pinnedMessageObject != null && pinnedMessageObject.getId() == messageObject.getId()) {
        pinnedMessageObject=messageObject;
        updatePinnedMessageView(true);
      }
      if (old != null) {
        addToPolls(messageObject,old);
        if (messageObject.type >= 0) {
          if (old.replyMessageObject != null) {
            messageObject.replyMessageObject=old.replyMessageObject;
            if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionGameScore) {
              messageObject.generateGameMessageText(null);
            }
 else             if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPaymentSent) {
              messageObject.generatePaymentSentMessageText(null);
            }
          }
          if (!old.isEditing()) {
            if (old.getFileName().equals(messageObject.getFileName())) {
              messageObject.messageOwner.attachPath=old.messageOwner.attachPath;
              messageObject.attachPathExists=old.attachPathExists;
              messageObject.mediaExists=old.mediaExists;
            }
 else {
              messageObject.checkMediaExistance();
            }
          }
          messagesDict[loadIndex].put(old.getId(),messageObject);
        }
 else {
          messagesDict[loadIndex].remove(old.getId());
        }
        int index=messages.indexOf(old);
        if (index >= 0) {
          ArrayList<MessageObject> dayArr=messagesByDays.get(old.dateKey);
          int index2=-1;
          if (dayArr != null) {
            index2=dayArr.indexOf(old);
          }
          if (old.getGroupId() != 0) {
            MessageObject.GroupedMessages groupedMessages=groupedMessagesMap.get(old.getGroupId());
            if (groupedMessages != null) {
              int idx=groupedMessages.messages.indexOf(old);
              if (idx >= 0) {
                if (old.getGroupId() != messageObject.getGroupId()) {
                  groupedMessagesMap.put(messageObject.getGroupId(),groupedMessages);
                }
                if (messageObject.photoThumbs == null || messageObject.photoThumbs.isEmpty()) {
                  if (newGroups == null) {
                    newGroups=new LongSparseArray<>();
                  }
                  newGroups.put(groupedMessages.groupId,groupedMessages);
                  if (idx > 0 && idx < groupedMessages.messages.size() - 1) {
                    MessageObject.GroupedMessages slicedGroup=new MessageObject.GroupedMessages();
                    slicedGroup.groupId=Utilities.random.nextLong();
                    slicedGroup.messages.addAll(groupedMessages.messages.subList(idx + 1,groupedMessages.messages.size()));
                    for (int b=0; b < slicedGroup.messages.size(); b++) {
                      slicedGroup.messages.get(b).localGroupId=slicedGroup.groupId;
                      groupedMessages.messages.remove(idx + 1);
                    }
                    newGroups.put(slicedGroup.groupId,slicedGroup);
                    groupedMessagesMap.put(slicedGroup.groupId,slicedGroup);
                  }
                  groupedMessages.messages.remove(idx);
                }
 else {
                  groupedMessages.messages.set(idx,messageObject);
                  MessageObject.GroupedMessagePosition oldPosition=groupedMessages.positions.remove(old);
                  if (oldPosition != null) {
                    groupedMessages.positions.put(messageObject,oldPosition);
                  }
                  if (newGroups == null) {
                    newGroups=new LongSparseArray<>();
                  }
                  newGroups.put(groupedMessages.groupId,groupedMessages);
                }
              }
            }
          }
          if (messageObject.type >= 0) {
            messages.set(index,messageObject);
            if (chatAdapter != null) {
              chatAdapter.updateRowAtPosition(chatAdapter.messagesStartRow + index);
            }
            if (index2 >= 0) {
              dayArr.set(index2,messageObject);
            }
          }
 else {
            messages.remove(index);
            if (chatAdapter != null) {
              chatAdapter.notifyItemRemoved(chatAdapter.messagesStartRow + index);
            }
            if (index2 >= 0) {
              dayArr.remove(index2);
              if (dayArr.isEmpty()) {
                messagesByDays.remove(old.dateKey);
                messages.remove(index);
                chatAdapter.notifyItemRemoved(chatAdapter.messagesStartRow);
              }
            }
          }
        }
      }
    }
    if (newGroups != null) {
      for (int b=0; b < newGroups.size(); b++) {
        MessageObject.GroupedMessages groupedMessages=newGroups.valueAt(b);
        if (groupedMessages.messages.isEmpty()) {
          groupedMessagesMap.remove(groupedMessages.groupId);
        }
 else {
          groupedMessages.calculate();
          MessageObject messageObject=groupedMessages.messages.get(groupedMessages.messages.size() - 1);
          int index=messages.indexOf(messageObject);
          if (index >= 0) {
            if (chatAdapter != null) {
              chatAdapter.notifyItemRangeChanged(index + chatAdapter.messagesStartRow,groupedMessages.messages.size());
            }
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.notificationsSettingsUpdated) {
    updateTitleIcons();
    if (ChatObject.isChannel(currentChat)) {
      updateBottomOverlay();
    }
  }
 else   if (id == NotificationCenter.replyMessagesDidLoad) {
    long did=(Long)args[0];
    if (did == dialog_id) {
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.pinnedMessageDidLoad) {
    MessageObject message=(MessageObject)args[0];
    if (message.getDialogId() == dialog_id && (chatInfo != null && chatInfo.pinned_msg_id == message.getId() || userInfo != null && userInfo.pinned_msg_id == message.getId())) {
      pinnedMessageObject=message;
      loadingPinnedMessage=0;
      updatePinnedMessageView(true);
    }
  }
 else   if (id == NotificationCenter.didReceivedWebpages) {
    ArrayList<TLRPC.Message> arrayList=(ArrayList<TLRPC.Message>)args[0];
    boolean updated=false;
    for (int a=0; a < arrayList.size(); a++) {
      TLRPC.Message message=arrayList.get(a);
      long did=MessageObject.getDialogId(message);
      if (did != dialog_id && did != mergeDialogId) {
        continue;
      }
      MessageObject currentMessage=messagesDict[did == dialog_id ? 0 : 1].get(message.id);
      if (currentMessage != null) {
        currentMessage.messageOwner.media=new TLRPC.TL_messageMediaWebPage();
        currentMessage.messageOwner.media.webpage=message.media.webpage;
        currentMessage.generateThumbs(true);
        updated=true;
      }
    }
    if (updated) {
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.didReceivedWebpagesInUpdates) {
    if (foundWebPage != null) {
      LongSparseArray<TLRPC.WebPage> hashMap=(LongSparseArray<TLRPC.WebPage>)args[0];
      for (int a=0; a < hashMap.size(); a++) {
        TLRPC.WebPage webPage=hashMap.valueAt(a);
        if (webPage.id == foundWebPage.id) {
          showFieldPanelForWebPage(!(webPage instanceof TLRPC.TL_webPageEmpty),webPage,false);
          break;
        }
      }
    }
  }
 else   if (id == NotificationCenter.messagesReadContent) {
    ArrayList<Long> arrayList=(ArrayList<Long>)args[0];
    boolean updated=false;
    int currentChannelId=ChatObject.isChannel(currentChat) ? currentChat.id : 0;
    for (int a=0; a < arrayList.size(); a++) {
      long mid=arrayList.get(a);
      int channelId=(int)(mid >> 32);
      if (channelId < 0) {
        channelId=0;
      }
      if (channelId != currentChannelId) {
        continue;
      }
      MessageObject currentMessage=messagesDict[0].get((int)mid);
      if (currentMessage != null) {
        currentMessage.setContentIsRead();
        updated=true;
        if (currentMessage.messageOwner.mentioned) {
          newMentionsCount--;
          if (newMentionsCount <= 0) {
            newMentionsCount=0;
            hasAllMentionsLocal=true;
            showMentionDownButton(false,true);
          }
 else {
            if (mentiondownButtonCounter != null) {
              mentiondownButtonCounter.setText(String.format("%d",newMentionsCount));
            }
          }
        }
      }
    }
    if (updated) {
      updateVisibleRows();
    }
  }
 else   if (id == NotificationCenter.botInfoDidLoad) {
    int guid=(Integer)args[1];
    if (classGuid == guid) {
      TLRPC.BotInfo info=(TLRPC.BotInfo)args[0];
      if (currentEncryptedChat == null) {
        if (!info.commands.isEmpty() && !ChatObject.isChannel(currentChat)) {
          hasBotsCommands=true;
        }
        botInfo.put(info.user_id,info);
        if (chatAdapter != null) {
          chatAdapter.notifyItemChanged(chatAdapter.botInfoRow);
        }
        if (mentionsAdapter != null && (!ChatObject.isChannel(currentChat) || currentChat != null && currentChat.megagroup)) {
          mentionsAdapter.setBotInfo(botInfo);
        }
        if (chatActivityEnterView != null) {
          chatActivityEnterView.setBotsCount(botsCount,hasBotsCommands);
        }
      }
      updateBotButtons();
    }
  }
 else   if (id == NotificationCenter.botKeyboardDidLoad) {
    if (dialog_id == (Long)args[1]) {
      TLRPC.Message message=(TLRPC.Message)args[0];
      if (message != null && !userBlocked) {
        botButtons=new MessageObject(currentAccount,message,false);
        checkBotKeyboard();
      }
 else {
        botButtons=null;
        if (chatActivityEnterView != null) {
          if (replyingMessageObject != null && botReplyButtons == replyingMessageObject) {
            botReplyButtons=null;
            hideFieldPanel(true);
          }
          chatActivityEnterView.setButtons(botButtons);
        }
      }
    }
  }
 else   if (id == NotificationCenter.chatSearchResultsAvailable) {
    if (classGuid == (Integer)args[0]) {
      int messageId=(Integer)args[1];
      long did=(Long)args[3];
      if (messageId != 0) {
        scrollToMessageId(messageId,0,true,did == dialog_id ? 0 : 1,false);
      }
 else {
        updateVisibleRows();
      }
      updateSearchButtons((Integer)args[2],(Integer)args[4],(Integer)args[5]);
      if (searchItem != null) {
        searchItem.setShowSearchProgress(false);
      }
    }
  }
 else   if (id == NotificationCenter.chatSearchResultsLoading) {
    if (classGuid == (Integer)args[0] && searchItem != null) {
      searchItem.setShowSearchProgress(true);
    }
  }
 else   if (id == NotificationCenter.didUpdatedMessagesViews) {
    SparseArray<SparseIntArray> channelViews=(SparseArray<SparseIntArray>)args[0];
    SparseIntArray array=channelViews.get((int)dialog_id);
    if (array != null) {
      boolean updated=false;
      for (int a=0; a < array.size(); a++) {
        int messageId=array.keyAt(a);
        MessageObject messageObject=messagesDict[0].get(messageId);
        if (messageObject != null) {
          int newValue=array.get(messageId);
          if (newValue > messageObject.messageOwner.views) {
            messageObject.messageOwner.views=newValue;
            updated=true;
          }
        }
      }
      if (updated) {
        updateVisibleRows();
      }
    }
  }
 else   if (id == NotificationCenter.peerSettingsDidLoad) {
    long did=(Long)args[0];
    if (did == dialog_id) {
      updateSpamView();
    }
  }
 else   if (id == NotificationCenter.newDraftReceived) {
    long did=(Long)args[0];
    if (did == dialog_id) {
      applyDraftMaybe(true);
    }
  }
 else   if (id == NotificationCenter.userInfoDidLoad) {
    Integer uid=(Integer)args[0];
    if (currentUser != null && currentUser.id == uid) {
      userInfo=(TLRPC.UserFull)args[1];
      if (headerItem != null) {
        if (userInfo.phone_calls_available) {
          headerItem.showSubItem(call);
        }
 else {
          headerItem.hideSubItem(call);
        }
      }
      if (args[2] instanceof MessageObject) {
        pinnedMessageObject=(MessageObject)args[2];
        updatePinnedMessageView(false);
      }
 else {
        updatePinnedMessageView(true);
      }
    }
  }
 else   if (id == NotificationCenter.didSetNewWallpapper) {
    if (fragmentView != null) {
      contentView.setBackgroundImage(Theme.getCachedWallpaper(),Theme.isWallpaperMotion());
      progressView2.getBackground().setColorFilter(Theme.colorFilter);
      if (emptyView != null) {
        emptyView.getBackground().setColorFilter(Theme.colorFilter);
      }
      if (bigEmptyView != null) {
        bigEmptyView.getBackground().setColorFilter(Theme.colorFilter);
      }
      chatListView.invalidateViews();
    }
  }
 else   if (id == NotificationCenter.channelRightsUpdated) {
    TLRPC.Chat chat=(TLRPC.Chat)args[0];
    if (currentChat != null && chat.id == currentChat.id && chatActivityEnterView != null) {
      currentChat=chat;
      chatActivityEnterView.checkChannelRights();
      checkRaiseSensors();
      updateSecretStatus();
    }
  }
 else   if (id == NotificationCenter.updateMentionsCount) {
    if (dialog_id == (Long)args[0]) {
      int count=(int)args[1];
      if (newMentionsCount > count) {
        newMentionsCount=count;
        if (newMentionsCount <= 0) {
          newMentionsCount=0;
          hasAllMentionsLocal=true;
          showMentionDownButton(false,true);
        }
 else {
          mentiondownButtonCounter.setText(String.format("%d",newMentionsCount));
        }
      }
    }
  }
 else   if (id == NotificationCenter.audioRecordTooShort) {
    showVoiceHint(false,(Boolean)args[0]);
  }
 else   if (id == NotificationCenter.videoLoadingStateChanged) {
    if (chatListView != null) {
      String fileName=(String)args[0];
      int count=chatListView.getChildCount();
      for (int a=0; a < count; a++) {
        View child=chatListView.getChildAt(a);
        if (!(child instanceof ChatMessageCell)) {
          continue;
        }
        ChatMessageCell cell=(ChatMessageCell)child;
        TLRPC.Document document=cell.getStreamingMedia();
        if (document == null) {
          continue;
        }
        if (FileLoader.getAttachFileName(document).equals(fileName)) {
          cell.updateButtonState(false,true,false);
        }
      }
    }
  }
}
