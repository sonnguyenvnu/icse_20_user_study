private void setupTabs(){
  pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForGists(this)));
  tabs.setupWithViewPager(pager);
}
