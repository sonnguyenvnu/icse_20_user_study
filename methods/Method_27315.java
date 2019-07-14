public static String getParticipated(@NonNull String username,@NonNull IssueState issueState,boolean isPr){
  return "type:" + (isPr ? "pr" : "issue") + "+" + "involves:" + username + "+is:" + issueState.name();
}
