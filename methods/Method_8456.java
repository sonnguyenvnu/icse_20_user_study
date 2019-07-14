public void setEditingMessageObject(MessageObject messageObject,boolean caption){
  if (audioToSend != null || videoToSendMessageObject != null || editingMessageObject == messageObject) {
    return;
  }
  if (editingMessageReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(editingMessageReqId,true);
    editingMessageReqId=0;
  }
  editingMessageObject=messageObject;
  editingCaption=caption;
  if (editingMessageObject != null) {
    if (doneButtonAnimation != null) {
      doneButtonAnimation.cancel();
      doneButtonAnimation=null;
    }
    doneButtonContainer.setVisibility(View.VISIBLE);
    showEditDoneProgress(true,false);
    InputFilter[] inputFilters=new InputFilter[1];
    CharSequence editingText;
    if (caption) {
      inputFilters[0]=new InputFilter.LengthFilter(MessagesController.getInstance(currentAccount).maxCaptionLength);
      editingText=editingMessageObject.caption;
    }
 else {
      inputFilters[0]=new InputFilter.LengthFilter(MessagesController.getInstance(currentAccount).maxMessageLength);
      editingText=editingMessageObject.messageText;
    }
    if (editingText != null) {
      ArrayList<TLRPC.MessageEntity> entities=editingMessageObject.messageOwner.entities;
      DataQuery.sortEntities(entities);
      SpannableStringBuilder stringBuilder=new SpannableStringBuilder(editingText);
      Object[] spansToRemove=stringBuilder.getSpans(0,stringBuilder.length(),Object.class);
      if (spansToRemove != null && spansToRemove.length > 0) {
        for (int a=0; a < spansToRemove.length; a++) {
          stringBuilder.removeSpan(spansToRemove[a]);
        }
      }
      if (entities != null) {
        int addToOffset=0;
        try {
          for (int a=0; a < entities.size(); a++) {
            TLRPC.MessageEntity entity=entities.get(a);
            if (entity.offset + entity.length + addToOffset > stringBuilder.length()) {
              continue;
            }
            if (entity instanceof TLRPC.TL_inputMessageEntityMentionName) {
              if (entity.offset + entity.length + addToOffset < stringBuilder.length() && stringBuilder.charAt(entity.offset + entity.length + addToOffset) == ' ') {
                entity.length++;
              }
              stringBuilder.setSpan(new URLSpanUserMention("" + ((TLRPC.TL_inputMessageEntityMentionName)entity).user_id.user_id,1),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
 else             if (entity instanceof TLRPC.TL_messageEntityMentionName) {
              if (entity.offset + entity.length + addToOffset < stringBuilder.length() && stringBuilder.charAt(entity.offset + entity.length + addToOffset) == ' ') {
                entity.length++;
              }
              stringBuilder.setSpan(new URLSpanUserMention("" + ((TLRPC.TL_messageEntityMentionName)entity).user_id,1),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
 else             if (entity instanceof TLRPC.TL_messageEntityCode) {
              stringBuilder.insert(entity.offset + entity.length + addToOffset,"`");
              stringBuilder.insert(entity.offset + addToOffset,"`");
              addToOffset+=2;
            }
 else             if (entity instanceof TLRPC.TL_messageEntityPre) {
              stringBuilder.insert(entity.offset + entity.length + addToOffset,"```");
              stringBuilder.insert(entity.offset + addToOffset,"```");
              addToOffset+=6;
            }
 else             if (entity instanceof TLRPC.TL_messageEntityBold) {
              stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
 else             if (entity instanceof TLRPC.TL_messageEntityItalic) {
              stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
 else             if (entity instanceof TLRPC.TL_messageEntityTextUrl) {
              stringBuilder.setSpan(new URLSpanReplacement(entity.url),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      setFieldText(Emoji.replaceEmoji(new SpannableStringBuilder(stringBuilder),messageEditText.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false));
    }
 else {
      setFieldText("");
    }
    messageEditText.setFilters(inputFilters);
    openKeyboard();
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)messageEditText.getLayoutParams();
    layoutParams.rightMargin=AndroidUtilities.dp(4);
    messageEditText.setLayoutParams(layoutParams);
    sendButton.setVisibility(GONE);
    cancelBotButton.setVisibility(GONE);
    audioVideoButtonContainer.setVisibility(GONE);
    attachLayout.setVisibility(GONE);
    sendButtonContainer.setVisibility(GONE);
  }
 else {
    doneButtonContainer.setVisibility(View.GONE);
    messageEditText.setFilters(new InputFilter[0]);
    delegate.onMessageEditEnd(false);
    audioVideoButtonContainer.setVisibility(VISIBLE);
    attachLayout.setVisibility(VISIBLE);
    sendButtonContainer.setVisibility(VISIBLE);
    attachLayout.setScaleX(1.0f);
    attachLayout.setAlpha(1.0f);
    sendButton.setScaleX(0.1f);
    sendButton.setScaleY(0.1f);
    sendButton.setAlpha(0.0f);
    cancelBotButton.setScaleX(0.1f);
    cancelBotButton.setScaleY(0.1f);
    cancelBotButton.setAlpha(0.0f);
    audioVideoButtonContainer.setScaleX(1.0f);
    audioVideoButtonContainer.setScaleY(1.0f);
    audioVideoButtonContainer.setAlpha(1.0f);
    sendButton.setVisibility(GONE);
    cancelBotButton.setVisibility(GONE);
    messageEditText.setText("");
    if (getVisibility() == VISIBLE) {
      delegate.onAttachButtonShow();
    }
    updateFieldRight(1);
  }
  updateFieldHint();
}
