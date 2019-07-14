public static Intent makeIntent(String userIdOrUid,Context context){
  return new Intent(context,BroadcastListActivity.class).putExtra(EXTRA_USER_ID_OR_UID,userIdOrUid);
}
