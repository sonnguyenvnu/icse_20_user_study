public static Intent launchIntentForFolder(Context context,Folder folder){
  Intent intent=new Intent(context,PlayListDetailsActivity.class);
  intent.putExtra(EXTRA_FOLDER,folder);
  return intent;
}
