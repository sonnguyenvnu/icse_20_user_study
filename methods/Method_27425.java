private static void appendReviews(@NonNull GenericEvent issueEventModel,@NonNull IssueEventType event,@NonNull SpannableBuilder spannableBuilder,@NonNull String from,@NonNull User user){
  spannableBuilder.append(" ");
  User reviewer=issueEventModel.getRequestedReviewer();
  if (reviewer != null && user.getLogin().equalsIgnoreCase(reviewer.getLogin())) {
    spannableBuilder.append(event == IssueEventType.review_requested ? "self-requested a review" : "removed their request for review");
  }
 else {
    spannableBuilder.append(event == IssueEventType.review_requested ? "Requested a review" : "dismissed the review").append(" ").append(reviewer != null && !reviewer.getLogin().equalsIgnoreCase(user.getLogin()) ? from : " ").append(reviewer != null && !reviewer.getLogin().equalsIgnoreCase(user.getLogin()) ? " " : "");
  }
  if (issueEventModel.getRequestedTeam() != null) {
    String name=!InputHelper.isEmpty(issueEventModel.getRequestedTeam().getName()) ? issueEventModel.getRequestedTeam().getName() : issueEventModel.getRequestedTeam().getSlug();
    spannableBuilder.bold(name).append(" ").append("team");
  }
 else   if (reviewer != null && !user.getLogin().equalsIgnoreCase(reviewer.getLogin())) {
    spannableBuilder.bold(issueEventModel.getRequestedReviewer().getLogin());
  }
}
