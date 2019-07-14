@CallSuper @Override public void updateUiElements(){
  super.updateUiElements();
  toolbar.setBackgroundColor(getPrimaryColor());
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
  setStatusBarColor();
  setNavBarColor();
  setRecentApp(getString(R.string.palette));
  findViewById(R.id.palette_background).setBackgroundColor(getBackgroundColor());
  ((CardView)findViewById(R.id.palette_colors_card)).setCardBackgroundColor(getCardBackgroundColor());
  ((CardView)findViewById(R.id.palette_image_card)).setCardBackgroundColor(getCardBackgroundColor());
  ((TextView)findViewById(R.id.palette_image_title)).setTextColor(getTextColor());
  ((TextView)findViewById(R.id.palette_image_caption)).setTextColor(getSubTextColor());
}
