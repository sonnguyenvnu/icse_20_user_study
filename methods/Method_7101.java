private String getShortStringForMessage(MessageObject messageObject,String[] userName,boolean[] preview){
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
      if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
        userName[0]=messageObject.localName;
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
      if (messageObject.messageOwner.to_id.channel_id == 0 || messageObject.isMegagroup()) {
        userName[0]=messageObject.localUserName;
      }
 else       if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
        userName[0]=messageObject.localName;
      }
    }
    return messageObject.messageOwner.message;
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
      if (chat_id != 0) {
        userName[0]=name;
      }
 else {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1) {
          userName[0]=name;
        }
 else {
          userName[0]=null;
        }
      }
    }
  }
 else {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-from_id);
    if (chat != null) {
      name=chat.title;
      userName[0]=name;
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
 else     if (ChatObject.isChannel(chat) && !chat.megagroup) {
      if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
        userName[0]=null;
      }
    }
  }
  String msg=null;
  if ((int)dialog_id == 0) {
    userName[0]=null;
    return LocaleController.getString("YouHaveNewMessage",R.string.YouHaveNewMessage);
  }
 else {
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    boolean isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
    if (chat_id == 0 && from_id != 0 && preferences.getBoolean("EnablePreviewAll",true) || chat_id != 0 && (!isChannel && preferences.getBoolean("EnablePreviewGroup",true) || isChannel && preferences.getBoolean("EnablePreviewChannel",true))) {
      if (messageObject.messageOwner instanceof TLRPC.TL_messageService) {
        userName[0]=null;
        if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionUserJoined || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionContactSignUp) {
          return LocaleController.formatString("NotificationContactJoined",R.string.NotificationContactJoined,name);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionUserUpdatedPhoto) {
          return LocaleController.formatString("NotificationContactNewPhoto",R.string.NotificationContactNewPhoto,name);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionLoginUnknownLocation) {
          String date=LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,LocaleController.getInstance().formatterYear.format(((long)messageObject.messageOwner.date) * 1000),LocaleController.getInstance().formatterDay.format(((long)messageObject.messageOwner.date) * 1000));
          return LocaleController.formatString("NotificationUnrecognizedDevice",R.string.NotificationUnrecognizedDevice,UserConfig.getInstance(currentAccount).getCurrentUser().first_name,date,messageObject.messageOwner.action.title,messageObject.messageOwner.action.address);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionGameScore || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPaymentSent) {
          return messageObject.messageText.toString();
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPhoneCall) {
          TLRPC.PhoneCallDiscardReason reason=messageObject.messageOwner.action.reason;
          if (!messageObject.isOut() && (reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed)) {
            return LocaleController.getString("CallMessageIncomingMissed",R.string.CallMessageIncomingMissed);
          }
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatAddUser) {
          int singleUserId=messageObject.messageOwner.action.user_id;
          if (singleUserId == 0 && messageObject.messageOwner.action.users.size() == 1) {
            singleUserId=messageObject.messageOwner.action.users.get(0);
          }
          if (singleUserId != 0) {
            if (messageObject.messageOwner.to_id.channel_id != 0 && !chat.megagroup) {
              return LocaleController.formatString("ChannelAddedByNotification",R.string.ChannelAddedByNotification,name,chat.title);
            }
 else {
              if (singleUserId == UserConfig.getInstance(currentAccount).getClientUserId()) {
                return LocaleController.formatString("NotificationInvitedToGroup",R.string.NotificationInvitedToGroup,name,chat.title);
              }
 else {
                TLRPC.User u2=MessagesController.getInstance(currentAccount).getUser(singleUserId);
                if (u2 == null) {
                  return null;
                }
                if (from_id == u2.id) {
                  if (chat.megagroup) {
                    return LocaleController.formatString("NotificationGroupAddSelfMega",R.string.NotificationGroupAddSelfMega,name,chat.title);
                  }
 else {
                    return LocaleController.formatString("NotificationGroupAddSelf",R.string.NotificationGroupAddSelf,name,chat.title);
                  }
                }
 else {
                  return LocaleController.formatString("NotificationGroupAddMember",R.string.NotificationGroupAddMember,name,chat.title,UserObject.getUserName(u2));
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
            return LocaleController.formatString("NotificationGroupAddMember",R.string.NotificationGroupAddMember,name,chat.title,names.toString());
          }
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByLink) {
          return LocaleController.formatString("NotificationInvitedToGroupByLink",R.string.NotificationInvitedToGroupByLink,name,chat.title);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatEditTitle) {
          return LocaleController.formatString("NotificationEditedGroupName",R.string.NotificationEditedGroupName,name,messageObject.messageOwner.action.title);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatEditPhoto || messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatDeletePhoto) {
          if (messageObject.messageOwner.to_id.channel_id != 0 && !chat.megagroup) {
            return LocaleController.formatString("ChannelPhotoEditNotification",R.string.ChannelPhotoEditNotification,chat.title);
          }
 else {
            return LocaleController.formatString("NotificationEditedGroupPhoto",R.string.NotificationEditedGroupPhoto,name,chat.title);
          }
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatDeleteUser) {
          if (messageObject.messageOwner.action.user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
            return LocaleController.formatString("NotificationGroupKickYou",R.string.NotificationGroupKickYou,name,chat.title);
          }
 else           if (messageObject.messageOwner.action.user_id == from_id) {
            return LocaleController.formatString("NotificationGroupLeftMember",R.string.NotificationGroupLeftMember,name,chat.title);
          }
 else {
            TLRPC.User u2=MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.action.user_id);
            if (u2 == null) {
              return null;
            }
            return LocaleController.formatString("NotificationGroupKickMember",R.string.NotificationGroupKickMember,name,chat.title,UserObject.getUserName(u2));
          }
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatCreate) {
          return messageObject.messageText.toString();
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChannelCreate) {
          return messageObject.messageText.toString();
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChatMigrateTo) {
          return LocaleController.formatString("ActionMigrateFromGroupNotify",R.string.ActionMigrateFromGroupNotify,chat.title);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionChannelMigrateFrom) {
          return LocaleController.formatString("ActionMigrateFromGroupNotify",R.string.ActionMigrateFromGroupNotify,messageObject.messageOwner.action.title);
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionScreenshotTaken) {
          return messageObject.messageText.toString();
        }
 else         if (messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
          if (chat != null && (!ChatObject.isChannel(chat) || chat.megagroup)) {
            if (messageObject.replyMessageObject == null) {
              return LocaleController.formatString("NotificationActionPinnedNoText",R.string.NotificationActionPinnedNoText,name,chat.title);
            }
 else {
              MessageObject object=messageObject.replyMessageObject;
              if (object.isMusic()) {
                return LocaleController.formatString("NotificationActionPinnedMusic",R.string.NotificationActionPinnedMusic,name,chat.title);
              }
 else               if (object.isVideo()) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDCF9 " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedVideo",R.string.NotificationActionPinnedVideo,name,chat.title);
                }
              }
 else               if (object.isGif()) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83C\uDFAC " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedGif",R.string.NotificationActionPinnedGif,name,chat.title);
                }
              }
 else               if (object.isVoice()) {
                return LocaleController.formatString("NotificationActionPinnedVoice",R.string.NotificationActionPinnedVoice,name,chat.title);
              }
 else               if (object.isRoundVideo()) {
                return LocaleController.formatString("NotificationActionPinnedRound",R.string.NotificationActionPinnedRound,name,chat.title);
              }
 else               if (object.isSticker()) {
                String emoji=object.getStickerEmoji();
                if (emoji != null) {
                  return LocaleController.formatString("NotificationActionPinnedStickerEmoji",R.string.NotificationActionPinnedStickerEmoji,name,chat.title,emoji);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedSticker",R.string.NotificationActionPinnedSticker,name,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDCCE " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedFile",R.string.NotificationActionPinnedFile,name,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || object.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
                return LocaleController.formatString("NotificationActionPinnedGeo",R.string.NotificationActionPinnedGeo,name,chat.title);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
                return LocaleController.formatString("NotificationActionPinnedGeoLive",R.string.NotificationActionPinnedGeoLive,name,chat.title);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
                TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)object.messageOwner.media;
                return LocaleController.formatString("NotificationActionPinnedContact2",R.string.NotificationActionPinnedContact2,name,chat.title,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
                TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
                return LocaleController.formatString("NotificationActionPinnedPoll2",R.string.NotificationActionPinnedPoll2,name,chat.title,mediaPoll.poll.question);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDDBC " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedPhoto",R.string.NotificationActionPinnedPhoto,name,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
                return LocaleController.formatString("NotificationActionPinnedGame",R.string.NotificationActionPinnedGame,name,chat.title);
              }
 else               if (object.messageText != null && object.messageText.length() > 0) {
                CharSequence message=object.messageText;
                if (message.length() > 20) {
                  message=message.subSequence(0,20) + "...";
                }
                return LocaleController.formatString("NotificationActionPinnedText",R.string.NotificationActionPinnedText,name,message,chat.title);
              }
 else {
                return LocaleController.formatString("NotificationActionPinnedNoText",R.string.NotificationActionPinnedNoText,name,chat.title);
              }
            }
          }
 else {
            if (messageObject.replyMessageObject == null) {
              return LocaleController.formatString("NotificationActionPinnedNoTextChannel",R.string.NotificationActionPinnedNoTextChannel,chat.title);
            }
 else {
              MessageObject object=messageObject.replyMessageObject;
              if (object.isMusic()) {
                return LocaleController.formatString("NotificationActionPinnedMusicChannel",R.string.NotificationActionPinnedMusicChannel,chat.title);
              }
 else               if (object.isVideo()) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDCF9 " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedVideoChannel",R.string.NotificationActionPinnedVideoChannel,chat.title);
                }
              }
 else               if (object.isGif()) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83C\uDFAC " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedGifChannel",R.string.NotificationActionPinnedGifChannel,chat.title);
                }
              }
 else               if (object.isVoice()) {
                return LocaleController.formatString("NotificationActionPinnedVoiceChannel",R.string.NotificationActionPinnedVoiceChannel,chat.title);
              }
 else               if (object.isRoundVideo()) {
                return LocaleController.formatString("NotificationActionPinnedRoundChannel",R.string.NotificationActionPinnedRoundChannel,chat.title);
              }
 else               if (object.isSticker()) {
                String emoji=object.getStickerEmoji();
                if (emoji != null) {
                  return LocaleController.formatString("NotificationActionPinnedStickerEmojiChannel",R.string.NotificationActionPinnedStickerEmojiChannel,chat.title,emoji);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedStickerChannel",R.string.NotificationActionPinnedStickerChannel,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDCCE " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedFileChannel",R.string.NotificationActionPinnedFileChannel,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || object.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
                return LocaleController.formatString("NotificationActionPinnedGeoChannel",R.string.NotificationActionPinnedGeoChannel,chat.title);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
                return LocaleController.formatString("NotificationActionPinnedGeoLiveChannel",R.string.NotificationActionPinnedGeoLiveChannel,chat.title);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
                TLRPC.TL_messageMediaContact mediaContact=(TLRPC.TL_messageMediaContact)object.messageOwner.media;
                return LocaleController.formatString("NotificationActionPinnedContactChannel2",R.string.NotificationActionPinnedContactChannel2,chat.title,ContactsController.formatName(mediaContact.first_name,mediaContact.last_name));
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
                TLRPC.TL_messageMediaPoll mediaPoll=(TLRPC.TL_messageMediaPoll)object.messageOwner.media;
                return LocaleController.formatString("NotificationActionPinnedPollChannel2",R.string.NotificationActionPinnedPollChannel2,chat.title,mediaPoll.poll.question);
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
                if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(object.messageOwner.message)) {
                  String message="\uD83D\uDDBC " + object.messageOwner.message;
                  return LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
                }
 else {
                  return LocaleController.formatString("NotificationActionPinnedPhotoChannel",R.string.NotificationActionPinnedPhotoChannel,chat.title);
                }
              }
 else               if (object.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
                return LocaleController.formatString("NotificationActionPinnedGameChannel",R.string.NotificationActionPinnedGameChannel,chat.title);
              }
 else               if (object.messageText != null && object.messageText.length() > 0) {
                CharSequence message=object.messageText;
                if (message.length() > 20) {
                  message=message.subSequence(0,20) + "...";
                }
                return LocaleController.formatString("NotificationActionPinnedTextChannel",R.string.NotificationActionPinnedTextChannel,chat.title,message);
              }
 else {
                return LocaleController.formatString("NotificationActionPinnedNoTextChannel",R.string.NotificationActionPinnedNoTextChannel,chat.title);
              }
            }
          }
        }
      }
 else {
        if (messageObject.isMediaEmpty()) {
          if (!TextUtils.isEmpty(messageObject.messageOwner.message)) {
            return messageObject.messageOwner.message;
          }
 else {
            return LocaleController.getString("Message",R.string.Message);
          }
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
          if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
            return "\uD83D\uDDBC " + messageObject.messageOwner.message;
          }
 else           if (messageObject.messageOwner.media.ttl_seconds != 0) {
            return LocaleController.getString("AttachDestructingPhoto",R.string.AttachDestructingPhoto);
          }
 else {
            return LocaleController.getString("AttachPhoto",R.string.AttachPhoto);
          }
        }
 else         if (messageObject.isVideo()) {
          if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
            return "\uD83D\uDCF9 " + messageObject.messageOwner.message;
          }
 else           if (messageObject.messageOwner.media.ttl_seconds != 0) {
            return LocaleController.getString("AttachDestructingVideo",R.string.AttachDestructingVideo);
          }
 else {
            return LocaleController.getString("AttachVideo",R.string.AttachVideo);
          }
        }
 else         if (messageObject.isGame()) {
          return LocaleController.getString("AttachGame",R.string.AttachGame);
        }
 else         if (messageObject.isVoice()) {
          return LocaleController.getString("AttachAudio",R.string.AttachAudio);
        }
 else         if (messageObject.isRoundVideo()) {
          return LocaleController.getString("AttachRound",R.string.AttachRound);
        }
 else         if (messageObject.isMusic()) {
          return LocaleController.getString("AttachMusic",R.string.AttachMusic);
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaContact) {
          return LocaleController.getString("AttachContact",R.string.AttachContact);
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaPoll) {
          return LocaleController.getString("Poll",R.string.Poll);
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeo || messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaVenue) {
          return LocaleController.getString("AttachLocation",R.string.AttachLocation);
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGeoLive) {
          return LocaleController.getString("AttachLiveLocation",R.string.AttachLiveLocation);
        }
 else         if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
          if (messageObject.isSticker()) {
            String emoji=messageObject.getStickerEmoji();
            if (emoji != null) {
              return emoji + " " + LocaleController.getString("AttachSticker",R.string.AttachSticker);
            }
 else {
              return LocaleController.getString("AttachSticker",R.string.AttachSticker);
            }
          }
 else           if (messageObject.isGif()) {
            if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              return "\uD83C\uDFAC " + messageObject.messageOwner.message;
            }
 else {
              return LocaleController.getString("AttachGif",R.string.AttachGif);
            }
          }
 else {
            if (Build.VERSION.SDK_INT >= 19 && !TextUtils.isEmpty(messageObject.messageOwner.message)) {
              return "\uD83D\uDCCE " + messageObject.messageOwner.message;
            }
 else {
              return LocaleController.getString("AttachDocument",R.string.AttachDocument);
            }
          }
        }
      }
    }
 else {
      if (preview != null) {
        preview[0]=false;
      }
      return LocaleController.getString("Message",R.string.Message);
    }
  }
  return null;
}
