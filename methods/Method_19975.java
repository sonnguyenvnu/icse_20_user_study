/** 
 * Display a welcome message in all caps if welcome_message_caps is set to true. Otherwise, display a welcome message as fetched from welcome_message.
 */
private void displayWelcomeMessage(){
  String welcomeMessage=mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);
  if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY)) {
    mWelcomeTextView.setAllCaps(true);
  }
 else {
    mWelcomeTextView.setAllCaps(false);
  }
  mWelcomeTextView.setText(welcomeMessage);
}
