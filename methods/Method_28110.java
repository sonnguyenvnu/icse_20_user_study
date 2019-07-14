public static void showPopup(@NonNull FragmentManager manager,@NonNull PullRequest pullRequest){
  IssuePopupFragment fragment=new IssuePopupFragment();
  PullsIssuesParser parser=PullsIssuesParser.getForPullRequest(pullRequest.getHtmlUrl());
  if (parser == null)   return;
  fragment.setArguments(getBundle(parser.getLogin(),parser.getRepoId(),pullRequest.getNumber(),pullRequest.getTitle(),pullRequest.getBody(),pullRequest.getUser(),pullRequest.getAssignee(),pullRequest.getLabels(),pullRequest.getMilestone(),!pullRequest.isLocked()));
  fragment.show(manager,"");
}
