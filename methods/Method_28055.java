@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getArguments() != null) {
    String repoId=getArguments().getString(BundleConstant.ID);
    String login=getArguments().getString(BundleConstant.EXTRA);
    String url=getArguments().getString(BundleConstant.EXTRA_TWO);
    String htmlUrl=getArguments().getString(BundleConstant.EXTRA_FOUR);
    String defaultBranch=getArguments().getString(BundleConstant.EXTRA_THREE);
    if (InputHelper.isEmpty(repoId) || InputHelper.isEmpty(login) || InputHelper.isEmpty(url) || InputHelper.isEmpty(htmlUrl)) {
      return;
    }
    pager.setAdapter(new FragmentsPagerAdapter(getChildFragmentManager(),FragmentPagerAdapterModel.buildForRepoCode(getContext(),repoId,login,url,Objects.toString(defaultBranch,"master"),htmlUrl)));
    tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    tabs.setupWithViewPager(pager);
  }
  if (savedInstanceState != null && !counts.isEmpty()) {
    Stream.of(counts).forEach(this::updateCount);
  }
  tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager){
    @Override public void onTabReselected(    TabLayout.Tab tab){
      super.onTabReselected(tab);
      onScrollTop(tab.getPosition());
    }
  }
);
}
