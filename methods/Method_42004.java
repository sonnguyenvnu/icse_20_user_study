private void initUi(){
  setSupportActionBar(toolbar);
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
}
