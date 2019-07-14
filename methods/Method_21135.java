public void startFacebookConfirmationActivity(final @NonNull ErrorEnvelope.FacebookUser facebookUser,final @NonNull String accessTokenString){
  final Intent intent=new Intent(this,FacebookConfirmationActivity.class).putExtra(IntentKey.FACEBOOK_USER,facebookUser).putExtra(IntentKey.FACEBOOK_TOKEN,accessTokenString);
  startActivityForResult(intent,ActivityRequestCodes.LOGIN_FLOW);
  transition(this,fadeIn());
}
