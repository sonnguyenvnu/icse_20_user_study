@NonNull public static List<FragmentPagerAdapterModel> buildForRepoPullRequest(@NonNull Context context,@NonNull String login,@NonNull String repoId){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.opened),RepoPullRequestFragment.newInstance(repoId,login,IssueState.open)),new FragmentPagerAdapterModel(context.getString(R.string.closed),RepoPullRequestFragment.newInstance(repoId,login,IssueState.closed))).collect(Collectors.toList());
}
