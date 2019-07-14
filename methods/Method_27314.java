@NonNull public static String getReviewRequests(@NonNull String username,@NonNull IssueState issueState){
  return "type:pr" + "+" + "review-requested:" + username + "+is:" + issueState.name();
}
