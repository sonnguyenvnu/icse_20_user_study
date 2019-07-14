public void drawNamesLayout(Canvas canvas){
  if (drawNameLayout && nameLayout != null) {
    canvas.save();
    if (currentMessageObject.shouldDrawWithoutBackground()) {
      Theme.chat_namePaint.setColor(Theme.getColor(Theme.key_chat_stickerNameText));
      int backWidth;
      if (currentMessageObject.isOutOwner()) {
        nameX=AndroidUtilities.dp(28);
      }
 else {
        nameX=backgroundDrawableLeft + backgroundDrawableRight + AndroidUtilities.dp(22);
      }
      nameY=layoutHeight - AndroidUtilities.dp(38);
      Theme.chat_systemDrawable.setColorFilter(Theme.colorFilter);
      Theme.chat_systemDrawable.setBounds((int)nameX - AndroidUtilities.dp(12),(int)nameY - AndroidUtilities.dp(5),(int)nameX + AndroidUtilities.dp(12) + nameWidth,(int)nameY + AndroidUtilities.dp(22));
      Theme.chat_systemDrawable.draw(canvas);
    }
 else {
      if (mediaBackground || currentMessageObject.isOutOwner()) {
        nameX=backgroundDrawableLeft + AndroidUtilities.dp(11) - nameOffsetX;
      }
 else {
        nameX=backgroundDrawableLeft + AndroidUtilities.dp(!mediaBackground && drawPinnedBottom ? 11 : 17) - nameOffsetX;
      }
      if (currentUser != null) {
        Theme.chat_namePaint.setColor(AvatarDrawable.getNameColorForId(currentUser.id));
      }
 else       if (currentChat != null) {
        if (ChatObject.isChannel(currentChat) && !currentChat.megagroup) {
          Theme.chat_namePaint.setColor(AvatarDrawable.getNameColorForId(5));
        }
 else {
          Theme.chat_namePaint.setColor(AvatarDrawable.getNameColorForId(currentChat.id));
        }
      }
 else {
        Theme.chat_namePaint.setColor(AvatarDrawable.getNameColorForId(0));
      }
      nameY=AndroidUtilities.dp(drawPinnedTop ? 9 : 10);
    }
    canvas.translate(nameX,nameY);
    nameLayout.draw(canvas);
    canvas.restore();
    if (adminLayout != null) {
      Theme.chat_adminPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_adminSelectedText : Theme.key_chat_adminText));
      canvas.save();
      canvas.translate(backgroundDrawableLeft + backgroundDrawableRight - AndroidUtilities.dp(11) - adminLayout.getLineWidth(0),nameY + AndroidUtilities.dp(0.5f));
      adminLayout.draw(canvas);
      canvas.restore();
    }
  }
  if (drawForwardedName && forwardedNameLayout[0] != null && forwardedNameLayout[1] != null && (currentPosition == null || currentPosition.minY == 0 && currentPosition.minX == 0)) {
    if (currentMessageObject.type == MessageObject.TYPE_ROUND_VIDEO) {
      Theme.chat_forwardNamePaint.setColor(Theme.getColor(Theme.key_chat_stickerReplyNameText));
      if (currentMessageObject.isOutOwner()) {
        forwardNameX=AndroidUtilities.dp(23);
      }
 else {
        forwardNameX=backgroundDrawableLeft + backgroundDrawableRight + AndroidUtilities.dp(17);
      }
      forwardNameY=AndroidUtilities.dp(12);
      int backWidth=forwardedNameWidth + AndroidUtilities.dp(14);
      Theme.chat_systemDrawable.setColorFilter(Theme.colorFilter);
      Theme.chat_systemDrawable.setBounds(forwardNameX - AndroidUtilities.dp(7),forwardNameY - AndroidUtilities.dp(6),forwardNameX - AndroidUtilities.dp(7) + backWidth,forwardNameY + AndroidUtilities.dp(38));
      Theme.chat_systemDrawable.draw(canvas);
    }
 else {
      forwardNameY=AndroidUtilities.dp(10 + (drawNameLayout ? 19 : 0));
      if (currentMessageObject.isOutOwner()) {
        Theme.chat_forwardNamePaint.setColor(Theme.getColor(Theme.key_chat_outForwardedNameText));
        forwardNameX=backgroundDrawableLeft + AndroidUtilities.dp(11);
      }
 else {
        Theme.chat_forwardNamePaint.setColor(Theme.getColor(Theme.key_chat_inForwardedNameText));
        if (mediaBackground) {
          forwardNameX=backgroundDrawableLeft + AndroidUtilities.dp(11);
        }
 else {
          forwardNameX=backgroundDrawableLeft + AndroidUtilities.dp(!mediaBackground && drawPinnedBottom ? 11 : 17);
        }
      }
    }
    for (int a=0; a < 2; a++) {
      canvas.save();
      canvas.translate(forwardNameX - forwardNameOffsetX[a],forwardNameY + AndroidUtilities.dp(16) * a);
      forwardedNameLayout[a].draw(canvas);
      canvas.restore();
    }
  }
  if (replyNameLayout != null) {
    if (currentMessageObject.shouldDrawWithoutBackground()) {
      Theme.chat_replyLinePaint.setColor(Theme.getColor(Theme.key_chat_stickerReplyLine));
      Theme.chat_replyNamePaint.setColor(Theme.getColor(Theme.key_chat_stickerReplyNameText));
      Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_stickerReplyMessageText));
      int backWidth=Math.max(replyNameWidth,replyTextWidth) + AndroidUtilities.dp(14);
      Theme.chat_systemDrawable.setColorFilter(Theme.colorFilter);
      Theme.chat_systemDrawable.setBounds(replyStartX - AndroidUtilities.dp(7),replyStartY - AndroidUtilities.dp(6),replyStartX - AndroidUtilities.dp(7) + backWidth,replyStartY + AndroidUtilities.dp(41));
      Theme.chat_systemDrawable.draw(canvas);
    }
 else {
      if (currentMessageObject.isOutOwner()) {
        Theme.chat_replyLinePaint.setColor(Theme.getColor(Theme.key_chat_outReplyLine));
        Theme.chat_replyNamePaint.setColor(Theme.getColor(Theme.key_chat_outReplyNameText));
        if (currentMessageObject.hasValidReplyMessageObject() && currentMessageObject.replyMessageObject.type == 0 && !(currentMessageObject.replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame || currentMessageObject.replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice)) {
          Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_outReplyMessageText));
        }
 else {
          Theme.chat_replyTextPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_outReplyMediaMessageSelectedText : Theme.key_chat_outReplyMediaMessageText));
        }
      }
 else {
        Theme.chat_replyLinePaint.setColor(Theme.getColor(Theme.key_chat_inReplyLine));
        Theme.chat_replyNamePaint.setColor(Theme.getColor(Theme.key_chat_inReplyNameText));
        if (currentMessageObject.hasValidReplyMessageObject() && currentMessageObject.replyMessageObject.type == 0 && !(currentMessageObject.replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaGame || currentMessageObject.replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice)) {
          Theme.chat_replyTextPaint.setColor(Theme.getColor(Theme.key_chat_inReplyMessageText));
        }
 else {
          Theme.chat_replyTextPaint.setColor(Theme.getColor(isDrawSelectionBackground() ? Theme.key_chat_inReplyMediaMessageSelectedText : Theme.key_chat_inReplyMediaMessageText));
        }
      }
    }
    if (currentPosition == null || currentPosition.minY == 0 && currentPosition.minX == 0) {
      canvas.drawRect(replyStartX,replyStartY,replyStartX + AndroidUtilities.dp(2),replyStartY + AndroidUtilities.dp(35),Theme.chat_replyLinePaint);
      if (needReplyImage) {
        replyImageReceiver.setImageCoords(replyStartX + AndroidUtilities.dp(10),replyStartY,AndroidUtilities.dp(35),AndroidUtilities.dp(35));
        replyImageReceiver.draw(canvas);
      }
      if (replyNameLayout != null) {
        canvas.save();
        canvas.translate(replyStartX - replyNameOffset + AndroidUtilities.dp(10 + (needReplyImage ? 44 : 0)),replyStartY);
        replyNameLayout.draw(canvas);
        canvas.restore();
      }
      if (replyTextLayout != null) {
        canvas.save();
        canvas.translate(replyStartX - replyTextOffset + AndroidUtilities.dp(10 + (needReplyImage ? 44 : 0)),replyStartY + AndroidUtilities.dp(19));
        replyTextLayout.draw(canvas);
        canvas.restore();
      }
    }
  }
}
