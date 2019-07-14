private void addTabSelectedListenerToTabLayout(){
  this.sortTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
    @Override public void onTabSelected(    final TabLayout.Tab tab){
    }
    @Override public void onTabUnselected(    final TabLayout.Tab tab){
    }
    @Override public void onTabReselected(    final TabLayout.Tab tab){
      DiscoveryActivity.this.pagerAdapter.scrollToTop(tab.getPosition());
    }
  }
);
}
