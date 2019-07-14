public void generatePaymentSentMessageText(TLRPC.User fromUser){
  if (fromUser == null) {
    fromUser=MessagesController.getInstance(currentAccount).getUser((int)getDialogId());
  }
  String name;
  if (fromUser != null) {
    name=UserObject.getFirstName(fromUser);
  }
 else {
    name="";
  }
  if (replyMessageObject != null && replyMessageObject.messageOwner.media instanceof TLRPC.TL_messageMediaInvoice) {
    messageText=LocaleController.formatString("PaymentSuccessfullyPaid",R.string.PaymentSuccessfullyPaid,LocaleController.getInstance().formatCurrencyString(messageOwner.action.total_amount,messageOwner.action.currency),name,replyMessageObject.messageOwner.media.title);
  }
 else {
    messageText=LocaleController.formatString("PaymentSuccessfullyPaidNoItem",R.string.PaymentSuccessfullyPaidNoItem,LocaleController.getInstance().formatCurrencyString(messageOwner.action.total_amount,messageOwner.action.currency),name);
  }
}
