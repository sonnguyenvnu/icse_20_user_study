private void createMenu(View v,boolean single,boolean listView,float x,float y,boolean searchGroup){
  if (actionBar.isActionModeShowed()) {
    return;
  }
  MessageObject message=null;
  if (v instanceof ChatMessageCell) {
    message=((ChatMessageCell)v).getMessageObject();
  }
 else   if (v instanceof ChatActionCell) {
    message=((ChatActionCell)v).getMessageObject();
  }
  if (message == null) {
    return;
  }
  final int type=getMessageType(message);
  if (single) {
    if (message.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage) {
      scrollToMessageId(message.messageOwner.reply_to_msg_id,message.messageOwner.id,true,0,false);
      return;
    }
  }
  selectedObject=null;
  selectedObjectGroup=null;
  forwardingMessage=null;
  forwardingMessageGroup=null;
  for (int a=1; a >= 0; a--) {
    selectedMessagesCanCopyIds[a].clear();
    selectedMessagesCanStarIds[a].clear();
    selectedMessagesIds[a].clear();
  }
  hideActionMode();
  updatePinnedMessageView(true);
  MessageObject.GroupedMessages groupedMessages;
  if (searchGroup) {
    groupedMessages=getValidGroupedMessage(message);
  }
 else {
    groupedMessages=null;
  }
  boolean allowChatActions=true;
  boolean allowPin;
  if (currentChat != null) {
    allowPin=message.getDialogId() != mergeDialogId && ChatObject.canPinMessages(currentChat);
  }
 else   if (currentEncryptedChat == null) {
    if (userInfo != null) {
      allowPin=userInfo.can_pin_message;
    }
 else {
      allowPin=false;
    }
  }
 else {
    allowPin=false;
  }
  allowPin=allowPin && message.getId() > 0 && (message.messageOwner.action == null || message.messageOwner.action instanceof TLRPC.TL_messageActionEmpty);
  boolean allowUnpin=message.getDialogId() != mergeDialogId && allowPin && (chatInfo != null && chatInfo.pinned_msg_id == message.getId() || userInfo != null && userInfo.pinned_msg_id == message.getId());
  boolean allowEdit=groupedMessages == null && message.canEditMessage(currentChat) && !chatActivityEnterView.hasAudioToSend() && message.getDialogId() != mergeDialogId;
  if (currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) < 46 || type == 1 && (message.getDialogId() == mergeDialogId || message.needDrawBluredPreview()) || message.messageOwner.action instanceof TLRPC.TL_messageActionSecureValuesSent || currentEncryptedChat == null && message.getId() < 0 || bottomOverlayChat != null && bottomOverlayChat.getVisibility() == View.VISIBLE || isBroadcast || currentChat != null && (ChatObject.isNotInChat(currentChat) || ChatObject.isChannel(currentChat) && !ChatObject.canPost(currentChat) && !currentChat.megagroup || !ChatObject.canSendMessages(currentChat))) {
    allowChatActions=false;
  }
  if (single || type < 2 || type == 20) {
    if (getParentActivity() == null) {
      return;
    }
    ArrayList<Integer> icons=new ArrayList<>();
    ArrayList<CharSequence> items=new ArrayList<>();
    final ArrayList<Integer> options=new ArrayList<>();
    if (type >= 0 || type == -1 && single && (message.isSending() || message.isEditing()) && currentEncryptedChat == null) {
      selectedObject=message;
      selectedObjectGroup=groupedMessages;
      if (type == -1) {
        if (selectedObject.type == 0 || selectedObject.caption != null) {
          items.add(LocaleController.getString("Copy",R.string.Copy));
          options.add(3);
          icons.add(R.drawable.msg_copy);
        }
        items.add(LocaleController.getString("CancelSending",R.string.CancelSending));
        options.add(24);
        icons.add(R.drawable.msg_delete);
      }
 else       if (type == 0) {
        items.add(LocaleController.getString("Retry",R.string.Retry));
        options.add(0);
        icons.add(R.drawable.msg_retry);
        items.add(LocaleController.getString("Delete",R.string.Delete));
        options.add(1);
        icons.add(R.drawable.msg_delete);
      }
 else       if (type == 1) {
        if (currentChat != null && !isBroadcast) {
          if (allowChatActions) {
            items.add(LocaleController.getString("Reply",R.string.Reply));
            options.add(8);
            icons.add(R.drawable.msg_reply);
          }
          if (allowUnpin) {
            items.add(LocaleController.getString("UnpinMessage",R.string.UnpinMessage));
            options.add(14);
            icons.add(R.drawable.msg_unpin);
          }
 else           if (allowPin) {
            items.add(LocaleController.getString("PinMessage",R.string.PinMessage));
            options.add(13);
            icons.add(R.drawable.msg_pin);
          }
          if (allowEdit) {
            items.add(LocaleController.getString("Edit",R.string.Edit));
            options.add(12);
            icons.add(R.drawable.msg_edit);
          }
          if (selectedObject.contentType == 0 && !selectedObject.isMediaEmptyWebpage() && selectedObject.getId() > 0 && !selectedObject.isOut() && (currentChat != null || currentUser != null && currentUser.bot)) {
            items.add(LocaleController.getString("ReportChat",R.string.ReportChat));
            options.add(23);
            icons.add(R.drawable.msg_report);
          }
          if (message.canDeleteMessage(currentChat)) {
            items.add(LocaleController.getString("Delete",R.string.Delete));
            options.add(1);
            icons.add(R.drawable.msg_delete);
          }
        }
 else {
          if (selectedObject.getId() > 0 && allowChatActions) {
            items.add(LocaleController.getString("Reply",R.string.Reply));
            options.add(8);
            icons.add(R.drawable.msg_reply);
          }
          if (message.canDeleteMessage(currentChat)) {
            items.add(LocaleController.getString("Delete",R.string.Delete));
            options.add(1);
            icons.add(R.drawable.msg_delete);
          }
        }
      }
 else       if (type == 20) {
        items.add(LocaleController.getString("Retry",R.string.Retry));
        options.add(0);
        icons.add(R.drawable.msg_retry);
        items.add(LocaleController.getString("Copy",R.string.Copy));
        options.add(3);
        icons.add(R.drawable.msg_copy);
        items.add(LocaleController.getString("Delete",R.string.Delete));
        options.add(1);
        icons.add(R.drawable.msg_delete);
      }
 else {
        if (currentEncryptedChat == null) {
          if (selectedObject.messageOwner.action instanceof TLRPC.TL_messageActionPhoneCall) {
            TLRPC.TL_messageActionPhoneCall call=(TLRPC.TL_messageActionPhoneCall)message.messageOwner.action;
            items.add((call.reason instanceof TLRPC.TL_phoneCallDiscardReasonMissed || call.reason instanceof TLRPC.TL_phoneCallDiscardReasonBusy) && !message.isOutOwner() ? LocaleController.getString("CallBack",R.string.CallBack) : LocaleController.getString("CallAgain",R.string.CallAgain));
            options.add(18);
            icons.add(R.drawable.msg_callback);
            if (VoIPHelper.canRateCall(call)) {
              items.add(LocaleController.getString("CallMessageReportProblem",R.string.CallMessageReportProblem));
              options.add(19);
              icons.add(R.drawable.msg_fave);
            }
          }
          if (allowChatActions) {
            items.add(LocaleController.getString("Reply",R.string.Reply));
            options.add(8);
            icons.add(R.drawable.msg_reply);
          }
          if (selectedObject.type == 0 || selectedObject.caption != null) {
            items.add(LocaleController.getString("Copy",R.string.Copy));
            options.add(3);
            icons.add(R.drawable.msg_copy);
          }
          if (ChatObject.isChannel(currentChat) && currentChat.megagroup) {
            items.add(LocaleController.getString("CopyLink",R.string.CopyLink));
            options.add(22);
            icons.add(R.drawable.msg_link);
          }
          if (type == 2) {
            if (selectedObject.type == MessageObject.TYPE_POLL && !message.isPollClosed()) {
              if (message.isVoted()) {
                items.add(LocaleController.getString("Unvote",R.string.Unvote));
                options.add(25);
                icons.add(R.drawable.msg_unvote);
              }
              if (!message.isForwarded() && (message.isOut() && (!ChatObject.isChannel(currentChat) || currentChat.megagroup) || ChatObject.isChannel(currentChat) && !currentChat.megagroup && (currentChat.creator || currentChat.admin_rights != null && currentChat.admin_rights.edit_messages))) {
                items.add(LocaleController.getString("StopPoll",R.string.StopPoll));
                options.add(26);
                icons.add(R.drawable.msg_pollstop);
              }
            }
          }
 else           if (type == 3) {
            if (selectedObject.messageOwner.media instanceof TLRPC.TL_messageMediaWebPage && MessageObject.isNewGifDocument(selectedObject.messageOwner.media.webpage.document)) {
              items.add(LocaleController.getString("SaveToGIFs",R.string.SaveToGIFs));
              options.add(11);
              icons.add(R.drawable.msg_gif);
            }
          }
 else           if (type == 4) {
            if (selectedObject.isVideo()) {
              if (!selectedObject.needDrawBluredPreview()) {
                items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
                options.add(4);
                icons.add(R.drawable.msg_gallery);
                items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
                options.add(6);
                icons.add(R.drawable.msg_shareout);
              }
            }
 else             if (selectedObject.isMusic()) {
              items.add(LocaleController.getString("SaveToMusic",R.string.SaveToMusic));
              options.add(10);
              icons.add(R.drawable.msg_download);
              items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
              options.add(6);
              icons.add(R.drawable.msg_shareout);
            }
 else             if (selectedObject.getDocument() != null) {
              if (MessageObject.isNewGifDocument(selectedObject.getDocument())) {
                items.add(LocaleController.getString("SaveToGIFs",R.string.SaveToGIFs));
                options.add(11);
                icons.add(R.drawable.msg_gif);
              }
              items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
              options.add(10);
              icons.add(R.drawable.msg_download);
              items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
              options.add(6);
              icons.add(R.drawable.msg_shareout);
            }
 else {
              if (!selectedObject.needDrawBluredPreview()) {
                items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
                options.add(4);
                icons.add(R.drawable.msg_gallery);
              }
            }
          }
 else           if (type == 5) {
            items.add(LocaleController.getString("ApplyLocalizationFile",R.string.ApplyLocalizationFile));
            options.add(5);
            icons.add(R.drawable.msg_language);
            items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
            options.add(10);
            icons.add(R.drawable.msg_download);
            items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
            options.add(6);
            icons.add(R.drawable.msg_shareout);
          }
 else           if (type == 10) {
            items.add(LocaleController.getString("ApplyThemeFile",R.string.ApplyThemeFile));
            options.add(5);
            icons.add(R.drawable.msg_theme);
            items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
            options.add(10);
            icons.add(R.drawable.msg_download);
            items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
            options.add(6);
            icons.add(R.drawable.msg_shareout);
          }
 else           if (type == 6) {
            items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
            options.add(7);
            icons.add(R.drawable.msg_gallery);
            items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
            options.add(10);
            icons.add(R.drawable.msg_download);
            items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
            options.add(6);
            icons.add(R.drawable.msg_shareout);
          }
 else           if (type == 7) {
            if (selectedObject.isMask()) {
              items.add(LocaleController.getString("AddToMasks",R.string.AddToMasks));
              options.add(9);
              icons.add(R.drawable.msg_sticker);
            }
 else {
              items.add(LocaleController.getString("AddToStickers",R.string.AddToStickers));
              options.add(9);
              icons.add(R.drawable.msg_sticker);
              if (!DataQuery.getInstance(currentAccount).isStickerInFavorites(selectedObject.getDocument())) {
                if (DataQuery.getInstance(currentAccount).canAddStickerToFavorites()) {
                  items.add(LocaleController.getString("AddToFavorites",R.string.AddToFavorites));
                  options.add(20);
                  icons.add(R.drawable.msg_fave);
                }
              }
 else {
                items.add(LocaleController.getString("DeleteFromFavorites",R.string.DeleteFromFavorites));
                options.add(21);
                icons.add(R.drawable.msg_unfave);
              }
            }
          }
 else           if (type == 8) {
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(selectedObject.messageOwner.media.user_id);
            if (user != null && user.id != UserConfig.getInstance(currentAccount).getClientUserId() && ContactsController.getInstance(currentAccount).contactsDict.get(user.id) == null) {
              items.add(LocaleController.getString("AddContactTitle",R.string.AddContactTitle));
              options.add(15);
              icons.add(R.drawable.msg_addcontact);
            }
            if (!TextUtils.isEmpty(selectedObject.messageOwner.media.phone_number)) {
              items.add(LocaleController.getString("Copy",R.string.Copy));
              options.add(16);
              icons.add(R.drawable.msg_copy);
              items.add(LocaleController.getString("Call",R.string.Call));
              options.add(17);
              icons.add(R.drawable.msg_callback);
            }
          }
 else           if (type == 9) {
            if (!DataQuery.getInstance(currentAccount).isStickerInFavorites(selectedObject.getDocument())) {
              items.add(LocaleController.getString("AddToFavorites",R.string.AddToFavorites));
              options.add(20);
              icons.add(R.drawable.msg_fave);
            }
 else {
              items.add(LocaleController.getString("DeleteFromFavorites",R.string.DeleteFromFavorites));
              options.add(21);
              icons.add(R.drawable.msg_unfave);
            }
          }
          if (!selectedObject.needDrawBluredPreview() && !selectedObject.isLiveLocation() && selectedObject.type != 16) {
            items.add(LocaleController.getString("Forward",R.string.Forward));
            options.add(2);
            icons.add(R.drawable.msg_forward);
          }
          if (allowUnpin) {
            items.add(LocaleController.getString("UnpinMessage",R.string.UnpinMessage));
            options.add(14);
            icons.add(R.drawable.msg_unpin);
          }
 else           if (allowPin) {
            items.add(LocaleController.getString("PinMessage",R.string.PinMessage));
            options.add(13);
            icons.add(R.drawable.msg_pin);
          }
          if (allowEdit) {
            items.add(LocaleController.getString("Edit",R.string.Edit));
            options.add(12);
            icons.add(R.drawable.msg_edit);
          }
          if (selectedObject.contentType == 0 && selectedObject.getId() > 0 && !selectedObject.isOut() && (currentChat != null || currentUser != null && currentUser.bot)) {
            items.add(LocaleController.getString("ReportChat",R.string.ReportChat));
            options.add(23);
            icons.add(R.drawable.msg_report);
          }
          if (message.canDeleteMessage(currentChat)) {
            items.add(LocaleController.getString("Delete",R.string.Delete));
            options.add(1);
            icons.add(R.drawable.msg_delete);
          }
        }
 else {
          if (allowChatActions) {
            items.add(LocaleController.getString("Reply",R.string.Reply));
            options.add(8);
            icons.add(R.drawable.msg_reply);
          }
          if (selectedObject.type == 0 || selectedObject.caption != null) {
            items.add(LocaleController.getString("Copy",R.string.Copy));
            options.add(3);
            icons.add(R.drawable.msg_copy);
          }
          if (type == 4) {
            if (selectedObject.isVideo()) {
              items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
              options.add(4);
              icons.add(R.drawable.msg_gallery);
              items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
              options.add(6);
              icons.add(R.drawable.msg_shareout);
            }
 else             if (selectedObject.isMusic()) {
              items.add(LocaleController.getString("SaveToMusic",R.string.SaveToMusic));
              options.add(10);
              icons.add(R.drawable.msg_download);
              items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
              options.add(6);
              icons.add(R.drawable.msg_shareout);
            }
 else             if (!selectedObject.isVideo() && selectedObject.getDocument() != null) {
              items.add(LocaleController.getString("SaveToDownloads",R.string.SaveToDownloads));
              options.add(10);
              icons.add(R.drawable.msg_download);
              items.add(LocaleController.getString("ShareFile",R.string.ShareFile));
              options.add(6);
              icons.add(R.drawable.msg_shareout);
            }
 else {
              items.add(LocaleController.getString("SaveToGallery",R.string.SaveToGallery));
              options.add(4);
              icons.add(R.drawable.msg_gallery);
            }
          }
 else           if (type == 5) {
            items.add(LocaleController.getString("ApplyLocalizationFile",R.string.ApplyLocalizationFile));
            options.add(5);
            icons.add(R.drawable.msg_language);
          }
 else           if (type == 10) {
            items.add(LocaleController.getString("ApplyThemeFile",R.string.ApplyThemeFile));
            options.add(5);
            icons.add(R.drawable.msg_theme);
          }
 else           if (type == 7) {
            items.add(LocaleController.getString("AddToStickers",R.string.AddToStickers));
            options.add(9);
            icons.add(R.drawable.msg_sticker);
          }
 else           if (type == 8) {
            TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(selectedObject.messageOwner.media.user_id);
            if (user != null && user.id != UserConfig.getInstance(currentAccount).getClientUserId() && ContactsController.getInstance(currentAccount).contactsDict.get(user.id) == null) {
              items.add(LocaleController.getString("AddContactTitle",R.string.AddContactTitle));
              options.add(15);
              icons.add(R.drawable.msg_addcontact);
            }
            if (!TextUtils.isEmpty(selectedObject.messageOwner.media.phone_number)) {
              items.add(LocaleController.getString("Copy",R.string.Copy));
              options.add(16);
              icons.add(R.drawable.msg_copy);
              items.add(LocaleController.getString("Call",R.string.Call));
              options.add(17);
              icons.add(R.drawable.msg_callback);
            }
          }
          items.add(LocaleController.getString("Delete",R.string.Delete));
          options.add(1);
          icons.add(R.drawable.msg_delete);
        }
      }
    }
    if (options.isEmpty()) {
      return;
    }
    if (scrimPopupWindow != null) {
      scrimPopupWindow.dismiss();
      scrimPopupWindow=null;
      return;
    }
    Rect rect=new Rect();
    ActionBarPopupWindow.ActionBarPopupWindowLayout popupLayout=new ActionBarPopupWindow.ActionBarPopupWindowLayout(getParentActivity());
    popupLayout.setOnTouchListener((view,event) -> {
      if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
        if (scrimPopupWindow != null && scrimPopupWindow.isShowing()) {
          v.getHitRect(rect);
          if (!rect.contains((int)event.getX(),(int)event.getY())) {
            scrimPopupWindow.dismiss();
          }
        }
      }
 else       if (event.getActionMasked() == MotionEvent.ACTION_OUTSIDE) {
        if (scrimPopupWindow != null && scrimPopupWindow.isShowing()) {
          scrimPopupWindow.dismiss();
        }
      }
      return false;
    }
);
    popupLayout.setDispatchKeyEventListener(keyEvent -> {
      if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0 && scrimPopupWindow != null && scrimPopupWindow.isShowing()) {
        scrimPopupWindow.dismiss();
      }
    }
);
    Rect backgroundPaddings=new Rect();
    Drawable shadowDrawable=getParentActivity().getResources().getDrawable(R.drawable.popup_fixed_alert).mutate();
    shadowDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground),PorterDuff.Mode.MULTIPLY));
    shadowDrawable.getPadding(backgroundPaddings);
    popupLayout.setBackgroundDrawable(shadowDrawable);
    LinearLayout linearLayout=new LinearLayout(getParentActivity());
    ScrollView scrollView;
    if (Build.VERSION.SDK_INT >= 21) {
      scrollView=new ScrollView(getParentActivity(),null,0,R.style.scrollbarShapeStyle){
        @Override protected void onMeasure(        int widthMeasureSpec,        int heightMeasureSpec){
          super.onMeasure(widthMeasureSpec,heightMeasureSpec);
          setMeasuredDimension(linearLayout.getMeasuredWidth(),getMeasuredHeight());
        }
      }
;
    }
 else {
      scrollView=new ScrollView(getParentActivity());
    }
    scrollView.setClipToPadding(false);
    popupLayout.addView(scrollView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT));
    linearLayout.setMinimumWidth(AndroidUtilities.dp(200));
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    for (int a=0, N=items.size(); a < N; a++) {
      ActionBarMenuSubItem cell=new ActionBarMenuSubItem(getParentActivity());
      cell.setTextAndIcon(items.get(a),icons.get(a));
      linearLayout.addView(cell);
      final int i=a;
      cell.setOnClickListener(v1 -> {
        if (selectedObject == null || i < 0 || i >= options.size()) {
          return;
        }
        processSelectedOption(options.get(i));
        if (scrimPopupWindow != null) {
          scrimPopupWindow.dismiss();
        }
      }
);
    }
    scrollView.addView(linearLayout,LayoutHelper.createScroll(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP));
    scrimPopupWindow=new ActionBarPopupWindow(popupLayout,LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT){
      @Override public void dismiss(){
        super.dismiss();
        if (scrimPopupWindow != this) {
          return;
        }
        scrimPopupWindow=null;
        if (scrimAnimatorSet != null) {
          scrimAnimatorSet.cancel();
          scrimAnimatorSet=null;
        }
        if (scrimView instanceof ChatMessageCell) {
          ChatMessageCell cell=(ChatMessageCell)scrimView;
          cell.setInvalidatesParent(false);
        }
        chatLayoutManager.setCanScrollVertically(true);
        scrimAnimatorSet=new AnimatorSet();
        ArrayList<Animator> animators=new ArrayList<>();
        animators.add(ObjectAnimator.ofInt(scrimPaint,AnimationProperties.PAINT_ALPHA,0));
        if (pagedownButton.getTag() != null) {
          animators.add(ObjectAnimator.ofFloat(pagedownButton,View.ALPHA,1.0f));
        }
        if (mentiondownButton.getTag() != null) {
          animators.add(ObjectAnimator.ofFloat(mentiondownButton,View.ALPHA,1.0f));
        }
        scrimAnimatorSet.playTogether(animators);
        scrimAnimatorSet.setDuration(220);
        scrimAnimatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            scrimView=null;
            contentView.invalidate();
            chatListView.invalidate();
          }
        }
);
        scrimAnimatorSet.start();
        if (chatActivityEnterView != null) {
          chatActivityEnterView.getEditField().setAllowDrawCursor(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          getParentActivity().getWindow().getDecorView().setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
        }
      }
    }
