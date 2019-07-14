private void initUi(){
  setSupportActionBar(toolbar);
  toolbar.bringToFront();
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(v -> onBackPressed());
  setupSystemUI();
  getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)     showSystemUI();
 else     hideSystemUI();
  }
);
  updatePageTitle(position);
  mViewPager.setAdapter(adapter);
  mViewPager.setCurrentItem(position);
  useImageMenu=isCurrentMediaImage();
  mViewPager.setPageTransformer(true,AnimationUtils.getPageTransformer(new DepthPageTransformer()));
  mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
    @Override public void onPageScrolled(    int position,    float positionOffset,    int positionOffsetPixels){
    }
    @Override public void onPageSelected(    int position){
      SingleMediaActivity.this.position=position;
      updatePageTitle(position);
      if (isCurrentMediaImage() == useImageMenu)       return;
      supportInvalidateOptionsMenu();
    }
    @Override public void onPageScrollStateChanged(    int state){
    }
  }
);
  if (((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getRotation() == Surface.ROTATION_90) {
    Configuration configuration=new Configuration();
    configuration.orientation=Configuration.ORIENTATION_LANDSCAPE;
    onConfigurationChanged(configuration);
  }
}
