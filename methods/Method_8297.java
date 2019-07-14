private void applyDraftMaybe(boolean canClear){
  if (chatActivityEnterView == null) {
    return;
  }
  TLRPC.DraftMessage draftMessage=DataQuery.getInstance(currentAccount).getDraft(dialog_id);
  TLRPC.Message draftReplyMessage=draftMessage != null && draftMessage.reply_to_msg_id != 0 ? DataQuery.getInstance(currentAccount).getDraftMessage(dialog_id) : null;
  if (chatActivityEnterView.getFieldText() == null) {
    if (draftMessage != null) {
      chatActivityEnterView.setWebPage(null,!draftMessage.no_webpage);
      CharSequence message;
      if (!draftMessage.entities.isEmpty()) {
        SpannableStringBuilder stringBuilder=SpannableStringBuilder.valueOf(draftMessage.message);
        DataQuery.sortEntities(draftMessage.entities);
        int addToOffset=0;
        for (int a=0; a < draftMessage.entities.size(); a++) {
          TLRPC.MessageEntity entity=draftMessage.entities.get(a);
          if (entity instanceof TLRPC.TL_inputMessageEntityMentionName || entity instanceof TLRPC.TL_messageEntityMentionName) {
            int user_id;
            if (entity instanceof TLRPC.TL_inputMessageEntityMentionName) {
              user_id=((TLRPC.TL_inputMessageEntityMentionName)entity).user_id.user_id;
            }
 else {
              user_id=((TLRPC.TL_messageEntityMentionName)entity).user_id;
            }
            if (entity.offset + addToOffset + entity.length < stringBuilder.length() && stringBuilder.charAt(entity.offset + addToOffset + entity.length) == ' ') {
              entity.length++;
            }
            stringBuilder.setSpan(new URLSpanUserMention("" + user_id,1),entity.offset + addToOffset,entity.offset + addToOffset + entity.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
 else           if (entity instanceof TLRPC.TL_messageEntityCode) {
            stringBuilder.insert(entity.offset + entity.length + addToOffset,"`");
            stringBuilder.insert(entity.offset + addToOffset,"`");
            addToOffset+=2;
          }
 else           if (entity instanceof TLRPC.TL_messageEntityPre) {
            stringBuilder.insert(entity.offset + entity.length + addToOffset,"```");
            stringBuilder.insert(entity.offset + addToOffset,"```");
            addToOffset+=6;
          }
 else           if (entity instanceof TLRPC.TL_messageEntityBold) {
            stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/rmedium.ttf")),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
 else           if (entity instanceof TLRPC.TL_messageEntityItalic) {
            stringBuilder.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface("fonts/ritalic.ttf")),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
 else           if (entity instanceof TLRPC.TL_messageEntityTextUrl) {
            stringBuilder.setSpan(new URLSpanReplacement(entity.url),entity.offset + addToOffset,entity.offset + entity.length + addToOffset,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
        message=stringBuilder;
      }
 else {
        message=draftMessage.message;
      }
      chatActivityEnterView.setFieldText(message);
      if (getArguments().getBoolean("hasUrl",false)) {
        chatActivityEnterView.setSelection(draftMessage.message.indexOf('\n') + 1);
        AndroidUtilities.runOnUIThread(() -> {
          if (chatActivityEnterView != null) {
            chatActivityEnterView.setFieldFocused(true);
            chatActivityEnterView.openKeyboard();
          }
        }
,700);
      }
    }
  }
 else   if (canClear && draftMessage == null) {
    chatActivityEnterView.setFieldText("");
    hideFieldPanel(true);
  }
  if (replyingMessageObject == null && draftReplyMessage != null) {
    replyingMessageObject=new MessageObject(currentAccount,draftReplyMessage,MessagesController.getInstance(currentAccount).getUsers(),false);
    showFieldPanelForReply(replyingMessageObject);
  }
}
