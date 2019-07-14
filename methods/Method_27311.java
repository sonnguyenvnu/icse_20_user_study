@NonNull public static String getMyIssuesPullRequestQuery(@NonNull String username,@NonNull IssueState issueState,boolean isPr){
  return "type:" + (isPr ? "pr" : "issue") + "+" + "author:" + username + "+is:" + issueState.name();
}
