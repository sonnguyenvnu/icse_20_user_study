@Override public void onAuthenticateError(int requestCode,AuthenticateRequest.RequestState requestState,ApiError error){
  updateViews(true);
  LogUtils.e(error.toString());
  if (error.bodyJson != null && error.code != Codes.Custom.INVALID_ERROR_RESPONSE) {
    String errorString=getString(error.getErrorStringRes());
    Activity activity=getActivity();
switch (error.code) {
case Codes.Token.INVALID_APIKEY:
case Codes.Token.APIKEY_IS_BLOCKED:
case Codes.Token.INVALID_REQUEST_URI:
case Codes.Token.INVALID_CREDENCIAL2:
case Codes.Token.REQUIRED_PARAMETER_IS_MISSING:
case Codes.Token.CLIENT_SECRET_MISMATCH:
      ToastUtils.show(errorString,activity);
    startActivity(AuthenticatorUtils.makeSetApiKeyIntent((activity)));
  break;
case Codes.Token.USER_HAS_BLOCKED:
case Codes.Token.USER_LOCKED:
ToastUtils.show(errorString,activity);
startActivity(AuthenticatorUtils.makeWebsiteIntent(activity));
break;
case Codes.Token.NOT_TRIAL_USER:
case Codes.Token.INVALID_USER:
mUsernameLayout.setError(errorString);
break;
default :
mPasswordLayout.setError(errorString);
break;
}
}
 else if (error.response != null) {
mPasswordLayout.setError(getString(R.string.auth_error_invalid_response));
}
 else {
mPasswordLayout.setError(getString(R.string.auth_error_unknown));
}
}
