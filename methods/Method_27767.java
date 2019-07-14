private void setupTabs(){
  pager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForNotifications(this)));
  tabs.setupWithViewPager(pager);
}
