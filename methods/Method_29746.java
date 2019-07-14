private Bundle makeFailureIntentBundle(Intent intent,String authFailedMessage){
  Bundle bundle=makeIntentBundle(intent);
  bundle.putString(AccountManager.KEY_AUTH_FAILED_MESSAGE,authFailedMessage);
  return bundle;
}
