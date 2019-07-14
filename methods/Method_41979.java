@Deprecated public void checkNothing(boolean status){
  ((TextView)findViewById(R.id.emoji_easter_egg)).setTextColor(getSubTextColor());
  ((TextView)findViewById(R.id.nothing_to_show_text_emoji_easter_egg)).setTextColor(getSubTextColor());
  if (status && Prefs.showEasterEgg()) {
    findViewById(R.id.ll_emoji_easter_egg).setVisibility(View.VISIBLE);
    findViewById(R.id.nothing_to_show_placeholder).setVisibility(View.VISIBLE);
  }
 else {
    findViewById(R.id.ll_emoji_easter_egg).setVisibility(View.GONE);
    findViewById(R.id.nothing_to_show_placeholder).setVisibility(View.GONE);
  }
}
