private void updatePinnedMessageView(boolean animated){
  if (pinnedMessageView == null) {
    return;
  }
  int pinned_msg_id;
  if (chatInfo != null) {
    if (pinnedMessageObject != null && chatInfo.pinned_msg_id != pinnedMessageObject.getId()) {
      pinnedMessageObject=null;
    }
    if (chatInfo.pinned_msg_id != 0 && pinnedMessageObject == null) {
      pinnedMessageObject=messagesDict[0].get(chatInfo.pinned_msg_id);
    }
    pinned_msg_id=chatInfo.pinned_msg_id;
  }
 else   if (userInfo != null) {
    if (pinnedMessageObject != null && userInfo.pinned_msg_id != pinnedMessageObject.getId()) {
      pinnedMessageObject=null;
    }
    if (userInfo.pinned_msg_id != 0 && pinnedMessageObject == null) {
      pinnedMessageObject=messagesDict[0].get(userInfo.pinned_msg_id);
    }
    pinned_msg_id=userInfo.pinned_msg_id;
  }
 else {
    pinned_msg_id=0;
  }
  SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
  if (chatInfo == null && userInfo == null || pinned_msg_id == 0 || pinned_msg_id == preferences.getInt("pin_" + dialog_id,0) || actionBar != null && (actionBar.isActionModeShowed() || actionBar.isSearchFieldVisible())) {
    hidePinnedMessageView(animated);
  }
 else {
    if (pinnedMessageObject != null) {
      if (pinnedMessageView.getTag() != null) {
        pinnedMessageView.setTag(null);
        if (pinnedMessageViewAnimator != null) {
          pinnedMessageViewAnimator.cancel();
          pinnedMessageViewAnimator=null;
        }
        if (animated) {
          pinnedMessageView.setVisibility(View.VISIBLE);
          pinnedMessageViewAnimator=new AnimatorSet();
          pinnedMessageViewAnimator.playTogether(ObjectAnimator.ofFloat(pinnedMessageView,View.TRANSLATION_Y,0));
          pinnedMessageViewAnimator.setDuration(200);
          pinnedMessageViewAnimator.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (pinnedMessageViewAnimator != null && pinnedMessageViewAnimator.equals(animation)) {
                pinnedMessageViewAnimator=null;
              }
            }
            @Override public void onAnimationCancel(            Animator animation){
              if (pinnedMessageViewAnimator != null && pinnedMessageViewAnimator.equals(animation)) {
                pinnedMessageViewAnimator=null;
              }
            }
          }
);
          pinnedMessageViewAnimator.start();
        }
 else {
          pinnedMessageView.setTranslationY(0);
          pinnedMessageView.setVisibility(View.VISIBLE);
        }
      }
      FrameLayout.LayoutParams layoutParams1=(FrameLayout.LayoutParams)pinnedMessageNameTextView.getLayoutParams();
      FrameLayout.LayoutParams layoutParams2=(FrameLayout.LayoutParams)pinnedMessageTextView.getLayoutParams();
      int cacheType=1;
      int size=0;
      TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(pinnedMessageObject.photoThumbs2,AndroidUtilities.dp(320));
      TLRPC.PhotoSize thumbPhotoSize=FileLoader.getClosestPhotoSizeWithSize(pinnedMessageObject.photoThumbs2,AndroidUtilities.dp(40));
      TLObject photoSizeObject=pinnedMessageObject.photoThumbsObject2;
      if (photoSize == null) {
        if (pinnedMessageObject.mediaExists) {
          photoSize=FileLoader.getClosestPhotoSizeWithSize(pinnedMessageObject.photoThumbs,AndroidUtilities.getPhotoSize());
          if (photoSize != null) {
            size=photoSize.size;
          }
          cacheType=0;
        }
 else {
          photoSize=FileLoader.getClosestPhotoSizeWithSize(pinnedMessageObject.photoThumbs,AndroidUtilities.dp(320));
        }
        thumbPhotoSize=FileLoader.getClosestPhotoSizeWithSize(pinnedMessageObject.photoThumbs,AndroidUtilities.dp(40));
        photoSizeObject=pinnedMessageObject.photoThumbsObject;
      }
      if (photoSize == thumbPhotoSize) {
        thumbPhotoSize=null;
      }
      if (photoSize == null || photoSize instanceof TLRPC.TL_photoSizeEmpty || photoSize.location instanceof TLRPC.TL_fileLocationUnavailable || pinnedMessageObject.type == 13) {
        pinnedMessageImageView.setImageBitmap(null);
        pinnedImageLocation=null;
        pinnedImageLocationObject=null;
        pinnedMessageImageView.setVisibility(View.INVISIBLE);
        layoutParams1.leftMargin=layoutParams2.leftMargin=AndroidUtilities.dp(18);
      }
 else {
        if (pinnedMessageObject.isRoundVideo()) {
          pinnedMessageImageView.setRoundRadius(AndroidUtilities.dp(16));
        }
 else {
          pinnedMessageImageView.setRoundRadius(0);
        }
        pinnedImageSize=size;
        pinnedImageCacheType=cacheType;
        pinnedImageLocation=photoSize;
        pinnedImageThumbLocation=thumbPhotoSize;
        pinnedImageLocationObject=photoSizeObject;
        pinnedMessageImageView.setImage(ImageLocation.getForObject(pinnedImageLocation,photoSizeObject),"50_50",ImageLocation.getForObject(thumbPhotoSize,photoSizeObject),"50_50_b",null,size,cacheType,pinnedMessageObject);
        pinnedMessageImageView.setVisibility(View.VISIBLE);
        layoutParams1.leftMargin=layoutParams2.leftMargin=AndroidUtilities.dp(55);
      }
      pinnedMessageNameTextView.setLayoutParams(layoutParams1);
      pinnedMessageTextView.setLayoutParams(layoutParams2);
      if (pinnedMessageObject.type == MessageObject.TYPE_POLL) {
        pinnedMessageNameTextView.setText(LocaleController.getString("PinnedPoll",R.string.PinnedPoll));
      }
 else {
        pinnedMessageNameTextView.setText(LocaleController.getString("PinnedMessage",R.string.PinnedMessage));
      }
      if (pinnedMessageObject.type == 14) {
        pinnedMessageTextView.setText(String.format("%s - %s",pinnedMessageObject.getMusicAuthor(),pinnedMessageObject.getMusicTitle()));
      }
 else       if (pinnedMessageObject.type == MessageObject.TYPE_POLL) {
        TLRPC.TL_messageMediaPoll poll=(TLRPC.TL_messageMediaPoll)pinnedMessageObject.messageOwner.media;
        String mess=poll.poll.question;
        if (mess.length() > 150) {
          mess=mess.substring(0,150);
        }
        mess=mess.replace('\n',' ');
        pinnedMessageTextView.setText(mess);
      }
 else       if (pinnedMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame) {
        pinnedMessageTextView.setText(Emoji.replaceEmoji(pinnedMessageObject.messageOwner.media.game.title,pinnedMessageTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
 else       if (!TextUtils.isEmpty(pinnedMessageObject.caption)) {
        String mess=pinnedMessageObject.caption.toString();
        if (mess.length() > 150) {
          mess=mess.substring(0,150);
        }
        mess=mess.replace('\n',' ');
        pinnedMessageTextView.setText(Emoji.replaceEmoji(mess,pinnedMessageTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
 else       if (pinnedMessageObject.messageText != null) {
        String mess=pinnedMessageObject.messageText.toString();
        if (mess.length() > 150) {
          mess=mess.substring(0,150);
        }
        mess=mess.replace('\n',' ');
        pinnedMessageTextView.setText(Emoji.replaceEmoji(mess,pinnedMessageTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
      }
    }
 else {
      pinnedImageLocation=null;
      pinnedImageLocationObject=null;
      hidePinnedMessageView(animated);
      if (loadingPinnedMessage != pinned_msg_id) {
        loadingPinnedMessage=pinned_msg_id;
        DataQuery.getInstance(currentAccount).loadPinnedMessage(dialog_id,ChatObject.isChannel(currentChat) ? currentChat.id : 0,pinned_msg_id,true);
      }
    }
  }
  checkListViewPaddings();
}
