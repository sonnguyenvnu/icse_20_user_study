@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  toolbar.setBackgroundColor(getPrimaryColor());
  setStatusBarColor();
  setNavBarColor();
  themeButton(btnDonatePP);
  setRecentApp(getString(R.string.donate));
  ((TextView)findViewById(R.id.team_name)).setTextColor(getAccentColor());
  ((TextView)findViewById(R.id.donate_googleplay_item_title)).setTextColor(getAccentColor());
  ((TextView)findViewById(R.id.donate_paypal_item_title)).setTextColor(getAccentColor());
  ((TextView)findViewById(R.id.donate_bitcoin_item_title)).setTextColor(getAccentColor());
  findViewById(R.id.donate_background).setBackgroundColor(getBackgroundColor());
  int color=getCardBackgroundColor();
  ((CardView)findViewById(R.id.donate_header_card)).setCardBackgroundColor(color);
  ((CardView)findViewById(R.id.donate_paypal_card)).setCardBackgroundColor(color);
  ((CardView)findViewById(R.id.donate_bitcoin_card)).setCardBackgroundColor(color);
  color=getIconColor();
  ((ThemedIcon)findViewById(R.id.donate_paypal_icon_title)).setColor(color);
  ((ThemedIcon)findViewById(R.id.donate_bitcoin_icon_title)).setColor(color);
  ((ThemedIcon)findViewById(R.id.donate_header_icon)).setColor(color);
  color=getTextColor();
  ((TextView)findViewById(R.id.donate_paypal_item)).setTextColor(color);
  ((TextView)findViewById(R.id.donate_bitcoin_item)).setTextColor(color);
  ((TextView)findViewById(R.id.donate_header_item)).setTextColor(color);
  setScrollViewColor(scr);
}
