@OnClick(R.id.about_link_rate) public void onRate(){
  Uri uri=Uri.parse(String.format("market://details?id=%s",BuildConfig.APPLICATION_ID));
  Intent goToMarket=new Intent(Intent.ACTION_VIEW,uri);
  int flags=Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)   flags|=Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
  goToMarket.addFlags(flags);
  try {
    startActivity(goToMarket);
  }
 catch (  ActivityNotFoundException e) {
    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("http://play.google.com/store/apps/details?id=%s",BuildConfig.APPLICATION_ID))));
  }
}
