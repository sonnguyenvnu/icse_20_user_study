/** 
 * Determine if the given Intent contains an email sign-in link.
 */
private boolean intentHasEmailLink(@Nullable Intent intent){
  if (intent != null && intent.getData() != null) {
    String intentData=intent.getData().toString();
    if (mAuth.isSignInWithEmailLink(intentData)) {
      return true;
    }
  }
  return false;
}
