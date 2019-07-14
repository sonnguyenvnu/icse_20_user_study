public static void showPopup(@NonNull FragmentManager manager,@NonNull Issue issue){
  IssuePopupFragment fragment=new IssuePopupFragment();
  PullsIssuesParser parser=PullsIssuesParser.getForIssue(issue.getHtmlUrl());
  if (parser == null) {
    parser=PullsIssuesParser.getForPullRequest(issue.getHtmlUrl());
  }
  if (parser == null)   return;
  fragment.setArguments(getBundle(parser.getLogin(),parser.getRepoId(),issue.getNumber(),issue.getTitle(),issue.getBody(),issue.getUser(),issue.getAssignee(),issue.getLabels(),issue.getMilestone(),!issue.isLocked()));
  fragment.show(manager,"");
}
