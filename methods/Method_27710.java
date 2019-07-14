@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  FragmentsPagerAdapter adapter=new FragmentsPagerAdapter(getChildFragmentManager(),FragmentPagerAdapterModel.buildForMyIssues(getContext()));
  pager.setAdapter(adapter);
  tabs.setTabsFromPagerAdapter(adapter);
  tabs.setTabGravity(TabLayout.GRAVITY_FILL);
  tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
  if (savedInstanceState == null) {
    tabs.getTabAt(0).select();
  }
  pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageSelected(    int position){
      super.onPageSelected(position);
      selectTab(position,true);
    }
  }
);
  tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
    @Override public void onTabSelected(    TabLayout.Tab tab){
      if (tab.getTag() == null) {
        int position=tab.getPosition();
        selectTab(position,false);
      }
      tab.setTag(null);
    }
    @Override public void onTabUnselected(    TabLayout.Tab tab){
    }
    @Override public void onTabReselected(    TabLayout.Tab tab){
      selectTab(tab.getPosition(),false);
    }
  }
);
  if (savedInstanceState != null && !counts.isEmpty()) {
    Stream.of(counts).forEach(this::updateCount);
  }
}
