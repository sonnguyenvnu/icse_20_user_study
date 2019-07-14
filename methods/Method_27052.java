@NonNull public static List<FragmentPagerAdapterModel> buildForTeam(@NonNull Context context,long id){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.members),TeamMembersFragment.newInstance(id)),new FragmentPagerAdapterModel(context.getString(R.string.repos),TeamReposFragment.newInstance(id))).collect(Collectors.toList());
}
