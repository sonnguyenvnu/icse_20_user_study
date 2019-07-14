public int sendMessage(ArrayList<MessageObject> messages,final long peer){
  if (messages == null || messages.isEmpty()) {
    return 0;
  }
  int lower_id=(int)peer;
  int sendResult=0;
  if (lower_id != 0) {
    final TLRPC.Peer to_id=MessagesController.getInstance(currentAccount).getPeer((int)peer);
    boolean isMegagroup=false;
    boolean isSignature=false;
    boolean canSendStickers=true;
    boolean canSendMedia=true;
    boolean canSendPolls=true;
    boolean canSendPreview=true;
    TLRPC.Chat chat;
    if (lower_id > 0) {
      TLRPC.User sendToUser=MessagesController.getInstance(currentAccount).getUser(lower_id);
      if (sendToUser == null) {
        return 0;
      }
      chat=null;
    }
 else {
      chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
      if (ChatObject.isChannel(chat)) {
        isMegagroup=chat.megagroup;
        isSignature=chat.signatures;
      }
      canSendStickers=ChatObject.canSendStickers(chat);
      canSendMedia=ChatObject.canSendMedia(chat);
      canSendPreview=ChatObject.canSendEmbed(chat);
      canSendPolls=ChatObject.canSendPolls(chat);
    }
    LongSparseArray<Long> groupsMap=new LongSparseArray<>();
    ArrayList<MessageObject> objArr=new ArrayList<>();
    ArrayList<TLRPC.Message> arr=new ArrayList<>();
    ArrayList<Long> randomIds=new ArrayList<>();
    ArrayList<Integer> ids=new ArrayList<>();
    LongSparseArray<TLRPC.Message> messagesByRandomIds=new LongSparseArray<>();
    TLRPC.InputPeer inputPeer=MessagesController.getInstance(currentAccount).getInputPeer(lower_id);
    long lastDialogId=0;
    int myId=UserConfig.getInstance(currentAccount).getClientUserId();
    final boolean toMyself=peer == myId;
    long lastGroupedId;
    for (int a=0; a < messages.size(); a++) {
      MessageObject msgObj=messages.get(a);
      if (msgObj.getId() <= 0 || msgObj.needDrawBluredPreview()) {
        if (msgObj.type == 0 && !TextUtils.isEmpty(msgObj.messageText)) {
          TLRPC.WebPage webPage=msgObj.messageOwner.media != null ? msgObj.messageOwner.media.webpage : null;
          sendMessage(msgObj.messageText.toString(),peer,null,webPage,webPage != null,msgObj.messageOwner.entities,null,null);
        }
        continue;
      }
      if (!canSendStickers && (msgObj.isSticker() || msgObj.isGif() || msgObj.isGame())) {
        if (sendResult == 0) {
          sendResult=ChatObject.isActionBannedByDefault(chat,ChatObject.ACTION_SEND_STICKERS) ? 4 : 1;
        }
        continue;
      }
 else       if (!canSendMedia && (msgObj.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto || msgObj.messageOwner.media instanceof TLRPC.TL_messageMediaDocument)) {
        if (sendResult == 0) {
          sendResult=ChatObject.isActionBannedByDefault(chat,ChatObject.ACTION_SEND_MEDIA) ? 5 : 2;
        }
        continue;
      }
 else       if (!canSendPolls && msgObj.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
        if (sendResult == 0) {
          sendResult=ChatObject.isActionBannedByDefault(chat,ChatObject.ACTION_SEND_POLLS) ? 6 : 3;
        }
        continue;
      }
      boolean groupedIdChanged=false;
      final TLRPC.Message newMsg=new TLRPC.TL_message();
      boolean forwardFromSaved=msgObj.getDialogId() == myId && msgObj.messageOwner.from_id == UserConfig.getInstance(currentAccount).getClientUserId();
      if (msgObj.isForwarded()) {
        newMsg.fwd_from=new TLRPC.TL_messageFwdHeader();
        newMsg.fwd_from.flags=msgObj.messageOwner.fwd_from.flags;
        newMsg.fwd_from.from_id=msgObj.messageOwner.fwd_from.from_id;
        newMsg.fwd_from.date=msgObj.messageOwner.fwd_from.date;
        newMsg.fwd_from.channel_id=msgObj.messageOwner.fwd_from.channel_id;
        newMsg.fwd_from.channel_post=msgObj.messageOwner.fwd_from.channel_post;
        newMsg.fwd_from.post_author=msgObj.messageOwner.fwd_from.post_author;
        newMsg.fwd_from.from_name=msgObj.messageOwner.fwd_from.from_name;
        newMsg.flags=TLRPC.MESSAGE_FLAG_FWD;
      }
 else       if (!forwardFromSaved) {
        newMsg.fwd_from=new TLRPC.TL_messageFwdHeader();
        newMsg.fwd_from.channel_post=msgObj.getId();
        newMsg.fwd_from.flags|=4;
        if (msgObj.isFromUser()) {
          newMsg.fwd_from.from_id=msgObj.messageOwner.from_id;
          newMsg.fwd_from.flags|=1;
        }
 else {
          newMsg.fwd_from.channel_id=msgObj.messageOwner.to_id.channel_id;
          newMsg.fwd_from.flags|=2;
          if (msgObj.messageOwner.post && msgObj.messageOwner.from_id > 0) {
            newMsg.fwd_from.from_id=msgObj.messageOwner.from_id;
            newMsg.fwd_from.flags|=1;
          }
        }
        if (msgObj.messageOwner.post_author != null) {
          newMsg.fwd_from.post_author=msgObj.messageOwner.post_author;
          newMsg.fwd_from.flags|=8;
        }
 else         if (!msgObj.isOutOwner() && msgObj.messageOwner.from_id > 0 && msgObj.messageOwner.post) {
          TLRPC.User signUser=MessagesController.getInstance(currentAccount).getUser(msgObj.messageOwner.from_id);
          if (signUser != null) {
            newMsg.fwd_from.post_author=ContactsController.formatName(signUser.first_name,signUser.last_name);
            newMsg.fwd_from.flags|=8;
          }
        }
        newMsg.date=msgObj.messageOwner.date;
        newMsg.flags=TLRPC.MESSAGE_FLAG_FWD;
      }
      if (peer == myId && newMsg.fwd_from != null) {
        newMsg.fwd_from.flags|=16;
        newMsg.fwd_from.saved_from_msg_id=msgObj.getId();
        newMsg.fwd_from.saved_from_peer=msgObj.messageOwner.to_id;
      }
      if (!canSendPreview && msgObj.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
        newMsg.media=new TLRPC.TL_messageMediaEmpty();
      }
 else {
        newMsg.media=msgObj.messageOwner.media;
      }
      if (newMsg.media != null) {
        newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_MEDIA;
      }
      if (isMegagroup) {
        newMsg.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
      }
      if (msgObj.messageOwner.via_bot_id != 0) {
        newMsg.via_bot_id=msgObj.messageOwner.via_bot_id;
        newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_BOT_ID;
      }
      newMsg.message=msgObj.messageOwner.message;
      newMsg.fwd_msg_id=msgObj.getId();
      newMsg.attachPath=msgObj.messageOwner.attachPath;
      newMsg.entities=msgObj.messageOwner.entities;
      if (msgObj.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) {
        newMsg.flags|=64;
        newMsg.reply_markup=new TLRPC.TL_replyInlineMarkup();
        for (int b=0, N=msgObj.messageOwner.reply_markup.rows.size(); b < N; b++) {
          TLRPC.TL_keyboardButtonRow oldRow=msgObj.messageOwner.reply_markup.rows.get(b);
          TLRPC.TL_keyboardButtonRow newRow=null;
          for (int c=0, N2=oldRow.buttons.size(); c < N2; c++) {
            TLRPC.KeyboardButton button=oldRow.buttons.get(c);
            if (button instanceof TLRPC.TL_keyboardButtonUrlAuth || button instanceof TLRPC.TL_keyboardButtonUrl || button instanceof TLRPC.TL_keyboardButtonSwitchInline) {
              if (button instanceof TLRPC.TL_keyboardButtonUrlAuth) {
                TLRPC.TL_keyboardButtonUrlAuth auth=new TLRPC.TL_keyboardButtonUrlAuth();
                auth.flags=button.flags;
                if (button.fwd_text != null) {
                  auth.text=auth.fwd_text=button.fwd_text;
                }
 else {
                  auth.text=button.text;
                }
                auth.url=button.url;
                auth.button_id=button.button_id;
                button=auth;
              }
              if (newRow == null) {
                newRow=new TLRPC.TL_keyboardButtonRow();
                newMsg.reply_markup.rows.add(newRow);
              }
              newRow.buttons.add(button);
            }
          }
        }
      }
      if (!newMsg.entities.isEmpty()) {
        newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_ENTITIES;
      }
      if (newMsg.attachPath == null) {
        newMsg.attachPath="";
      }
      newMsg.local_id=newMsg.id=UserConfig.getInstance(currentAccount).getNewMessageId();
      newMsg.out=true;
      if ((lastGroupedId=msgObj.messageOwner.grouped_id) != 0) {
        Long gId=groupsMap.get(msgObj.messageOwner.grouped_id);
        if (gId == null) {
          gId=Utilities.random.nextLong();
          groupsMap.put(msgObj.messageOwner.grouped_id,gId);
        }
        newMsg.grouped_id=gId;
        newMsg.flags|=131072;
      }
      if (a != messages.size() - 1) {
        MessageObject next=messages.get(a + 1);
        if (next.messageOwner.grouped_id != msgObj.messageOwner.grouped_id) {
          groupedIdChanged=true;
        }
      }
      if (to_id.channel_id != 0 && !isMegagroup) {
        newMsg.from_id=isSignature ? UserConfig.getInstance(currentAccount).getClientUserId() : -to_id.channel_id;
        newMsg.post=true;
      }
 else {
        newMsg.from_id=UserConfig.getInstance(currentAccount).getClientUserId();
        newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
      }
      if (newMsg.random_id == 0) {
        newMsg.random_id=getNextRandomId();
      }
      randomIds.add(newMsg.random_id);
      messagesByRandomIds.put(newMsg.random_id,newMsg);
      ids.add(newMsg.fwd_msg_id);
      newMsg.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      if (inputPeer instanceof TLRPC.TL_inputPeerChannel && !isMegagroup) {
        newMsg.views=1;
        newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_VIEWS;
      }
 else {
        if ((msgObj.messageOwner.flags & TLRPC.MESSAGE_FLAG_HAS_VIEWS) != 0) {
          newMsg.views=msgObj.messageOwner.views;
          newMsg.flags|=TLRPC.MESSAGE_FLAG_HAS_VIEWS;
        }
        newMsg.unread=true;
      }
      newMsg.dialog_id=peer;
      newMsg.to_id=to_id;
      if (MessageObject.isVoiceMessage(newMsg) || MessageObject.isRoundVideoMessage(newMsg)) {
        if (inputPeer instanceof TLRPC.TL_inputPeerChannel && msgObj.getChannelId() != 0) {
          newMsg.media_unread=msgObj.isContentUnread();
        }
 else {
          newMsg.media_unread=true;
        }
      }
      if (msgObj.messageOwner.to_id instanceof TLRPC.TL_peerChannel) {
        newMsg.ttl=-msgObj.messageOwner.to_id.channel_id;
      }
      MessageObject newMsgObj=new MessageObject(currentAccount,newMsg,true);
      newMsgObj.messageOwner.send_state=MessageObject.MESSAGE_SEND_STATE_SENDING;
      objArr.add(newMsgObj);
      arr.add(newMsg);
      putToSendingMessages(newMsg);
      boolean differentDialog=false;
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("forward message user_id = " + inputPeer.user_id + " chat_id = " + inputPeer.chat_id + " channel_id = " + inputPeer.channel_id + " access_hash = " + inputPeer.access_hash);
      }
      if (groupedIdChanged && arr.size() > 0 || arr.size() == 100 || a == messages.size() - 1 || a != messages.size() - 1 && messages.get(a + 1).getDialogId() != msgObj.getDialogId()) {
        MessagesStorage.getInstance(currentAccount).putMessages(new ArrayList<>(arr),false,true,false,0);
        MessagesController.getInstance(currentAccount).updateInterfaceWithMessages(peer,objArr);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
        UserConfig.getInstance(currentAccount).saveConfig(false);
        final TLRPC.TL_messages_forwardMessages req=new TLRPC.TL_messages_forwardMessages();
        req.to_peer=inputPeer;
        req.grouped=lastGroupedId != 0;
        if (req.to_peer instanceof TLRPC.TL_inputPeerChannel) {
          req.silent=MessagesController.getNotificationsSettings(currentAccount).getBoolean("silent_" + peer,false);
        }
        if (msgObj.messageOwner.to_id instanceof TLRPC.TL_peerChannel) {
          TLRPC.Chat channel=MessagesController.getInstance(currentAccount).getChat(msgObj.messageOwner.to_id.channel_id);
          req.from_peer=new TLRPC.TL_inputPeerChannel();
          req.from_peer.channel_id=msgObj.messageOwner.to_id.channel_id;
          if (channel != null) {
            req.from_peer.access_hash=channel.access_hash;
          }
        }
 else {
          req.from_peer=new TLRPC.TL_inputPeerEmpty();
        }
        req.random_id=randomIds;
        req.id=ids;
        req.with_my_score=messages.size() == 1 && messages.get(0).messageOwner.with_my_score;
        final ArrayList<TLRPC.Message> newMsgObjArr=arr;
        final ArrayList<MessageObject> newMsgArr=objArr;
        final LongSparseArray<TLRPC.Message> messagesByRandomIdsFinal=messagesByRandomIds;
        final boolean isMegagroupFinal=isMegagroup;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
          if (error == null) {
            SparseLongArray newMessagesByIds=new SparseLongArray();
            TLRPC.Updates updates=(TLRPC.Updates)response;
            for (int a1=0; a1 < updates.updates.size(); a1++) {
              TLRPC.Update update=updates.updates.get(a1);
              if (update instanceof TLRPC.TL_updateMessageID) {
                TLRPC.TL_updateMessageID updateMessageID=(TLRPC.TL_updateMessageID)update;
                newMessagesByIds.put(updateMessageID.id,updateMessageID.random_id);
                updates.updates.remove(a1);
                a1--;
              }
            }
            Integer value=MessagesController.getInstance(currentAccount).dialogs_read_outbox_max.get(peer);
            if (value == null) {
              value=MessagesStorage.getInstance(currentAccount).getDialogReadMax(true,peer);
              MessagesController.getInstance(currentAccount).dialogs_read_outbox_max.put(peer,value);
            }
            int sentCount=0;
            for (int a1=0; a1 < updates.updates.size(); a1++) {
              TLRPC.Update update=updates.updates.get(a1);
              if (update instanceof TLRPC.TL_updateNewMessage || update instanceof TLRPC.TL_updateNewChannelMessage) {
                updates.updates.remove(a1);
                a1--;
                final TLRPC.Message message;
                if (update instanceof TLRPC.TL_updateNewMessage) {
                  TLRPC.TL_updateNewMessage updateNewMessage=(TLRPC.TL_updateNewMessage)update;
                  message=updateNewMessage.message;
                  MessagesController.getInstance(currentAccount).processNewDifferenceParams(-1,updateNewMessage.pts,-1,updateNewMessage.pts_count);
                }
 else {
                  TLRPC.TL_updateNewChannelMessage updateNewChannelMessage=(TLRPC.TL_updateNewChannelMessage)update;
                  message=updateNewChannelMessage.message;
                  MessagesController.getInstance(currentAccount).processNewChannelDifferenceParams(updateNewChannelMessage.pts,updateNewChannelMessage.pts_count,message.to_id.channel_id);
                  if (isMegagroupFinal) {
                    message.flags|=TLRPC.MESSAGE_FLAG_MEGAGROUP;
                  }
                }
                ImageLoader.saveMessageThumbs(message);
                message.unread=value < message.id;
                if (toMyself) {
                  message.out=true;
                  message.unread=false;
                  message.media_unread=false;
                }
                long random_id=newMessagesByIds.get(message.id);
                if (random_id != 0) {
                  final TLRPC.Message newMsgObj1=messagesByRandomIdsFinal.get(random_id);
                  if (newMsgObj1 == null) {
                    continue;
                  }
                  int index=newMsgObjArr.indexOf(newMsgObj1);
                  if (index == -1) {
                    continue;
                  }
                  MessageObject msgObj1=newMsgArr.get(index);
                  newMsgObjArr.remove(index);
                  newMsgArr.remove(index);
                  final int oldId=newMsgObj1.id;
                  final ArrayList<TLRPC.Message> sentMessages=new ArrayList<>();
                  sentMessages.add(message);
                  updateMediaPaths(msgObj1,message,message.id,null,true);
                  int existFlags=msgObj1.getMediaExistanceFlags();
                  newMsgObj1.id=message.id;
                  sentCount++;
                  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
                    MessagesStorage.getInstance(currentAccount).updateMessageStateAndId(newMsgObj1.random_id,oldId,newMsgObj1.id,0,false,to_id.channel_id);
                    MessagesStorage.getInstance(currentAccount).putMessages(sentMessages,true,false,false,0);
                    AndroidUtilities.runOnUIThread(() -> {
                      newMsgObj1.send_state=MessageObject.MESSAGE_SEND_STATE_SENT;
                      DataQuery.getInstance(currentAccount).increasePeerRaiting(peer);
                      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messageReceivedByServer,oldId,message.id,message,peer,0L,existFlags);
                      processSentMessage(oldId);
                      removeFromSendingMessages(oldId);
                    }
);
                  }
);
                }
              }
            }
            if (!updates.updates.isEmpty()) {
              MessagesController.getInstance(currentAccount).processUpdates(updates,false);
            }
            StatsController.getInstance(currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(),StatsController.TYPE_MESSAGES,sentCount);
          }
 else {
            AndroidUtilities.runOnUIThread(() -> AlertsCreator.processError(currentAccount,error,null,req));
          }
          for (int a1=0; a1 < newMsgObjArr.size(); a1++) {
            final TLRPC.Message newMsgObj1=newMsgObjArr.get(a1);
            MessagesStorage.getInstance(currentAccount).markMessageAsSendError(newMsgObj1);
            AndroidUtilities.runOnUIThread(() -> {
              newMsgObj1.send_state=MessageObject.MESSAGE_SEND_STATE_SEND_ERROR;
              NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.messageSendError,newMsgObj1.id);
              processSentMessage(newMsgObj1.id);
              removeFromSendingMessages(newMsgObj1.id);
            }
);
          }
        }
,ConnectionsManager.RequestFlagCanCompress | ConnectionsManager.RequestFlagInvokeAfter);
        if (a != messages.size() - 1) {
          objArr=new ArrayList<>();
          arr=new ArrayList<>();
          randomIds=new ArrayList<>();
          ids=new ArrayList<>();
          messagesByRandomIds=new LongSparseArray<>();
        }
      }
    }
  }
 else {
    for (int a=0; a < messages.size(); a++) {
      processForwardFromMyName(messages.get(a),peer);
    }
  }
  return sendResult;
}
