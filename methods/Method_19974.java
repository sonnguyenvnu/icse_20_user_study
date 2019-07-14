/** 
 * Fetch a welcome message from the Remote Config service, and then activate it.
 */
private void fetchWelcome(){
  mWelcomeTextView.setText(mFirebaseRemoteConfig.getString(LOADING_PHRASE_CONFIG_KEY));
  mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this,new OnCompleteListener<Boolean>(){
    @Override public void onComplete(    @NonNull Task<Boolean> task){
      if (task.isSuccessful()) {
        boolean updated=task.getResult();
        Log.d(TAG,"Config params updated: " + updated);
        Toast.makeText(MainActivity.this,"Fetch and activate succeeded",Toast.LENGTH_SHORT).show();
      }
 else {
        Toast.makeText(MainActivity.this,"Fetch failed",Toast.LENGTH_SHORT).show();
      }
      displayWelcomeMessage();
    }
  }
);
}
