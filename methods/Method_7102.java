private String getStringForMessage(MessageObject messageObject,boolean shortMessage,boolean[] text,boolean[] preview){
  if (AndroidUtilities.needShowPasscode(false) || SharedConfig.isWaitingForPasscodeEnter) {
    return LocaleController.getString("YouHaveNewMessage",R.string.YouHaveNewMessage);
  }
  long dialog_id=messageObject.messageOwner.dialog_id;
  int chat_id=messageObject.messageOwner.to_id.chat_id != 0 ? messageObject.messageOwner.to_id.chat_id : messageObject.messageOwner.to_id.channel_id;
  int from_id=messageObject.messageOwner.to_id.user_id;
  if (preview != null) {
    preview[0]=true;
  }
  if (messageObject.isFcmMessage()) {
    if (chat_id == 0 && from_id != 0) {
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      if (!preferences.getBoolean("EnablePreviewAll",true)) {
        if (preview != null) {
          preview[0]=false;
        }
        return LocaleController.formatString("NotificationMessageNoText",R.string.NotificationMessageNoText,messageObject.localName);
      }
    }
 else     if (chat_id != 0) {
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      if (!messageObject.localChannel && !preferences.getBoolean("EnablePreviewGroup",true) || messageObject.localChannel && !preferences.getBoolean("EnablePreviewChannel",true)) {
        if (preview != null) {
          preview[0]=false;
        }
        if (!messageObject.isMegagroup() && messageObject.messageOwner.to_id.channel_id != 0) {
          return LocaleController.formatString("ChannelMessageNoText",R.string.ChannelMessageNoText,messageObject.localName);
        }
 else {
          return LocaleController.formatString("NotificationMessageGroupNoText",R.string.NotificationMessageGroupNoText,messageObject.localUserName,messageObject.localName);
        }
      }
    }
    text[0]=true;
    return (String)messageObject.messageText;
  }
  if (from_id == 0) {
    if (messageObject.isFromUser() || messageObject.getId() < 0) {
      from_id=messageObject.messageOwner.from_id;
    }
 else {
      from_id=-chat_id;
    }
  }
 else   if (from_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
    from_id=messageObject.messageOwner.from_id;
  }
  if (dialog_id == 0) {
    if (chat_id != 0) {
      dialog_id=-chat_id;
    }
 else     if (from_id != 0) {
      dialog_id=from_id;
    }
  }
  String name=null;
  if (from_id > 0) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(from_id);
    if (user != null) {
      name=UserObject.getUserName(user);
    }
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-from_id);
    if (chat != null) {
      name=chat.title;
    }
  }
  if (name == null) {
    return null;
  }
  TLRPC.Chat chat=null;
  if (chat_id != 0) {
    chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
    if (chat == null) {
      return null;
    }
  }
  String msg=null;
  if ((int)dialog_id == 0) {
    msg=LocaleController.getString("YouHaveNewMessage",R.string.YouHaveNewMessage);
  }
 else {
    if (chat_id == 0 && from_id != 0) {
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      if (preferences.getBoolean("EnablePreviewAll",true)) {
        if (messageObject.messageOwner instanceof TLRPC.TL_messageService) {
          if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionUserJoined || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionContactSignUp) {
            msg=LocaleController.formatString("NotificationContactJoined",R.string.NotificationContactJoined,name);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionUserUpdatedPhoto) {
            msg=LocaleController.formatString("NotificationContactNewPhoto",R.string.NotificationContactNewPhoto,name);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionLoginUnknownLocation) {
            String date=LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,LocaleController.getInstance().formatterYear.format(((long)messageObject.messageOwner.date) * 1000),LocaleController.getInstance().formatterDay.format(((long)messageObject.messageOwner.date) * 1000));
            msg=LocaleController.formatString("NotificationUnrecognizedDevice",R.string.NotificationUnrecognizedDevice,UserConfig.getInstance(currentAccount).getCurrentUser().first_name,date,messageObject.messageOwner.action.title,messageObject.messageOwner.action.address);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionGameScore || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPaymentSent) {
            msg=messageObject.messageText.toString();
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPhoneCall) {
            TLRPC.PhoneCallDiscardReason reason=messageObject.messageOwner.action.reason;
            if (!messageObject.isOut() && (reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed)) {
              msg=LocaleController.getString("CallMessageIncomingMissed",R.string.CallMessageIncomingMissed);
            }
          }
        }
 else {
          if (messageObject.isMediaEmpty()) {
            if (!shortMessage) {
              if (!TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,messageObject.messageOwner.message);
                text[0]=true;
              }
 else {
                msg=LocaleController.formatString("NotificationMessageNoText",R.string.NotificationMessageNoText,name);
              }
            }
 else {
              msg=LocaleController.formatString("NotificationMessageNoText",R.string.NotificationMessageNoText,name);
            }
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDDBC " + messageObject.messageOwner.message);
              text[0]=true;
            }
 else {
              if (messageObject.messageOwner.media.ttl_seconds != 0) {
                msg=LocaleController.formatString("NotificationMessageSDPhoto",R.string.NotificationMessageSDPhoto,name);
              }
 else {
                msg=LocaleController.formatString("NotificationMessagePhoto",R.string.NotificationMessagePhoto,name);
              }
            }
          }
 else           if (messageObject.isVideo()) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDCF9 " + messageObject.messageOwner.message);
              text[0]=true;
            }
 else {
              if (messageObject.messageOwner.media.ttl_seconds != 0) {
                msg=LocaleController.formatString("NotificationMessageSDVideo",R.string.NotificationMessageSDVideo,name);
              }
 else {
                msg=LocaleController.formatString("NotificationMessageVideo",R.string.NotificationMessageVideo,name);
              }
            }
          }
 else           if (messageObject.isGame()) {
            msg=LocaleController.formatString("NotificationMessageGame",R.string.NotificationMessageGame,name,messageObject.messageOwner.media.game.title);
          }
 else           if (messageObject.isVoice()) {
            msg=LocaleController.formatString("NotificationMessageAudio",R.string.NotificationMessageAudio,name);
          }
 else           if (messageObject.isRoundVideo()) {
            msg=LocaleController.formatString("NotificationMessageRound",R.string.NotificationMessageRound,name);
          }
 else           if (messageObject.isMusic()) {
            msg=LocaleController.formatString("NotificationMessageMusic",R.string.NotificationMessageMusic,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
            TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)messageObject.messageOwner.media;
            msg=LocaleController.formatString("NotificationMessageContact2",R.string.NotificationMessageContact2,name,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
            TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)messageObject.messageOwner.media;
            msg=LocaleController.formatString("NotificationMessagePoll2",R.string.NotificationMessagePoll2,name,mediaPoll.poll.question);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
            msg=LocaleController.formatString("NotificationMessageMap",R.string.NotificationMessageMap,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
            msg=LocaleController.formatString("NotificationMessageLiveLocation",R.string.NotificationMessageLiveLocation,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
            if (messageObject.isSticker()) {
              String emoji=messageObject.getStickerEmoji();
              if (emoji != null) {
                msg=LocaleController.formatString("NotificationMessageStickerEmoji",R.string.NotificationMessageStickerEmoji,name,emoji);
              }
 else {
                msg=LocaleController.formatString("NotificationMessageSticker",R.string.NotificationMessageSticker,name);
              }
            }
 else             if (messageObject.isGif()) {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83C\uDFAC " + messageObject.messageOwner.message);
                text[0]=true;
              }
 else {
                msg=LocaleController.formatString("NotificationMessageGif",R.string.NotificationMessageGif,name);
              }
            }
 else {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDCCE " + messageObject.messageOwner.message);
                text[0]=true;
              }
 else {
                msg=LocaleController.formatString("NotificationMessageDocument",R.string.NotificationMessageDocument,name);
              }
            }
          }
        }
      }
 else {
        if (preview != null) {
          preview[0]=false;
        }
        msg=LocaleController.formatString("NotificationMessageNoText",R.string.NotificationMessageNoText,name);
      }
    }
 else     if (chat_id != 0) {
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      boolean isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
      if (!isChannel && preferences.getBoolean("EnablePreviewGroup",true) || isChannel && preferences.getBoolean("EnablePreviewChannel",true)) {
        if (messageObject.messageOwner instanceof TLRPC.TL_messageService) {
          if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatAddUser) {
            int singleUserId=messageObject.messageOwner.action.user_id;
            if (singleUserId == 0 && messageObject.messageOwner.action.users.size() == 1) {
              singleUserId=messageObject.messageOwner.action.users.get(0);
            }
            if (singleUserId != 0) {
              if (messageObject.messageOwner.to_id.channel_id != 0 && !chat.megagroup) {
                msg=LocaleController.formatString("ChannelAddedByNotification",R.string.ChannelAddedByNotification,name,chat.title);
              }
 else {
                if (singleUserId == UserConfig.getInstance(currentAccount).getClientUserId()) {
                  msg=LocaleController.formatString("NotificationInvitedToGroup",R.string.NotificationInvitedToGroup,name,chat.title);
                }
 else {
                  TLRPC.User u2=MessagesController.getInstance(currentAccount).getUser(singleUserId);
                  if (u2 == null) {
                    return null;
                  }
                  if (from_id == u2.id) {
                    if (chat.megagroup) {
                      msg=LocaleController.formatString("NotificationGroupAddSelfMega",R.string.NotificationGroupAddSelfMega,name,chat.title);
                    }
 else {
                      msg=LocaleController.formatString("NotificationGroupAddSelf",R.string.NotificationGroupAddSelf,name,chat.title);
                    }
                  }
 else {
                    msg=LocaleController.formatString("NotificationGroupAddMember",R.string.NotificationGroupAddMember,name,chat.title,UserObject.getUserName(u2));
                  }
                }
              }
            }
 else {
              StringBuilder names=new StringBuilder();
              for (int a=0; a < messageObject.messageOwner.action.users.size(); a++) {
                TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.action.users.get(a));
                if (user != null) {
                  String name2=UserObject.getUserName(user);
                  if (names.length() != 0) {
                    names.append(", ");
                  }
                  names.append(name2);
                }
              }
              msg=LocaleController.formatString("NotificationGroupAddMember",R.string.NotificationGroupAddMember,name,chat.title,names.toString());
            }
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByLink) {
            msg=LocaleController.formatString("NotificationInvitedToGroupByLink",R.string.NotificationInvitedToGroupByLink,name,chat.title);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatEditTitle) {
            msg=LocaleController.formatString("NotificationEditedGroupName",R.string.NotificationEditedGroupName,name,messageObject.messageOwner.action.title);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatEditPhoto || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatDeletePhoto) {
            if (messageObject.messageOwner.to_id.channel_id != 0 && !chat.megagroup) {
              msg=LocaleController.formatString("ChannelPhotoEditNotification",R.string.ChannelPhotoEditNotification,chat.title);
            }
 else {
              msg=LocaleController.formatString("NotificationEditedGroupPhoto",R.string.NotificationEditedGroupPhoto,name,chat.title);
            }
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatDeleteUser) {
            if (messageObject.messageOwner.action.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
              msg=LocaleController.formatString("NotificationGroupKickYou",R.string.NotificationGroupKickYou,name,chat.title);
            }
 else             if (messageObject.messageOwner.action.user_id == from_id) {
              msg=LocaleController.formatString("NotificationGroupLeftMember",R.string.NotificationGroupLeftMember,name,chat.title);
            }
 else {
              TLRPC.User u2=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.action.user_id);
              if (u2 == null) {
                return null;
              }
              msg=LocaleController.formatString("NotificationGroupKickMember",R.string.NotificationGroupKickMember,name,chat.title,UserObject.getUserName(u2));
            }
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatCreate) {
            msg=messageObject.messageText.toString();
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChannelCreate) {
            msg=messageObject.messageText.toString();
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatMigrateTo) {
            msg=LocaleController.formatString("ActionMigrateFromGroupNotify",R.string.ActionMigrateFromGroupNotify,chat.title);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChannelMigrateFrom) {
            msg=LocaleController.formatString("ActionMigrateFromGroupNotify",R.string.ActionMigrateFromGroupNotify,messageObject.messageOwner.action.title);
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionScreenshotTaken) {
            msg=messageObject.messageText.toString();
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
            if (chat != null && (!ChatObject.isChannel(chat) || chat.megagroup)) {
              if (messageObject.replyMessageObject == null) {
                msg=LocaleController.formatString("NotificationActionPinnedNoText",R.string.NotificationActionPinnedNoText,name,chat.title);
              }
 else {
                MessageObject object=messageObject.replyMessageObject;
                if (object.isMusic()) {
                  msg=LocaleController.formatString("NotificationActionPinnedMusic",R.string.NotificationActionPinnedMusic,name,chat.title);
                }
 else                 if (object.isVideo()) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDCF9 " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedVideo",R.string.NotificationActionPinnedVideo,name,chat.title);
                  }
                }
 else                 if (object.isGif()) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83C\uDFAC " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedGif",R.string.NotificationActionPinnedGif,name,chat.title);
                  }
                }
 else                 if (object.isVoice()) {
                  msg=LocaleController.formatString("NotificationActionPinnedVoice",R.string.NotificationActionPinnedVoice,name,chat.title);
                }
 else                 if (object.isRoundVideo()) {
                  msg=LocaleController.formatString("NotificationActionPinnedRound",R.string.NotificationActionPinnedRound,name,chat.title);
                }
 else                 if (object.isSticker()) {
                  String emoji=object.getStickerEmoji();
                  if (emoji != null) {
                    msg=LocaleController.formatString("NotificationActionPinnedStickerEmoji",R.string.NotificationActionPinnedStickerEmoji,name,chat.title,emoji);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedSticker",R.string.NotificationActionPinnedSticker,name,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDCCE " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedFile",R.string.NotificationActionPinnedFile,name,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || object.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
                  msg=LocaleController.formatString("NotificationActionPinnedGeo",R.string.NotificationActionPinnedGeo,name,chat.title);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
                  msg=LocaleController.formatString("NotificationActionPinnedGeoLive",R.string.NotificationActionPinnedGeoLive,name,chat.title);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
                  TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)messageObject.messageOwner.media;
                  msg=LocaleController.formatString("NotificationActionPinnedContact2",R.string.NotificationActionPinnedContact2,name,chat.title,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
                  TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
                  msg=LocaleController.formatString("NotificationActionPinnedPoll2",R.string.NotificationActionPinnedPoll2,name,chat.title,mediaPoll.poll.question);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDDBC " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedPhoto",R.string.NotificationActionPinnedPhoto,name,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
                  msg=LocaleController.formatString("NotificationActionPinnedGame",R.string.NotificationActionPinnedGame,name,chat.title);
                }
 else                 if (object.messageText != null && object.messageText.length() > 0) {
                  CharSequence message=object.messageText;
                  if (message.length() > 20) {
                    message=message.subSequence(0,20) + "...";
                  }
                  msg=LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                }
 else {
                  msg=LocaleController.formatString("NotificationActionPinnedNoText",R.string.NotificationActionPinnedNoText,name,chat.title);
                }
              }
            }
 else {
              if (messageObject.replyMessageObject == null) {
                msg=LocaleController.formatString("NotificationActionPinnedNoTextChannel",R.string.NotificationActionPinnedNoTextChannel,chat.title);
              }
 else {
                MessageObject object=messageObject.replyMessageObject;
                if (object.isMusic()) {
                  msg=LocaleController.formatString("NotificationActionPinnedMusicChannel",R.string.NotificationActionPinnedMusicChannel,chat.title);
                }
 else                 if (object.isVideo()) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDCF9 " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedVideoChannel",R.string.NotificationActionPinnedVideoChannel,chat.title);
                  }
                }
 else                 if (object.isGif()) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83C\uDFAC " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedGifChannel",R.string.NotificationActionPinnedGifChannel,chat.title);
                  }
                }
 else                 if (object.isVoice()) {
                  msg=LocaleController.formatString("NotificationActionPinnedVoiceChannel",R.string.NotificationActionPinnedVoiceChannel,chat.title);
                }
 else                 if (object.isRoundVideo()) {
                  msg=LocaleController.formatString("NotificationActionPinnedRoundChannel",R.string.NotificationActionPinnedRoundChannel,chat.title);
                }
 else                 if (object.isSticker()) {
                  String emoji=object.getStickerEmoji();
                  if (emoji != null) {
                    msg=LocaleController.formatString("NotificationActionPinnedStickerEmojiChannel",R.string.NotificationActionPinnedStickerEmojiChannel,chat.title,emoji);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedStickerChannel",R.string.NotificationActionPinnedStickerChannel,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDCCE " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedFileChannel",R.string.NotificationActionPinnedFileChannel,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || object.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
                  msg=LocaleController.formatString("NotificationActionPinnedGeoChannel",R.string.NotificationActionPinnedGeoChannel,chat.title);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
                  msg=LocaleController.formatString("NotificationActionPinnedGeoLiveChannel",R.string.NotificationActionPinnedGeoLiveChannel,chat.title);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
                  TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)messageObject.messageOwner.media;
                  msg=LocaleController.formatString("NotificationActionPinnedContactChannel2",R.string.NotificationActionPinnedContactChannel2,chat.title,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
                  TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
                  msg=LocaleController.formatString("NotificationActionPinnedPollChannel2",R.string.NotificationActionPinnedPollChannel2,chat.title,mediaPoll.poll.question);
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
                  if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                    String message="\uD83D\uDDBC " + object.messageOwner.message;
                    msg=LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                  }
 else {
                    msg=LocaleController.formatString("NotificationActionPinnedPhotoChannel",R.string.NotificationActionPinnedPhotoChannel,chat.title);
                  }
                }
 else                 if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
                  msg=LocaleController.formatString("NotificationActionPinnedGameChannel",R.string.NotificationActionPinnedGameChannel,chat.title);
                }
 else                 if (object.messageText != null && object.messageText.length() > 0) {
                  CharSequence message=object.messageText;
                  if (message.length() > 20) {
                    message=message.subSequence(0,20) + "...";
                  }
                  msg=LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                }
 else {
                  msg=LocaleController.formatString("NotificationActionPinnedNoTextChannel",R.string.NotificationActionPinnedNoTextChannel,chat.title);
                }
              }
            }
          }
 else           if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionGameScore) {
            msg=messageObject.messageText.toString();
          }
        }
 else         if (ChatObject.isChannel(chat) && !chat.megagroup) {
          if (messageObject.isMediaEmpty()) {
            if (!shortMessage && messageObject.messageOwner.message != null && messageObject.messageOwner.message.length() != 0) {
              msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,messageObject.messageOwner.message);
              text[0]=true;
            }
 else {
              msg=LocaleController.formatString("ChannelMessageNoText",R.string.ChannelMessageNoText,name);
            }
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDDBC " + messageObject.messageOwner.message);
              text[0]=true;
            }
 else {
              msg=LocaleController.formatString("ChannelMessagePhoto",R.string.ChannelMessagePhoto,name);
            }
          }
 else           if (messageObject.isVideo()) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDCF9 " + messageObject.messageOwner.message);
              text[0]=true;
            }
 else {
              msg=LocaleController.formatString("ChannelMessageVideo",R.string.ChannelMessageVideo,name);
            }
          }
 else           if (messageObject.isVoice()) {
            msg=LocaleController.formatString("ChannelMessageAudio",R.string.ChannelMessageAudio,name);
          }
 else           if (messageObject.isRoundVideo()) {
            msg=LocaleController.formatString("ChannelMessageRound",R.string.ChannelMessageRound,name);
          }
 else           if (messageObject.isMusic()) {
            msg=LocaleController.formatString("ChannelMessageMusic",R.string.ChannelMessageMusic,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
            TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)messageObject.messageOwner.media;
            msg=LocaleController.formatString("ChannelMessageContact2",R.string.ChannelMessageContact2,name,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
            TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)messageObject.messageOwner.media;
            msg=LocaleController.formatString("ChannelMessagePoll2",R.string.ChannelMessagePoll2,name,mediaPoll.poll.question);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
            msg=LocaleController.formatString("ChannelMessageMap",R.string.ChannelMessageMap,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
            msg=LocaleController.formatString("ChannelMessageLiveLocation",R.string.ChannelMessageLiveLocation,name);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
            if (messageObject.isSticker()) {
              String emoji=messageObject.getStickerEmoji();
              if (emoji != null) {
                msg=LocaleController.formatString("ChannelMessageStickerEmoji",R.string.ChannelMessageStickerEmoji,name,emoji);
              }
 else {
                msg=LocaleController.formatString("ChannelMessageSticker",R.string.ChannelMessageSticker,name);
              }
            }
 else             if (messageObject.isGif()) {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83C\uDFAC " + messageObject.messageOwner.message);
                text[0]=true;
              }
 else {
                msg=LocaleController.formatString("ChannelMessageGIF",R.string.ChannelMessageGIF,name);
              }
            }
 else {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageText",R.string.NotificationMessageText,name,"\uD83D\uDCCE " + messageObject.messageOwner.message);
                text[0]=true;
              }
 else {
                msg=LocaleController.formatString("ChannelMessageDocument",R.string.ChannelMessageDocument,name);
              }
            }
          }
        }
 else {
          if (messageObject.isMediaEmpty()) {
            if (!shortMessage && messageObject.messageOwner.message != null && messageObject.messageOwner.message.length() != 0) {
              msg=LocaleController.formatString("NotificationMessageGroupText",R.string.NotificationMessageGroupText,name,chat.title,messageObject.messageOwner.message);
            }
 else {
              msg=LocaleController.formatString("NotificationMessageGroupNoText",R.string.NotificationMessageGroupNoText,name,chat.title);
            }
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageGroupText",R.string.NotificationMessageGroupText,name,chat.title,"\uD83D\uDDBC " + messageObject.messageOwner.message);
            }
 else {
              msg=LocaleController.formatString("NotificationMessageGroupPhoto",R.string.NotificationMessageGroupPhoto,name,chat.title);
            }
          }
 else           if (messageObject.isVideo()) {
            if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              msg=LocaleController.formatString("NotificationMessageGroupText",R.string.NotificationMessageGroupText,name,chat.title,"\uD83D\uDCF9 " + messageObject.messageOwner.message);
            }
 else {
              msg=LocaleController.formatString(" ",R.string.NotificationMessageGroupVideo,name,chat.title);
            }
          }
 else           if (messageObject.isVoice()) {
            msg=LocaleController.formatString("NotificationMessageGroupAudio",R.string.NotificationMessageGroupAudio,name,chat.title);
          }
 else           if (messageObject.isRoundVideo()) {
            msg=LocaleController.formatString("NotificationMessageGroupRound",R.string.NotificationMessageGroupRound,name,chat.title);
          }
 else           if (messageObject.isMusic()) {
            msg=LocaleController.formatString("NotificationMessageGroupMusic",R.string.NotificationMessageGroupMusic,name,chat.title);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
            TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)messageObject.messageOwner.media;
            msg=LocaleController.formatString("NotificationMessageGroupContact2",R.string.NotificationMessageGroupContact2,name,chat.title,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
            TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)messageObject.messageOwner.media;
            msg=LocaleController.formatString("NotificationMessageGroupPoll2",R.string.NotificationMessageGroupPoll2,name,chat.title,mediaPoll.poll.question);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
            msg=LocaleController.formatString("NotificationMessageGroupGame",R.string.NotificationMessageGroupGame,name,chat.title,messageObject.messageOwner.media.game.title);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
            msg=LocaleController.formatString("NotificationMessageGroupMap",R.string.NotificationMessageGroupMap,name,chat.title);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
            msg=LocaleController.formatString("NotificationMessageGroupLiveLocation",R.string.NotificationMessageGroupLiveLocation,name,chat.title);
          }
 else           if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
            if (messageObject.isSticker()) {
              String emoji=messageObject.getStickerEmoji();
              if (emoji != null) {
                msg=LocaleController.formatString("NotificationMessageGroupStickerEmoji",R.string.NotificationMessageGroupStickerEmoji,name,chat.title,emoji);
              }
 else {
                msg=LocaleController.formatString("NotificationMessageGroupSticker",R.string.NotificationMessageGroupSticker,name,chat.title);
              }
            }
 else             if (messageObject.isGif()) {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageGroupText",R.string.NotificationMessageGroupText,name,chat.title,"\uD83C\uDFAC " + messageObject.messageOwner.message);
              }
 else {
                msg=LocaleController.formatString("NotificationMessageGroupGif",R.string.NotificationMessageGroupGif,name,chat.title);
              }
            }
 else {
              if (!shortMessage && Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
                msg=LocaleController.formatString("NotificationMessageGroupText",R.string.NotificationMessageGroupText,name,chat.title,"\uD83D\uDCCE " + messageObject.messageOwner.message);
              }
 else {
                msg=LocaleController.formatString("NotificationMessageGroupDocument",R.string.NotificationMessageGroupDocument,name,chat.title);
              }
            }
          }
        }
      }
 else {
        if (preview != null) {
          preview[0]=false;
        }
        if (ChatObject.isChannel(chat) && !chat.megagroup) {
          msg=LocaleController.formatString("ChannelMessageNoText",R.string.ChannelMessageNoText,name);
        }
 else {
          msg=LocaleController.formatString("NotificationMessageGroupNoText",R.string.NotificationMessageGroupNoText,name,chat.title);
        }
      }
    }
  }
  return msg;
}
