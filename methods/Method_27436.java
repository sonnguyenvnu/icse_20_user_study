private void appendIssueEvent(SpannableBuilder spannableBuilder,Event eventsModel){
  Issue issue=eventsModel.getPayload().getIssue();
  boolean isLabel="label".equals(eventsModel.getPayload().getAction());
  LabelModel label=isLabel ? issue.getLabels() != null && !issue.getLabels().isEmpty() ? issue.getLabels().get(issue.getLabels().size() - 1) : null : null;
  spannableBuilder.bold(isLabel && label != null ? ("Labeled " + label.getName()) : eventsModel.getPayload().getAction()).append(" ").bold("issue").append(" ").append(eventsModel.getRepo().getName()).bold("#").bold(String.valueOf(issue.getNumber()));
  if (issue.getTitle() != null) {
    MarkDownProvider.stripMdText(description,issue.getTitle().replaceAll("\\r?\\n|\\r"," "));
    description.setVisibility(View.VISIBLE);
  }
 else {
    description.setText("");
    description.setVisibility(View.GONE);
  }
}
