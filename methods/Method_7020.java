private boolean isValidKeyboardToSave(TLRPC.Message message){
  return message.reply_markup != null && !(message.reply_markup instanceof TLRPC.TL_replyInlineMarkup) && (!message.reply_markup.selective || message.mentioned);
}
