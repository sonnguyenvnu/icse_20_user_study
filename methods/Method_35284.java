@Override protected void onViewBound(@NonNull View view){
  super.onViewBound(view);
  viewPager.setAdapter(pagerAdapter);
  tabLayout.setupWithViewPager(viewPager);
}
