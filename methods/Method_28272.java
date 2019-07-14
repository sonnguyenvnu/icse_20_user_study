@Override public void onPutAssignees(@NonNull ArrayList<User> users,boolean isAssignees){
  AssigneesRequestModel assigneesRequestModel=new AssigneesRequestModel();
  ArrayList<String> assignees=Stream.of(users).map(User::getLogin).collect(Collectors.toCollection(ArrayList::new));
  if (isAssignees) {
    assigneesRequestModel.setAssignees(assignees.isEmpty() ? Stream.of(pullRequest.getAssignees()).map(User::getLogin).toList() : assignees);
    makeRestCall(!assignees.isEmpty() ? RestProvider.getIssueService(isEnterprise()).putAssignees(login,repoId,issueNumber,assigneesRequestModel) : RestProvider.getIssueService(isEnterprise()).deleteAssignees(login,repoId,issueNumber,assigneesRequestModel),pullRequestResponse -> {
      UsersListModel usersListModel=new UsersListModel();
      usersListModel.addAll(users);
      this.pullRequest.setAssignees(usersListModel);
      manageObservable(pullRequest.save(pullRequest).toObservable());
      sendToView(view -> updateTimeline(view,R.string.assignee_added));
    }
);
  }
 else {
    assigneesRequestModel.setReviewers(assignees);
    makeRestCall(RestProvider.getPullRequestService(isEnterprise()).putReviewers(login,repoId,issueNumber,assigneesRequestModel),pullRequestResponse -> sendToView(view -> updateTimeline(view,R.string.reviewer_added)));
  }
}
