public void measureInlineBotButtons(){
  wantedBotKeyboardWidth=0;
  if (!(messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup)) {
    return;
  }
  Theme.createChatResources(null,true);
  if (botButtonsLayout == null) {
    botButtonsLayout=new StringBuilder();
  }
 else {
    botButtonsLayout.setLength(0);
  }
  for (int a=0; a < messageOwner.reply_markup.rows.size(); a++) {
    TLRPC.TL_keyboardButtonRow row=messageOwner.reply_markup.rows.get(a);
    int maxButtonSize=0;
    int size=row.buttons.size();
    for (int b=0; b < size; b++) {
      TLRPC.KeyboardButton button=row.buttons.get(b);
      botButtonsLayout.append(a).append(b);
      CharSequence text;
      if (button instanceof TLRPC.TL_keyboardButtonBuy && (messageOwner.media.flags & 4) != 0) {
        text=LocaleController.getString("PaymentReceipt",R.string.PaymentReceipt);
      }
 else {
        text=Emoji.replaceEmoji(button.text,Theme.chat_msgBotButtonPaint.getFontMetricsInt(),AndroidUtilities.dp(15),false);
      }
      StaticLayout staticLayout=new StaticLayout(text,Theme.chat_msgBotButtonPaint,AndroidUtilities.dp(2000),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      if (staticLayout.getLineCount() > 0) {
        float width=staticLayout.getLineWidth(0);
        float left=staticLayout.getLineLeft(0);
        if (left < width) {
          width-=left;
        }
        maxButtonSize=Math.max(maxButtonSize,(int)Math.ceil(width) + AndroidUtilities.dp(4));
      }
    }
    wantedBotKeyboardWidth=Math.max(wantedBotKeyboardWidth,(maxButtonSize + AndroidUtilities.dp(12)) * size + AndroidUtilities.dp(5) * (size - 1));
  }
}
