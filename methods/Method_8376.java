public static Dialog processError(int currentAccount,TLRPC.TL_error error,BaseFragment fragment,TLObject request,Object... args){
  if (error.code == 406 || error.text == null) {
    return null;
  }
  if (request instanceof TLRPC.TL_account_saveSecureValue || request instanceof TLRPC.TL_account_getAuthorizationForm) {
    if (error.text.contains("PHONE_NUMBER_INVALID")) {
      showSimpleAlert(fragment,LocaleController.getString("InvalidPhoneNumber",R.string.InvalidPhoneNumber));
    }
 else     if (error.text.startsWith("FLOOD_WAIT")) {
      showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else     if ("APP_VERSION_OUTDATED".equals(error.text)) {
      showUpdateAppAlert(fragment.getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
    }
 else {
      showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text);
    }
  }
 else   if (request instanceof TLRPC.TL_channels_joinChannel || request instanceof TLRPC.TL_channels_editAdmin || request instanceof TLRPC.TL_channels_inviteToChannel || request instanceof TLRPC.TL_messages_addChatUser || request instanceof TLRPC.TL_messages_startBot || request instanceof TLRPC.TL_channels_editBanned || request instanceof TLRPC.TL_messages_editChatDefaultBannedRights || request instanceof TLRPC.TL_messages_editChatAdmin) {
    if (fragment != null) {
      AlertsCreator.showAddUserAlert(error.text,fragment,(Boolean)args[0]);
    }
 else {
      if (error.text.equals("PEER_FLOOD")) {
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needShowAlert,1);
      }
    }
  }
 else   if (request instanceof TLRPC.TL_messages_createChat) {
    if (error.text.startsWith("FLOOD_WAIT")) {
      AlertsCreator.showFloodWaitAlert(error.text,fragment);
    }
 else {
      AlertsCreator.showAddUserAlert(error.text,fragment,false);
    }
  }
 else   if (request instanceof TLRPC.TL_channels_createChannel) {
    if (error.text.startsWith("FLOOD_WAIT")) {
      AlertsCreator.showFloodWaitAlert(error.text,fragment);
    }
 else {
      AlertsCreator.showAddUserAlert(error.text,fragment,false);
    }
  }
 else   if (request instanceof TLRPC.TL_messages_editMessage) {
    if (!error.text.equals("MESSAGE_NOT_MODIFIED")) {
      if (fragment != null) {
        showSimpleAlert(fragment,LocaleController.getString("EditMessageError",R.string.EditMessageError));
      }
 else {
        showSimpleToast(fragment,LocaleController.getString("EditMessageError",R.string.EditMessageError));
      }
    }
  }
 else   if (request instanceof TLRPC.TL_messages_sendMessage || request instanceof TLRPC.TL_messages_sendMedia || request instanceof TLRPC.TL_messages_sendBroadcast || request instanceof TLRPC.TL_messages_sendInlineBotResult || request instanceof TLRPC.TL_messages_forwardMessages || request instanceof TLRPC.TL_messages_sendMultiMedia) {
    if (error.text.equals("PEER_FLOOD")) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needShowAlert,0);
    }
 else     if (error.text.equals("USER_BANNED_IN_CHANNEL")) {
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.needShowAlert,5);
    }
  }
 else   if (request instanceof TLRPC.TL_messages_importChatInvite) {
    if (error.text.startsWith("FLOOD_WAIT")) {
      showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else     if (error.text.equals("USERS_TOO_MUCH")) {
      showSimpleAlert(fragment,LocaleController.getString("JoinToGroupErrorFull",R.string.JoinToGroupErrorFull));
    }
 else {
      showSimpleAlert(fragment,LocaleController.getString("JoinToGroupErrorNotExist",R.string.JoinToGroupErrorNotExist));
    }
  }
 else   if (request instanceof TLRPC.TL_messages_getAttachedStickers) {
    if (fragment != null && fragment.getParentActivity() != null) {
      Toast.makeText(fragment.getParentActivity(),LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text,Toast.LENGTH_SHORT).show();
    }
  }
 else   if (request instanceof TLRPC.TL_account_confirmPhone || request instanceof TLRPC.TL_account_verifyPhone || request instanceof TLRPC.TL_account_verifyEmail) {
    if (error.text.contains("PHONE_CODE_EMPTY") || error.text.contains("PHONE_CODE_INVALID") || error.text.contains("CODE_INVALID") || error.text.contains("CODE_EMPTY")) {
      return showSimpleAlert(fragment,LocaleController.getString("InvalidCode",R.string.InvalidCode));
    }
 else     if (error.text.contains("PHONE_CODE_EXPIRED") || error.text.contains("EMAIL_VERIFY_EXPIRED")) {
      return showSimpleAlert(fragment,LocaleController.getString("CodeExpired",R.string.CodeExpired));
    }
 else     if (error.text.startsWith("FLOOD_WAIT")) {
      return showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else {
      return showSimpleAlert(fragment,error.text);
    }
  }
 else   if (request instanceof TLRPC.TL_auth_resendCode) {
    if (error.text.contains("PHONE_NUMBER_INVALID")) {
      return showSimpleAlert(fragment,LocaleController.getString("InvalidPhoneNumber",R.string.InvalidPhoneNumber));
    }
 else     if (error.text.contains("PHONE_CODE_EMPTY") || error.text.contains("PHONE_CODE_INVALID")) {
      return showSimpleAlert(fragment,LocaleController.getString("InvalidCode",R.string.InvalidCode));
    }
 else     if (error.text.contains("PHONE_CODE_EXPIRED")) {
      return showSimpleAlert(fragment,LocaleController.getString("CodeExpired",R.string.CodeExpired));
    }
 else     if (error.text.startsWith("FLOOD_WAIT")) {
      return showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else     if (error.code != -1000) {
      return showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text);
    }
  }
 else   if (request instanceof TLRPC.TL_account_sendConfirmPhoneCode) {
    if (error.code == 400) {
      return showSimpleAlert(fragment,LocaleController.getString("CancelLinkExpired",R.string.CancelLinkExpired));
    }
 else     if (error.text != null) {
      if (error.text.startsWith("FLOOD_WAIT")) {
        return showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
      }
 else {
        return showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred));
      }
    }
  }
 else   if (request instanceof TLRPC.TL_account_changePhone) {
    if (error.text.contains("PHONE_NUMBER_INVALID")) {
      showSimpleAlert(fragment,LocaleController.getString("InvalidPhoneNumber",R.string.InvalidPhoneNumber));
    }
 else     if (error.text.contains("PHONE_CODE_EMPTY") || error.text.contains("PHONE_CODE_INVALID")) {
      showSimpleAlert(fragment,LocaleController.getString("InvalidCode",R.string.InvalidCode));
    }
 else     if (error.text.contains("PHONE_CODE_EXPIRED")) {
      showSimpleAlert(fragment,LocaleController.getString("CodeExpired",R.string.CodeExpired));
    }
 else     if (error.text.startsWith("FLOOD_WAIT")) {
      showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else {
      showSimpleAlert(fragment,error.text);
    }
  }
 else   if (request instanceof TLRPC.TL_account_sendChangePhoneCode) {
    if (error.text.contains("PHONE_NUMBER_INVALID")) {
      showSimpleAlert(fragment,LocaleController.getString("InvalidPhoneNumber",R.string.InvalidPhoneNumber));
    }
 else     if (error.text.contains("PHONE_CODE_EMPTY") || error.text.contains("PHONE_CODE_INVALID")) {
      showSimpleAlert(fragment,LocaleController.getString("InvalidCode",R.string.InvalidCode));
    }
 else     if (error.text.contains("PHONE_CODE_EXPIRED")) {
      showSimpleAlert(fragment,LocaleController.getString("CodeExpired",R.string.CodeExpired));
    }
 else     if (error.text.startsWith("FLOOD_WAIT")) {
      showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
    }
 else     if (error.text.startsWith("PHONE_NUMBER_OCCUPIED")) {
      showSimpleAlert(fragment,LocaleController.formatString("ChangePhoneNumberOccupied",R.string.ChangePhoneNumberOccupied,(String)args[0]));
    }
 else {
      showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred));
    }
  }
 else   if (request instanceof TLRPC.TL_updateUserName) {
switch (error.text) {
case "USERNAME_INVALID":
      showSimpleAlert(fragment,LocaleController.getString("UsernameInvalid",R.string.UsernameInvalid));
    break;
case "USERNAME_OCCUPIED":
  showSimpleAlert(fragment,LocaleController.getString("UsernameInUse",R.string.UsernameInUse));
break;
default :
showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred));
break;
}
}
 else if (request instanceof TLRPC.TL_contacts_importContacts) {
if (error == null || error.text.startsWith("FLOOD_WAIT")) {
showSimpleAlert(fragment,LocaleController.getString("FloodWait",R.string.FloodWait));
}
 else {
showSimpleAlert(fragment,LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred) + "\n" + error.text);
}
}
 else if (request instanceof TLRPC.TL_account_getPassword || request instanceof TLRPC.TL_account_getTmpPassword) {
if (error.text.startsWith("FLOOD_WAIT")) {
showSimpleToast(fragment,getFloodWaitString(error.text));
}
 else {
showSimpleToast(fragment,error.text);
}
}
 else if (request instanceof TLRPC.TL_payments_sendPaymentForm) {
switch (error.text) {
case "BOT_PRECHECKOUT_FAILED":
showSimpleToast(fragment,LocaleController.getString("PaymentPrecheckoutFailed",R.string.PaymentPrecheckoutFailed));
break;
case "PAYMENT_FAILED":
showSimpleToast(fragment,LocaleController.getString("PaymentFailed",R.string.PaymentFailed));
break;
default :
showSimpleToast(fragment,error.text);
break;
}
}
 else if (request instanceof TLRPC.TL_payments_validateRequestedInfo) {
switch (error.text) {
case "SHIPPING_NOT_AVAILABLE":
showSimpleToast(fragment,LocaleController.getString("PaymentNoShippingMethod",R.string.PaymentNoShippingMethod));
break;
default :
showSimpleToast(fragment,error.text);
break;
}
}
return null;
}
