private void sendData(){
  if (canceled) {
    return;
  }
  showEditDoneProgress(false,true);
  final TLRPC.TL_payments_sendPaymentForm req=new TLRPC.TL_payments_sendPaymentForm();
  req.msg_id=messageObject.getId();
  if (UserConfig.getInstance(currentAccount).tmpPassword != null && paymentForm.saved_credentials != null) {
    req.credentials=new TLRPC.TL_inputPaymentCredentialsSaved();
    req.credentials.id=paymentForm.saved_credentials.id;
    req.credentials.tmp_password=UserConfig.getInstance(currentAccount).tmpPassword.tmp_password;
  }
 else   if (androidPayCredentials != null) {
    req.credentials=androidPayCredentials;
  }
 else {
    req.credentials=new TLRPC.TL_inputPaymentCredentials();
    req.credentials.save=saveCardInfo;
    req.credentials.data=new TLRPC.TL_dataJSON();
    req.credentials.data.data=paymentJson;
  }
  if (requestedInfo != null && requestedInfo.id != null) {
    req.requested_info_id=requestedInfo.id;
    req.flags|=1;
  }
  if (shippingOption != null) {
    req.shipping_option_id=shippingOption.id;
    req.flags|=2;
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (response != null) {
      if (response instanceof TLRPC.TL_payments_paymentResult) {
        MessagesController.getInstance(currentAccount).processUpdates(((TLRPC.TL_payments_paymentResult)response).updates,false);
        AndroidUtilities.runOnUIThread(this::goToNextStep);
      }
 else       if (response instanceof TLRPC.TL_payments_paymentVerficationNeeded) {
        AndroidUtilities.runOnUIThread(() -> {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.paymentFinished);
          setDonePressed(false);
          webView.setVisibility(View.VISIBLE);
          webviewLoading=true;
          showEditDoneProgress(true,true);
          progressView.setVisibility(View.VISIBLE);
          doneItem.setEnabled(false);
          doneItem.getImageView().setVisibility(View.INVISIBLE);
          webView.loadUrl(webViewUrl=((TLRPC.TL_payments_paymentVerficationNeeded)response).url);
        }
);
      }
    }
 else {
      AndroidUtilities.runOnUIThread(() -> {
        AlertsCreator.processError(currentAccount,error,PaymentFormActivity.this,req);
        setDonePressed(false);
        showEditDoneProgress(false,false);
      }
);
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors);
}
