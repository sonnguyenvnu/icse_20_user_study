public static Intent makeIntent(SimpleUser user,Context context){
  return new Intent(context,BroadcastListActivity.class).putExtra(EXTRA_USER,user);
}
