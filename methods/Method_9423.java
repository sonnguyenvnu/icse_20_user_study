private void init(TLRPC.TL_payments_paymentForm form,MessageObject message,int step,TLRPC.TL_payments_validatedRequestedInfo validatedRequestedInfo,TLRPC.TL_shippingOption shipping,String tokenJson,String card,TLRPC.TL_payments_validateRequestedInfo request,boolean saveCard,TLRPC.TL_inputPaymentCredentialsAndroidPay androidPay){
  currentStep=step;
  paymentJson=tokenJson;
  androidPayCredentials=androidPay;
  requestedInfo=validatedRequestedInfo;
  paymentForm=form;
  shippingOption=shipping;
  messageObject=message;
  saveCardInfo=saveCard;
  isWebView=!"stripe".equals(paymentForm.native_provider);
  botUser=MessagesController.getInstance(currentAccount).getUser(form.bot_id);
  if (botUser != null) {
    currentBotName=botUser.first_name;
  }
 else {
    currentBotName="";
  }
  currentItemName=message.messageOwner.media.title;
  validateRequest=request;
  saveShippingInfo=true;
  if (saveCard) {
    saveCardInfo=saveCard;
  }
 else {
    saveCardInfo=paymentForm.saved_credentials != null;
  }
  if (card == null) {
    if (form.saved_credentials != null) {
      cardName=form.saved_credentials.title;
    }
  }
 else {
    cardName=card;
  }
}
