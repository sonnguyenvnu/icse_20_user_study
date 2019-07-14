private void callCallback(boolean success){
  if (!callbackCalled) {
    if (!TextUtils.isEmpty(currentCallbackUrl)) {
      if (success) {
        Browser.openUrl(getParentActivity(),Uri.parse(currentCallbackUrl + "&tg_passport=success"));
      }
 else       if (!ignoreOnFailure && (currentActivityType == TYPE_PASSWORD || currentActivityType == TYPE_REQUEST)) {
        Browser.openUrl(getParentActivity(),Uri.parse(currentCallbackUrl + "&tg_passport=cancel"));
      }
      callbackCalled=true;
    }
 else     if (needActivityResult) {
      if (success || (!ignoreOnFailure && (currentActivityType == TYPE_PASSWORD || currentActivityType == TYPE_REQUEST))) {
        getParentActivity().setResult(success ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
      }
      callbackCalled=true;
    }
  }
}
