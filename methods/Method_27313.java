@NonNull public static String getMentioned(@NonNull String username,@NonNull IssueState issueState,boolean isPr){
  return "type:" + (isPr ? "pr" : "issue") + "+" + "mentions:" + username + "+is:" + issueState.name();
}
