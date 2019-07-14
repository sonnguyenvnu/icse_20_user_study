private void loadUri(Uri uri){
  album=new Album(uri.toString(),uri.getPath());
  album.settings=AlbumSettings.getDefaults();
  try {
    InputStream inputStream=getContentResolver().openInputStream(uri);
    if (inputStream != null)     inputStream.close();
  }
 catch (  Exception ex) {
    boolean showEasterEgg=Prefs.showEasterEgg();
    ((TextView)findViewById(R.id.nothing_to_show_text_emoji_easter_egg)).setText(R.string.error_occured_open_media);
    findViewById(R.id.nothing_to_show_placeholder).setVisibility(!showEasterEgg ? View.VISIBLE : View.GONE);
    findViewById(R.id.ll_emoji_easter_egg).setVisibility(showEasterEgg ? View.VISIBLE : View.GONE);
  }
  media=new ArrayList<>(Collections.singletonList(new Media(uri)));
  position=0;
  customUri=true;
}
