private void initContentFragment(){
  ArrayList<Fragment> mFragmentList=new ArrayList<>();
  mFragmentList.add(new WanFragment());
  mFragmentList.add(new GankFragment());
  mFragmentList.add(new FilmFragment());
  MyFragmentPagerAdapter adapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList);
  vpContent.setAdapter(adapter);
  vpContent.setOffscreenPageLimit(2);
  vpContent.addOnPageChangeListener(this);
  setSupportActionBar(toolbar);
  ActionBar actionBar=getSupportActionBar();
  if (actionBar != null) {
    actionBar.setDisplayShowTitleEnabled(false);
  }
  setCurrentItem(0);
}
