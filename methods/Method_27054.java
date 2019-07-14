@NonNull public static List<FragmentPagerAdapterModel> buildForBranches(@NonNull Context context,@NonNull String repoId,@NonNull String login){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.branches),BranchesFragment.Companion.newInstance(login,repoId,true)),new FragmentPagerAdapterModel(context.getString(R.string.tags),BranchesFragment.Companion.newInstance(login,repoId,false))).toList();
}
