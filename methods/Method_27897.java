@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getArguments() == null) {
    throw new RuntimeException("Bundle is null?");
  }
  String login=getArguments().getString(BundleConstant.EXTRA);
  if (login == null) {
    throw new RuntimeException("user is null?");
  }
  FragmentsPagerAdapter adapter=new FragmentsPagerAdapter(getChildFragmentManager(),FragmentPagerAdapterModel.buildForProfile(getContext(),login));
  tabs.setTabGravity(TabLayout.GRAVITY_FILL);
  tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
  pager.setAdapter(adapter);
  tabs.setupWithViewPager(pager);
}
