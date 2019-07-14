@Override public void onInitOrg(boolean isMember){
  hideProgress();
  FragmentsPagerAdapter adapter=new FragmentsPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapterModel.buildForOrg(this,login,isMember));
  pager.setAdapter(adapter);
  tabs.setTabGravity(TabLayout.GRAVITY_FILL);
  tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
  tabs.setupWithViewPager(pager);
  setTaskName(login);
}
