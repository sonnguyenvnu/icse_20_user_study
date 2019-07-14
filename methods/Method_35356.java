public static Intent launchIntentForPlayList(Context context,PlayList playList){
  Intent intent=new Intent(context,PlayListDetailsActivity.class);
  intent.putExtra(EXTRA_PLAY_LIST,playList);
  return intent;
}
