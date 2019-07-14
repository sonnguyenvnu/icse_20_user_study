/** 
 * This is called when a user loses data or Wi-Fi connection. When the user loses connection we will show our network error Snackbar. We're also using (findViewById(android.R.id.content) to get the root view of our activity so whatever Activity the user navigates to while disconnected the error will display.
 */
@Override public void onNetworkConnectionChanged(final boolean isConnected){
  if (!isConnected) {
    ActivityExtKt.showSnackbar(findViewById(android.R.id.content),getString(R.string.Youre_offline));
  }
}
