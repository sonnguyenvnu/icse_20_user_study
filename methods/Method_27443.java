private void appendCommitComment(SpannableBuilder spannableBuilder,Event eventsModel){
  Comment comment=eventsModel.getPayload().getCommitComment() == null ? eventsModel.getPayload().getComment() : eventsModel.getPayload().getCommitComment();
  String commitId=comment != null && comment.getCommitId() != null && comment.getCommitId().length() > 10 ? comment.getCommitId().substring(0,10) : null;
  spannableBuilder.bold("commented").append(" ").bold("on").append(" ").bold("commit").append(" ").append(eventsModel.getRepo().getName()).url(commitId != null ? "@" + commitId : "");
  if (comment != null && comment.getBody() != null) {
    MarkDownProvider.stripMdText(description,comment.getBody().replaceAll("\\r?\\n|\\r"," "));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setText("");
    description.setVisibility(View.GONE);
  }
}
