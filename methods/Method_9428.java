private void goToNextStep(){
  if (currentStep == 0) {
    int nextStep;
    if (paymentForm.invoice.flexible) {
      nextStep=1;
    }
 else     if (paymentForm.saved_credentials != null) {
      if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
        if (UserConfig.getInstance(currentAccount).tmpPassword.valid_until < ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60) {
          UserConfig.getInstance(currentAccount).tmpPassword=null;
          UserConfig.getInstance(currentAccount).saveConfig(false);
        }
      }
      if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
        nextStep=4;
      }
 else {
        nextStep=3;
      }
    }
 else {
      nextStep=2;
    }
    presentFragment(new PaymentFormActivity(paymentForm,messageObject,nextStep,requestedInfo,null,null,cardName,validateRequest,saveCardInfo,androidPayCredentials),isWebView);
  }
 else   if (currentStep == 1) {
    int nextStep;
    if (paymentForm.saved_credentials != null) {
      if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
        if (UserConfig.getInstance(currentAccount).tmpPassword.valid_until < ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60) {
          UserConfig.getInstance(currentAccount).tmpPassword=null;
          UserConfig.getInstance(currentAccount).saveConfig(false);
        }
      }
      if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
        nextStep=4;
      }
 else {
        nextStep=3;
      }
    }
 else {
      nextStep=2;
    }
    presentFragment(new PaymentFormActivity(paymentForm,messageObject,nextStep,requestedInfo,shippingOption,null,cardName,validateRequest,saveCardInfo,androidPayCredentials),isWebView);
  }
 else   if (currentStep == 2) {
    if (paymentForm.password_missing && saveCardInfo) {
      passwordFragment=new PaymentFormActivity(paymentForm,messageObject,6,requestedInfo,shippingOption,paymentJson,cardName,validateRequest,saveCardInfo,androidPayCredentials);
      passwordFragment.setCurrentPassword(currentPassword);
      passwordFragment.setDelegate(new PaymentFormActivityDelegate(){
        @Override public boolean didSelectNewCard(        String tokenJson,        String card,        boolean saveCard,        TLRPC.TL_inputPaymentCredentialsAndroidPay androidPay){
          if (delegate != null) {
            delegate.didSelectNewCard(tokenJson,card,saveCard,androidPay);
          }
          if (isWebView) {
            removeSelfFromStack();
          }
          return delegate != null;
        }
        @Override public void onFragmentDestroyed(){
          passwordFragment=null;
        }
        @Override public void currentPasswordUpdated(        TLRPC.TL_account_password password){
          currentPassword=password;
        }
      }
);
      presentFragment(passwordFragment,isWebView);
    }
 else {
      if (delegate != null) {
        delegate.didSelectNewCard(paymentJson,cardName,saveCardInfo,androidPayCredentials);
        finishFragment();
      }
 else {
        presentFragment(new PaymentFormActivity(paymentForm,messageObject,4,requestedInfo,shippingOption,paymentJson,cardName,validateRequest,saveCardInfo,androidPayCredentials),isWebView);
      }
    }
  }
 else   if (currentStep == 3) {
    int nextStep;
    if (passwordOk) {
      nextStep=4;
    }
 else {
      nextStep=2;
    }
    presentFragment(new PaymentFormActivity(paymentForm,messageObject,nextStep,requestedInfo,shippingOption,paymentJson,cardName,validateRequest,saveCardInfo,androidPayCredentials),!passwordOk);
  }
 else   if (currentStep == 4) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.paymentFinished);
    finishFragment();
  }
 else   if (currentStep == 6) {
    if (!delegate.didSelectNewCard(paymentJson,cardName,saveCardInfo,androidPayCredentials)) {
      presentFragment(new PaymentFormActivity(paymentForm,messageObject,4,requestedInfo,shippingOption,paymentJson,cardName,validateRequest,saveCardInfo,androidPayCredentials),true);
    }
 else {
      finishFragment();
    }
  }
}