;
    scrimPopupWindow.setDismissAnimationDuration(220);
    scrimPopupWindow.setOutsideTouchable(true);
    scrimPopupWindow.setClippingEnabled(true);
    scrimPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
    scrimPopupWindow.setFocusable(true);
    popupLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),View.MeasureSpec.AT_MOST),View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000),View.MeasureSpec.AT_MOST));
    scrimPopupWindow.setInputMethodMode(ActionBarPopupWindow.INPUT_METHOD_NOT_NEEDED);
    scrimPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
    scrimPopupWindow.getContentView().setFocusableInTouchMode(true);
    int popupX=v.getLeft() + (int)x - popupLayout.getMeasuredWidth() + backgroundPaddings.left - AndroidUtilities.dp(28);
    if (popupX < AndroidUtilities.dp(6)) {
      popupX=AndroidUtilities.dp(6);
    }
 else     if (popupX > chatListView.getMeasuredWidth() - AndroidUtilities.dp(6) - popupLayout.getMeasuredWidth()) {
      popupX=chatListView.getMeasuredWidth() - AndroidUtilities.dp(6) - popupLayout.getMeasuredWidth();
    }
    if (AndroidUtilities.isTablet()) {
      int[] location=new int[2];
      fragmentView.getLocationInWindow(location);
      popupX+=location[0];
    }
    int totalHeight=contentView.getHeight();
    int height=popupLayout.getMeasuredHeight();
    int keyboardHeight=contentView.getKeyboardHeight();
    if (keyboardHeight > AndroidUtilities.dp(20)) {
      totalHeight+=keyboardHeight;
    }
    int popupY;
    if (height < totalHeight) {
      popupY=(int)(chatListView.getY() + v.getTop() + y);
      if (height - backgroundPaddings.top - backgroundPaddings.bottom > AndroidUtilities.dp(240)) {
        popupY+=AndroidUtilities.dp(240) - height;
      }
      if (popupY < chatListView.getY() + AndroidUtilities.dp(24)) {
        popupY=(int)(chatListView.getY() + AndroidUtilities.dp(24));
      }
 else       if (popupY > totalHeight - height - AndroidUtilities.dp(8)) {
        popupY=totalHeight - height - AndroidUtilities.dp(8);
      }
    }
 else {
      popupY=AndroidUtilities.statusBarHeight;
    }
    scrimPopupWindow.showAtLocation(chatListView,Gravity.LEFT | Gravity.TOP,popupX,popupY);
    chatListView.stopScroll();
    chatLayoutManager.setCanScrollVertically(false);
    scrimView=v;
    if (scrimView instanceof ChatMessageCell) {
      ChatMessageCell cell=(ChatMessageCell)scrimView;
      cell.setInvalidatesParent(true);
    }
    contentView.invalidate();
    chatListView.invalidate();
    if (scrimAnimatorSet != null) {
      scrimAnimatorSet.cancel();
    }
    scrimAnimatorSet=new AnimatorSet();
    ArrayList<Animator> animators=new ArrayList<>();
    animators.add(ObjectAnimator.ofInt(scrimPaint,AnimationProperties.PAINT_ALPHA,0,50));
    if (pagedownButton.getTag() != null) {
      animators.add(ObjectAnimator.ofFloat(pagedownButton,View.ALPHA,0));
    }
    if (mentiondownButton.getTag() != null) {
      animators.add(ObjectAnimator.ofFloat(mentiondownButton,View.ALPHA,0));
    }
    scrimAnimatorSet.playTogether(animators);
    scrimAnimatorSet.setDuration(150);
    scrimAnimatorSet.start();
    if (forwardHintView != null) {
      forwardHintView.hide();
    }
    if (noSoundHintView != null) {
      noSoundHintView.hide();
    }
    if (chatActivityEnterView != null) {
      chatActivityEnterView.getEditField().setAllowDrawCursor(false);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      getParentActivity().getWindow().getDecorView().setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
    }
    return;
  }
  if (chatActivityEnterView != null && (chatActivityEnterView.isRecordingAudioVideo() || chatActivityEnterView.isRecordLocked())) {
    return;
  }
  final ActionBarMenu actionMode=actionBar.createActionMode();
  View item=actionMode.getItem(delete);
  if (item != null) {
    item.setVisibility(View.VISIBLE);
  }
  bottomMessagesActionContainer.setVisibility(View.VISIBLE);
  int translationY=chatActivityEnterView.getMeasuredHeight() - AndroidUtilities.dp(51);
  if (chatActivityEnterView.getVisibility() == View.VISIBLE) {
    ArrayList<View> views=new ArrayList<>();
    views.add(chatActivityEnterView);
    if (mentionContainer != null && mentionContainer.getVisibility() == View.VISIBLE) {
      views.add(mentionContainer);
    }
    if (stickersPanel != null && stickersPanel.getVisibility() == View.VISIBLE) {
      views.add(stickersPanel);
    }
    actionBar.showActionMode(bottomMessagesActionContainer,null,views.toArray(new View[0]),new boolean[]{false,true,true},chatListView,translationY);
    if (getParentActivity() != null) {
      ((LaunchActivity)getParentActivity()).hideVisibleActionMode();
    }
    chatActivityEnterView.getEditField().setAllowDrawCursor(false);
  }
 else   if (bottomOverlayChat.getVisibility() == View.VISIBLE) {
    actionBar.showActionMode(bottomMessagesActionContainer,null,new View[]{bottomOverlayChat},new boolean[]{true},chatListView,translationY);
  }
 else   if (searchContainer.getVisibility() == View.VISIBLE) {
    actionBar.showActionMode(bottomMessagesActionContainer,null,new View[]{searchContainer},new boolean[]{true},chatListView,translationY);
  }
 else {
    actionBar.showActionMode(bottomMessagesActionContainer,null,null,null,chatListView,translationY);
  }
  if (scrimPopupWindow != null) {
    scrimPopupWindow.dismiss();
  }
  chatLayoutManager.setCanScrollVertically(true);
  updatePinnedMessageView(true);
  AnimatorSet animatorSet=new AnimatorSet();
  ArrayList<Animator> animators=new ArrayList<>();
  for (int a=0; a < actionModeViews.size(); a++) {
    View view=actionModeViews.get(a);
    view.setPivotY(ActionBar.getCurrentActionBarHeight() / 2);
    AndroidUtilities.clearDrawableAnimation(view);
    animators.add(ObjectAnimator.ofFloat(view,View.SCALE_Y,0.1f,1.0f));
  }
  animatorSet.playTogether(animators);
  animatorSet.setDuration(250);
  animatorSet.start();
  addToSelectedMessages(message,listView);
  selectedMessagesCountTextView.setNumber(selectedMessagesIds[0].size() + selectedMessagesIds[1].size(),false);
  updateVisibleRows();
}
