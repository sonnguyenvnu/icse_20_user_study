@Override public void onAuthenticateSuccess(int requestCode,AuthenticateRequest.RequestState requestState,AuthenticationResponse response){
  Account account=new Account(requestState.username,AccountContract.ACCOUNT_TYPE);
switch (mAuthMode) {
case AUTH_MODE_NEW:
    AccountUtils.addAccountExplicitly(account,requestState.password);
  AccountUtils.setActiveAccount(account);
break;
case AUTH_MODE_ADD:
AccountUtils.addAccountExplicitly(account,requestState.password);
break;
case AUTH_MODE_UPDATE:
case AUTH_MODE_CONFIRM:
AccountUtils.setPassword(account,requestState.password);
break;
}
AccountUtils.setUserName(account,response.userName);
AccountUtils.setUserId(account,response.userId);
AccountUtils.setAuthToken(account,AUTH_TOKEN_TYPE,response.accessToken);
AccountUtils.setRefreshToken(account,AUTH_TOKEN_TYPE,response.refreshToken);
Intent intent;
switch (mAuthMode) {
case AUTH_MODE_NEW:
case AUTH_MODE_ADD:
case AUTH_MODE_UPDATE:
intent=makeSuccessIntent(requestState.username);
break;
case AUTH_MODE_CONFIRM:
intent=makeBooleanIntent(true);
break;
default :
throw new IllegalArgumentException();
}
AppCompatAccountAuthenticatorActivity activity=(AppCompatAccountAuthenticatorActivity)getActivity();
activity.setAccountAuthenticatorResult(intent.getExtras());
activity.setResult(Activity.RESULT_OK,intent);
activity.finish();
}
