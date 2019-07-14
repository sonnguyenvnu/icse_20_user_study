private void appendIssueCommentEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  Comment comment=eventsModel.getPayload().getComment();
  Issue issue=eventsModel.getPayload().getIssue();
  spannableBuilder.bold("commented").append(" ").bold("on").append(" ").bold(issue.getPullRequest() != null ? "pull request" : "issue").append(" ").append(eventsModel.getRepo().getName()).bold("#").bold(String.valueOf(issue.getNumber()));
  if (comment.getBody() != null) {
    MarkDownProvider.stripMdText(description,comment.getBody().replaceAll("\\r?\\n|\\r"," "));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setText("");
    description.setVisibility(View.GONE);
  }
}
