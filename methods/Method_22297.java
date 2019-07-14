/** 
 * creates an email prompt
 * @param savedEmail the content of the prompt (usually from a saved state or settings)
 * @return the email prompt
 */
@NonNull protected EditText getEmailPrompt(@Nullable CharSequence savedEmail){
  final EditText userEmailView=new EditText(this);
  userEmailView.setSingleLine();
  userEmailView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
  if (savedEmail != null) {
    userEmailView.setText(savedEmail);
  }
 else {
    final SharedPreferences prefs=sharedPreferencesFactory.create();
    userEmailView.setText(prefs.getString(ACRA.PREF_USER_EMAIL_ADDRESS,""));
  }
  return userEmailView;
}
