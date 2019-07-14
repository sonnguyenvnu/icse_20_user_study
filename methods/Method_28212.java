@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  String repoId=getArguments().getString(BundleConstant.ID);
  String login=getArguments().getString(BundleConstant.EXTRA);
  if (login == null || repoId == null)   throw new NullPointerException("repoId || login is null???");
  pager.setAdapter(new FragmentsPagerAdapter(getChildFragmentManager(),FragmentPagerAdapterModel.buildForRepoIssue(getContext(),login,repoId)));
  tabs.setupWithViewPager(pager);
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
