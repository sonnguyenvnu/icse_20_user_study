@NonNull public static String getIssuesPullRequestQuery(@NonNull String owner,@NonNull String repo,@NonNull IssueState issueState,boolean isPr){
  return "+" + "type:" + (isPr ? "pr" : "issue") + "+" + "repo:" + owner + "/" + repo + "+" + "is:" + issueState.name();
}
