@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward) {
    if (webView != null) {
      if (currentStep != 4) {
        webView.loadUrl(webViewUrl=paymentForm.url);
      }
    }
 else     if (currentStep == 2) {
      inputFields[FIELD_CARD].requestFocus();
      AndroidUtilities.showKeyboard(inputFields[FIELD_CARD]);
    }
 else     if (currentStep == 3) {
      inputFields[FIELD_SAVEDPASSWORD].requestFocus();
      AndroidUtilities.showKeyboard(inputFields[FIELD_SAVEDPASSWORD]);
    }
 else     if (currentStep == 6) {
      if (!waitingForEmail) {
        inputFields[FIELD_ENTERPASSWORD].requestFocus();
        AndroidUtilities.showKeyboard(inputFields[FIELD_ENTERPASSWORD]);
      }
    }
  }
}
