private void checkNothing(){
  findViewById(R.id.white_list_decription_card).setVisibility(showDescriptionCard() ? View.VISIBLE : View.GONE);
  findViewById(R.id.nothing_to_show_placeholder).setVisibility(showNothingToShowPlaceholder() ? View.VISIBLE : View.GONE);
  findViewById(R.id.ll_emoji_easter_egg).setVisibility(showEasterEgg() ? View.VISIBLE : View.GONE);
}
