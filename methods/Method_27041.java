@NonNull public static List<FragmentPagerAdapterModel> buildForIssues(@NonNull Context context,long commentId){
  return Stream.of(new FragmentPagerAdapterModel(context.getString(R.string.details),IssueTimelineFragment.newInstance(commentId))).collect(Collectors.toList());
}
