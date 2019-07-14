public static Intent makeConfirmPasswordIntent(Account account,ConfirmPasswordListener listener){
  try {
    return confirmPassword(null,account,makeConfirmPasswordCallback(listener),null).getResult().getParcelable(AccountManager.KEY_INTENT);
  }
 catch (  AuthenticatorException|IOException|OperationCanceledException e) {
    e.printStackTrace();
    return null;
  }
}
