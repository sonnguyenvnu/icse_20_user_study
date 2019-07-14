@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  toolbar.setBackgroundColor(getPrimaryColor());
  mRecyclerView.setBackgroundColor(getBackgroundColor());
  setStatusBarColor();
  setNavBarColor();
  toolbar.setTitle(getTitle());
  setRecentApp(getTitle().toString());
  ((CardView)findViewById(R.id.white_list_decription_card)).setCardBackgroundColor(getCardBackgroundColor());
  ((TextView)findViewById(R.id.white_list_decription_txt)).setTextColor(getTextColor());
  ((TextView)findViewById(R.id.emoji_easter_egg)).setTextColor(getSubTextColor());
  ((TextView)findViewById(R.id.nothing_to_show_text_emoji_easter_egg)).setTextColor(getSubTextColor());
  findViewById(org.horaapps.leafpic.R.id.rl_ea).setBackgroundColor(getBackgroundColor());
}
