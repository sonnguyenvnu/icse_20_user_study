@NonNull public static List<FragmentPagerAdapterModel> buildForPullRequest(@NonNull Context context,@NonNull PullRequest pullRequest){
  String login=pullRequest.getLogin();
  String repoId=pullRequest.getRepoId();
  int number=pullRequest.getNumber();
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.details),PullRequestTimelineFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.commits),PullRequestCommitsFragment.newInstance(repoId,login,number)),new FragmentPagerAdapterModel(context.getString(R.string.files),PullRequestFilesFragment.newInstance(repoId,login,number))).collect(Collectors.toList());
}
