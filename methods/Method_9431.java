private void sendForm(){
  if (canceled) {
    return;
  }
  showEditDoneProgress(true,true);
  validateRequest=new TLRPC.TL_payments_validateRequestedInfo();
  validateRequest.save=saveShippingInfo;
  validateRequest.msg_id=messageObject.getId();
  validateRequest.info=new TLRPC.TL_paymentRequestedInfo();
  if (paymentForm.invoice.name_requested) {
    validateRequest.info.name=inputFields[FIELD_NAME].getText().toString();
    validateRequest.info.flags|=1;
  }
  if (paymentForm.invoice.phone_requested) {
    validateRequest.info.phone="+" + inputFields[FIELD_PHONECODE].getText().toString() + inputFields[FIELD_PHONE].getText().toString();
    validateRequest.info.flags|=2;
  }
  if (paymentForm.invoice.email_requested) {
    validateRequest.info.email=inputFields[FIELD_EMAIL].getText().toString().trim();
    validateRequest.info.flags|=4;
  }
  if (paymentForm.invoice.shipping_address_requested) {
    validateRequest.info.shipping_address=new TLRPC.TL_postAddress();
    validateRequest.info.shipping_address.street_line1=inputFields[FIELD_STREET1].getText().toString();
    validateRequest.info.shipping_address.street_line2=inputFields[FIELD_STREET2].getText().toString();
    validateRequest.info.shipping_address.city=inputFields[FIELD_CITY].getText().toString();
    validateRequest.info.shipping_address.state=inputFields[FIELD_STATE].getText().toString();
    validateRequest.info.shipping_address.country_iso2=countryName != null ? countryName : "";
    validateRequest.info.shipping_address.post_code=inputFields[FIELD_POSTCODE].getText().toString();
    validateRequest.info.flags|=8;
  }
  final TLObject req=validateRequest;
  ConnectionsManager.getInstance(currentAccount).sendRequest(validateRequest,(response,error) -> {
    if (response instanceof TLRPC.TL_payments_validatedRequestedInfo) {
      AndroidUtilities.runOnUIThread(() -> {
        requestedInfo=(TLRPC.TL_payments_validatedRequestedInfo)response;
        if (paymentForm.saved_info != null && !saveShippingInfo) {
          TLRPC.TL_payments_clearSavedInfo req1=new TLRPC.TL_payments_clearSavedInfo();
          req1.info=true;
          ConnectionsManager.getInstance(currentAccount).sendRequest(req1,(response1,error1) -> {
          }
);
        }
        goToNextStep();
        setDonePressed(false);
        showEditDoneProgress(true,false);
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> {
        setDonePressed(false);
        showEditDoneProgress(true,false);
        if (error != null) {
switch (error.text) {
case "REQ_INFO_NAME_INVALID":
            shakeField(FIELD_NAME);
          break;
case "REQ_INFO_PHONE_INVALID":
        shakeField(FIELD_PHONE);
      break;
case "REQ_INFO_EMAIL_INVALID":
    shakeField(FIELD_EMAIL);
  break;
case "ADDRESS_COUNTRY_INVALID":
shakeField(FIELD_COUNTRY);
break;
case "ADDRESS_CITY_INVALID":
shakeField(FIELD_CITY);
break;
case "ADDRESS_POSTCODE_INVALID":
shakeField(FIELD_POSTCODE);
break;
case "ADDRESS_STATE_INVALID":
shakeField(FIELD_STATE);
break;
case "ADDRESS_STREET_LINE1_INVALID":
shakeField(FIELD_STREET1);
break;
case "ADDRESS_STREET_LINE2_INVALID":
shakeField(FIELD_STREET2);
break;
default :
AlertsCreator.processError(currentAccount,error,PaymentFormActivity.this,req);
break;
}
}
}
);
}
}
,ConnectionsManager.RequestFlagFailOnServerErrors);
}
