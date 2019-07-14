private void initUi(){
  toolbar=findViewById(R.id.toolbar);
  setSupportActionBar(toolbar);
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(v -> onBackPressed());
  getSupportActionBar().setTitle(StringUtils.getName(getIntent().getData().getPath()));
}
