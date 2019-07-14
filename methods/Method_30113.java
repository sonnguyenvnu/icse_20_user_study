@Override public void onActivityCreated(@Nullable Bundle savedInstanceState){
  super.onActivityCreated(savedInstanceState);
  AppCompatActivity activity=(AppCompatActivity)getActivity();
  activity.setSupportActionBar(mToolbar);
  mSystemUiHelper=new SystemUiHelper(activity,SystemUiHelper.LEVEL_IMMERSIVE,SystemUiHelper.FLAG_IMMERSIVE_STICKY,visible -> {
    if (visible) {
      mToolbar.animate().alpha(1).translationY(0).setDuration(mToolbarHideDuration).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
 else {
      mToolbar.animate().alpha(0).translationY(-mToolbar.getBottom()).setDuration(mToolbarHideDuration).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
  }
);
  mSystemUiHelper.show();
  mAdapter=new GalleryAdapter(mImageList,new GalleryAdapter.Listener(){
    @Override public void onTap(){
      mSystemUiHelper.toggle();
    }
    @Override public void onFileDownloaded(    int position){
      if (position == mViewPager.getCurrentItem()) {
        updateOptionsMenu();
      }
    }
  }
);
  mViewPager.setAdapter(mAdapter);
  mViewPager.setCurrentItem(mInitialPosition);
  mViewPager.setPageTransformer(true,ViewPagerTransformers.DEPTH);
  mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageSelected(    int position){
      updateTitle();
      updateOptionsMenu();
    }
  }
);
  updateTitle();
}
