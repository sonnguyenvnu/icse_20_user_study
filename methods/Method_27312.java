@NonNull public static String getAssigned(@NonNull String username,@NonNull IssueState issueState,boolean isPr){
  return "type:" + (isPr ? "pr" : "issue") + "+" + "assignee:" + username + "+is:" + issueState.name();
}
