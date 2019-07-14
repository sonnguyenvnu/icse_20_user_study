@NonNull public static List<FragmentPagerAdapterModel> buildForRepoIssue(@NonNull Context context,@NonNull String login,@NonNull String repoId){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.opened),RepoOpenedIssuesFragment.newInstance(repoId,login)),new FragmentPagerAdapterModel(context.getString(R.string.closed),RepoClosedIssuesFragment.newInstance(repoId,login))).collect(Collectors.toList());
}
