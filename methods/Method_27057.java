@NonNull public static List<FragmentPagerAdapterModel> buildForPinned(@NonNull Context context){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.repos),PinnedReposFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.issues),PinnedIssueFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.pull_requests),PinnedPullRequestFragment.newInstance()),new FragmentPagerAdapterModel(context.getString(R.string.gists),PinnedGistFragment.newInstance())).collect(Collectors.toList());
}
