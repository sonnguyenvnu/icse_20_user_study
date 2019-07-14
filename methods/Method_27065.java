public static IssueRequestModel clone(@NonNull Issue issue,boolean toClose){
  IssueRequestModel model=new IssueRequestModel();
  if (issue.getLabels() != null) {
    model.setLabels(Stream.of(issue.getLabels()).filter(value -> value.getName() != null).map(LabelModel::getName).collect(Collectors.toList()));
  }
  model.setAssignee(issue.getAssignee() != null ? issue.getAssignee().getLogin() : null);
  model.setBody(issue.getBody());
  model.setMilestone(issue.getMilestone() != null ? issue.getMilestone().getNumber() : null);
  model.setState(toClose ? issue.getState() == IssueState.closed ? IssueState.open : IssueState.closed : issue.getState());
  model.setTitle(issue.getTitle());
  return model;
}
