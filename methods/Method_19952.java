/** 
 * Check to see if the Intent has an email link, and if so set up the UI accordingly. This can be called from either onCreate or onNewIntent, depending on how the Activity was launched.
 */
private void checkIntent(@Nullable Intent intent){
  if (intentHasEmailLink(intent)) {
    mEmailLink=intent.getData().toString();
    mStatusText.setText(R.string.status_link_found);
    mSendLinkButton.setEnabled(false);
    mSignInButton.setEnabled(true);
  }
 else {
    mStatusText.setText(R.string.status_email_not_sent);
    mSendLinkButton.setEnabled(true);
    mSignInButton.setEnabled(false);
  }
}
