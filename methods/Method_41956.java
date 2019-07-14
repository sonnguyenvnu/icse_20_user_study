private void initUi(){
  setSupportActionBar(toolbar);
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(v -> onBackPressed());
  mRecyclerView.setHasFixedSize(true);
  mRecyclerView.setAdapter((adapter=new ItemsAdapter()));
  mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
  mRecyclerView.setItemAnimator(AnimationUtils.getItemAnimator(new LandingAnimator(new OvershootInterpolator(1f))));
}
