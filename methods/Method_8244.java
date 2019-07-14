public void showFieldPanel(boolean show,MessageObject messageObjectToReply,MessageObject messageObjectToEdit,ArrayList<MessageObject> messageObjectsToForward,TLRPC.WebPage webPage,boolean cancel,boolean animated){
  if (chatActivityEnterView == null) {
    return;
  }
  if (show) {
    if (messageObjectToReply == null && messageObjectsToForward == null && messageObjectToEdit == null && webPage == null) {
      return;
    }
    if (noSoundHintView != null) {
      noSoundHintView.hide();
    }
    if (forwardHintView != null) {
      forwardHintView.hide();
    }
    if (searchItem != null && actionBar.isSearchFieldVisible()) {
      actionBar.closeSearchField(false);
      chatActivityEnterView.setFieldFocused();
      AndroidUtilities.runOnUIThread(() -> {
        if (chatActivityEnterView != null) {
          chatActivityEnterView.openKeyboard();
        }
      }
,100);
    }
    boolean openKeyboard=false;
    if (messageObjectToReply != null && messageObjectToReply.getDialogId() != dialog_id) {
      messageObjectsToForward=new ArrayList<>();
      messageObjectsToForward.add(messageObjectToReply);
      messageObjectToReply=null;
      openKeyboard=true;
    }
    if (messageObjectToEdit != null) {
      forwardingMessages=null;
      replyingMessageObject=null;
      editingMessageObject=messageObjectToEdit;
      chatActivityEnterView.setReplyingMessageObject(null);
      chatActivityEnterView.setEditingMessageObject(messageObjectToEdit,!messageObjectToEdit.isMediaEmpty());
      if (foundWebPage != null) {
        return;
      }
      chatActivityEnterView.setForceShowSendButton(false,false);
      replyIconImageView.setImageResource(R.drawable.group_edit);
      replyIconImageView.setContentDescription(LocaleController.getString("AccDescrEditing",R.string.AccDescrEditing));
      replyCloseImageView.setContentDescription(LocaleController.getString("AccDescrCancelEdit",R.string.AccDescrCancelEdit));
      if (messageObjectToEdit.isMediaEmpty()) {
        replyNameTextView.setText(LocaleController.getString("EditMessage",R.string.EditMessage));
      }
 else {
        replyNameTextView.setText(LocaleController.getString("EditCaption",R.string.EditCaption));
      }
      if (messageObjectToEdit.canEditMedia()) {
        replyObjectTextView.setText(LocaleController.getString("EditMessageMedia",R.string.EditMessageMedia));
      }
 else       if (messageObjectToEdit.messageText != null) {
        String mess=messageObjectToEdit.messageText.toString();
        if (mess.length() > 150) {
          mess=mess.substring(0,150);
        }
        mess=mess.replace('\n',' ');
        replyObjectTextView.setText(Emoji.replaceEmoji(mess,replyObjectTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
    }
 else     if (messageObjectToReply != null) {
      forwardingMessages=null;
      editingMessageObject=null;
      replyingMessageObject=messageObjectToReply;
      chatActivityEnterView.setReplyingMessageObject(messageObjectToReply);
      chatActivityEnterView.setEditingMessageObject(null,false);
      if (foundWebPage != null) {
        return;
      }
      chatActivityEnterView.setForceShowSendButton(false,false);
      String name;
      if (messageObjectToReply.isFromUser()) {
        TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(messageObjectToReply.messageOwner.from_id);
        if (user == null) {
          return;
        }
        name=UserObject.getUserName(user);
      }
 else {
        TLRPC.Chat chat;
        if (ChatObject.isChannel(currentChat) && currentChat.megagroup && messageObjectToReply.isForwardedChannelPost()) {
          chat=MessagesController.getInstance(currentAccount).getChat(messageObjectToReply.messageOwner.fwd_from.channel_id);
        }
 else {
          chat=MessagesController.getInstance(currentAccount).getChat(messageObjectToReply.messageOwner.to_id.channel_id);
        }
        if (chat == null) {
          return;
        }
        name=chat.title;
      }
      replyIconImageView.setImageResource(R.drawable.msg_panel_reply);
      replyNameTextView.setText(name);
      replyIconImageView.setContentDescription(LocaleController.getString("AccDescrReplying",R.string.AccDescrReplying));
      replyCloseImageView.setContentDescription(LocaleController.getString("AccDescrCancelReply",R.string.AccDescrCancelReply));
      if (messageObjectToReply.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
        replyObjectTextView.setText(Emoji.replaceEmoji(messageObjectToReply.messageOwner.media.game.title,replyObjectTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
 else       if (messageObjectToReply.messageText != null) {
        String mess=messageObjectToReply.messageText.toString();
        if (mess.length() > 150) {
          mess=mess.substring(0,150);
        }
        mess=mess.replace('\n',' ');
        replyObjectTextView.setText(Emoji.replaceEmoji(mess,replyObjectTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
    }
 else     if (messageObjectsToForward != null) {
      if (messageObjectsToForward.isEmpty()) {
        return;
      }
      replyingMessageObject=null;
      editingMessageObject=null;
      chatActivityEnterView.setReplyingMessageObject(null);
      chatActivityEnterView.setEditingMessageObject(null,false);
      forwardingMessages=messageObjectsToForward;
      if (foundWebPage != null) {
        return;
      }
      chatActivityEnterView.setForceShowSendButton(true,false);
      ArrayList<Integer> uids=new ArrayList<>();
      replyIconImageView.setImageResource(R.drawable.msg_panel_forward);
      replyIconImageView.setContentDescription(LocaleController.getString("AccDescrForwarding",R.string.AccDescrForwarding));
      replyCloseImageView.setContentDescription(LocaleController.getString("AccDescrCancelForward",R.string.AccDescrCancelForward));
      MessageObject object=messageObjectsToForward.get(0);
      if (object.isFromUser()) {
        uids.add(object.messageOwner.from_id);
      }
 else {
        TLRPC.Chat chat=getMessagesController().getChat(object.messageOwner.to_id.channel_id);
        if (ChatObject.isChannel(chat) && chat.megagroup && object.isForwardedChannelPost()) {
          uids.add(-object.messageOwner.fwd_from.channel_id);
        }
 else {
          uids.add(-object.messageOwner.to_id.channel_id);
        }
      }
      int type=messageObjectsToForward.get(0).type;
      for (int a=1; a < messageObjectsToForward.size(); a++) {
        object=messageObjectsToForward.get(a);
        int uid;
        if (object.isFromUser()) {
          uid=object.messageOwner.from_id;
        }
 else {
          TLRPC.Chat chat=getMessagesController().getChat(object.messageOwner.to_id.channel_id);
          if (ChatObject.isChannel(chat) && chat.megagroup && object.isForwardedChannelPost()) {
            uid=-object.messageOwner.fwd_from.channel_id;
          }
 else {
            uid=-object.messageOwner.to_id.channel_id;
          }
        }
        if (!uids.contains(uid)) {
          uids.add(uid);
        }
        if (messageObjectsToForward.get(a).type != type) {
          type=-1;
        }
      }
      StringBuilder userNames=new StringBuilder();
      for (int a=0; a < uids.size(); a++) {
        Integer uid=uids.get(a);
        TLRPC.Chat chat=null;
        TLRPC.User user=null;
        if (uid > 0) {
          user=MessagesController.getInstance(currentAccount).getUser(uid);
        }
 else {
          chat=MessagesController.getInstance(currentAccount).getChat(-uid);
        }
        if (user == null && chat == null) {
          continue;
        }
        if (uids.size() == 1) {
          if (user != null) {
            userNames.append(UserObject.getUserName(user));
          }
 else {
            userNames.append(chat.title);
          }
        }
 else         if (uids.size() == 2 || userNames.length() == 0) {
          if (userNames.length() > 0) {
            userNames.append(", ");
          }
          if (user != null) {
            if (!TextUtils.isEmpty(user.first_name)) {
              userNames.append(user.first_name);
            }
 else             if (!TextUtils.isEmpty(user.last_name)) {
              userNames.append(user.last_name);
            }
 else {
              userNames.append(" ");
            }
          }
 else {
            userNames.append(chat.title);
          }
        }
 else {
          userNames.append(" ");
          userNames.append(LocaleController.formatPluralString("AndOther",uids.size() - 1));
          break;
        }
      }
      replyNameTextView.setText(userNames);
      if (type == -1 || type == 0 || type == 10 || type == 11) {
        if (messageObjectsToForward.size() == 1 && messageObjectsToForward.get(0).messageText != null) {
          MessageObject messageObject=messageObjectsToForward.get(0);
          if (messageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
            replyObjectTextView.setText(Emoji.replaceEmoji(messageObject.messageOwner.media.game.title,replyObjectTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
          }
 else {
            String mess=messageObject.messageText.toString();
            if (mess.length() > 150) {
              mess=mess.substring(0,150);
            }
            mess=mess.replace('\n',' ');
            replyObjectTextView.setText(Emoji.replaceEmoji(mess,replyObjectTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
          }
        }
 else {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedMessageCount",messageObjectsToForward.size()));
        }
      }
 else {
        if (type == 1) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedPhoto",messageObjectsToForward.size()));
          if (messageObjectsToForward.size() == 1) {
            messageObjectToReply=messageObjectsToForward.get(0);
          }
        }
 else         if (type == 4) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedLocation",messageObjectsToForward.size()));
        }
 else         if (type == 3) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedVideo",messageObjectsToForward.size()));
          if (messageObjectsToForward.size() == 1) {
            messageObjectToReply=messageObjectsToForward.get(0);
          }
        }
 else         if (type == 12) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedContact",messageObjectsToForward.size()));
        }
 else         if (type == 2) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedAudio",messageObjectsToForward.size()));
        }
 else         if (type == 5) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedRound",messageObjectsToForward.size()));
        }
 else         if (type == 14) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedMusic",messageObjectsToForward.size()));
        }
 else         if (type == 13) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedSticker",messageObjectsToForward.size()));
        }
 else         if (type == 17) {
          replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedPoll",messageObjectsToForward.size()));
        }
 else         if (type == 8 || type == 9) {
          if (messageObjectsToForward.size() == 1) {
            if (type == 8) {
              replyObjectTextView.setText(LocaleController.getString("AttachGif",R.string.AttachGif));
            }
 else {
              String name;
              if ((name=FileLoader.getDocumentFileName(messageObjectsToForward.get(0).getDocument())).length() != 0) {
                replyObjectTextView.setText(name);
              }
              messageObjectToReply=messageObjectsToForward.get(0);
            }
          }
 else {
            replyObjectTextView.setText(LocaleController.formatPluralString("ForwardedFile",messageObjectsToForward.size()));
          }
        }
      }
    }
 else {
      replyIconImageView.setImageResource(R.drawable.msg_link);
      if (webPage instanceof TLRPC.TL_webPagePending) {
        replyNameTextView.setText(LocaleController.getString("GettingLinkInfo",R.string.GettingLinkInfo));
        replyObjectTextView.setText(pendingLinkSearchString);
      }
 else {
        if (webPage.site_name != null) {
          replyNameTextView.setText(webPage.site_name);
        }
 else         if (webPage.title != null) {
          replyNameTextView.setText(webPage.title);
        }
 else {
          replyNameTextView.setText(LocaleController.getString("LinkPreview",R.string.LinkPreview));
        }
        if (webPage.title != null) {
          replyObjectTextView.setText(webPage.title);
        }
 else         if (webPage.description != null) {
          replyObjectTextView.setText(webPage.description);
        }
 else         if (webPage.author != null) {
          replyObjectTextView.setText(webPage.author);
        }
 else {
          replyObjectTextView.setText(webPage.display_url);
        }
        chatActivityEnterView.setWebPage(webPage,true);
      }
    }
    MessageObject thumbMediaMessageObject;
    if (messageObjectToReply != null) {
      thumbMediaMessageObject=messageObjectToReply;
    }
 else     if (messageObjectToEdit != null) {
      thumbMediaMessageObject=messageObjectToEdit;
    }
 else {
      thumbMediaMessageObject=null;
    }
    FrameLayout.LayoutParams layoutParams1=(FrameLayout.LayoutParams)replyNameTextView.getLayoutParams();
    FrameLayout.LayoutParams layoutParams2=(FrameLayout.LayoutParams)replyObjectTextView.getLayoutParams();
    int cacheType=1;
    int size=0;
    TLRPC.PhotoSize photoSize=null;
    TLRPC.PhotoSize thumbPhotoSize=null;
    TLObject photoSizeObject=null;
    if (thumbMediaMessageObject != null) {
      photoSize=FileLoader.getClosestPhotoSizeWithSize(thumbMediaMessageObject.photoThumbs2,320);
      thumbPhotoSize=FileLoader.getClosestPhotoSizeWithSize(thumbMediaMessageObject.photoThumbs2,AndroidUtilities.dp(40));
      photoSizeObject=thumbMediaMessageObject.photoThumbsObject2;
      if (photoSize == null) {
        if (thumbMediaMessageObject.mediaExists) {
          photoSize=FileLoader.getClosestPhotoSizeWithSize(thumbMediaMessageObject.photoThumbs,AndroidUtilities.getPhotoSize());
          if (photoSize != null) {
            size=photoSize.size;
          }
          cacheType=0;
        }
 else {
          photoSize=FileLoader.getClosestPhotoSizeWithSize(thumbMediaMessageObject.photoThumbs,320);
        }
        thumbPhotoSize=FileLoader.getClosestPhotoSizeWithSize(thumbMediaMessageObject.photoThumbs,AndroidUtilities.dp(40));
        photoSizeObject=thumbMediaMessageObject.photoThumbsObject;
      }
    }
    if (photoSize == thumbPhotoSize) {
      thumbPhotoSize=null;
    }
    if (photoSize == null || photoSize instanceof TLRPC.TL_photoSizeEmpty || photoSize.location instanceof TLRPC.TL_fileLocationUnavailable || thumbMediaMessageObject.type == 13 || thumbMediaMessageObject != null && thumbMediaMessageObject.isSecretMedia()) {
      replyImageView.setImageBitmap(null);
      replyImageLocation=null;
      replyImageLocationObject=null;
      replyImageView.setVisibility(View.INVISIBLE);
      layoutParams1.leftMargin=layoutParams2.leftMargin=AndroidUtilities.dp(52);
    }
 else {
      if (thumbMediaMessageObject != null && thumbMediaMessageObject.isRoundVideo()) {
        replyImageView.setRoundRadius(AndroidUtilities.dp(17));
      }
 else {
        replyImageView.setRoundRadius(0);
      }
      replyImageSize=size;
      replyImageCacheType=cacheType;
      replyImageLocation=photoSize;
      replyImageThumbLocation=thumbPhotoSize;
      replyImageLocationObject=photoSizeObject;
      replyImageView.setImage(ImageLocation.getForObject(replyImageLocation,photoSizeObject),"50_50",ImageLocation.getForObject(thumbPhotoSize,photoSizeObject),"50_50_b",null,size,cacheType,thumbMediaMessageObject);
      replyImageView.setVisibility(View.VISIBLE);
      layoutParams1.leftMargin=layoutParams2.leftMargin=AndroidUtilities.dp(96);
    }
    replyNameTextView.setLayoutParams(layoutParams1);
    replyObjectTextView.setLayoutParams(layoutParams2);
    chatActivityEnterView.showTopView(true,openKeyboard);
  }
 else {
    if (replyingMessageObject == null && forwardingMessages == null && foundWebPage == null && editingMessageObject == null) {
      return;
    }
    if (replyingMessageObject != null && replyingMessageObject.messageOwner.reply_markup instanceof TLRPC.TL_replyKeyboardForceReply) {
      SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
      preferences.edit().putInt("answered_" + dialog_id,replyingMessageObject.getId()).commit();
    }
    if (foundWebPage != null) {
      foundWebPage=null;
      chatActivityEnterView.setWebPage(null,!cancel);
      if (webPage != null && (replyingMessageObject != null || forwardingMessages != null || editingMessageObject != null)) {
        showFieldPanel(true,replyingMessageObject,editingMessageObject,forwardingMessages,null,false,true);
        return;
      }
    }
    if (forwardingMessages != null) {
      forwardMessages(forwardingMessages,false);
    }
    chatActivityEnterView.setForceShowSendButton(false,false);
    chatActivityEnterView.hideTopView(animated);
    chatActivityEnterView.setReplyingMessageObject(null);
    chatActivityEnterView.setEditingMessageObject(null,false);
    replyingMessageObject=null;
    editingMessageObject=null;
    forwardingMessages=null;
    replyImageLocation=null;
    replyImageLocationObject=null;
  }
}
