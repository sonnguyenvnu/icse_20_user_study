@Override public void onPutAssignees(@NonNull ArrayList<User> users){
  AssigneesRequestModel assigneesRequestModel=new AssigneesRequestModel();
  ArrayList<String> assignees=new ArrayList<>();
  Stream.of(users).forEach(userModel -> assignees.add(userModel.getLogin()));
  assigneesRequestModel.setAssignees(assignees.isEmpty() ? Stream.of(issueModel.getAssignees()).map(User::getLogin).toList() : assignees);
  makeRestCall(!assignees.isEmpty() ? RestProvider.getIssueService(isEnterprise()).putAssignees(login,repoId,issueNumber,assigneesRequestModel) : RestProvider.getIssueService(isEnterprise()).deleteAssignees(login,repoId,issueNumber,assigneesRequestModel),issue -> {
    UsersListModel assignee=new UsersListModel();
    assignee.addAll(users);
    issueModel.setAssignees(assignee);
    manageObservable(issueModel.save(issueModel).toObservable());
    sendToView(view -> updateTimeline(view,R.string.assignee_added));
  }
);
}
