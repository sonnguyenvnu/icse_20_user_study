private static @NonNull List<DiscoveryFragment> createFragments(final int pages){
  final List<DiscoveryFragment> fragments=new ArrayList<>(pages);
  for (int position=0; position <= pages; position++) {
    fragments.add(DiscoveryFragment.newInstance(position));
  }
  return fragments;
}
