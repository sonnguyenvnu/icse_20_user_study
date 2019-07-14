@NonNull public static List<FragmentPagerAdapterModel> buildForCommit(@NonNull Context context,@NonNull Commit commitModel){
  String login=commitModel.getLogin();
  String repoId=commitModel.getRepoId();
  String sha=commitModel.getSha();
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.files),CommitFilesFragment.newInstance(commitModel.getSha(),commitModel.getFiles())),new FragmentPagerAdapterModel(context.getString(R.string.comments),CommitCommentsFragment.newInstance(login,repoId,sha))).collect(Collectors.toList());
}
