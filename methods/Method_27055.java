@NonNull public static List<FragmentPagerAdapterModel> buildForRepoProjects(@NonNull Context context,@Nullable String repoId,@NonNull String login){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.open),RepoProjectFragment.Companion.newInstance(login,repoId,IssueState.open)),new FragmentPagerAdapterModel(context.getString(R.string.closed),RepoProjectFragment.Companion.newInstance(login,repoId,IssueState.closed))).toList();
}
