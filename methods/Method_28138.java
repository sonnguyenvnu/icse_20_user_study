@Override public void onSubmit(@NonNull String title,@NonNull CharSequence description,@NonNull String login,@NonNull String repo,@Nullable Issue issue,@Nullable PullRequest pullRequestModel,@Nullable ArrayList<LabelModel> labels,@Nullable MilestoneModel milestoneModel,@Nullable ArrayList<User> users){
  boolean isEmptyTitle=InputHelper.isEmpty(title);
  if (getView() != null) {
    getView().onTitleError(isEmptyTitle);
  }
  if (!isEmptyTitle) {
    if (issue == null && pullRequestModel == null) {
      CreateIssueModel createIssue=new CreateIssueModel();
      createIssue.setBody(InputHelper.toString(description));
      createIssue.setTitle(title);
      if (isCollaborator) {
        if (labels != null && !labels.isEmpty()) {
          createIssue.setLabels(Stream.of(labels).map(LabelModel::getName).collect(Collectors.toCollection(ArrayList::new)));
        }
        if (users != null && !users.isEmpty()) {
          createIssue.setAssignees(Stream.of(users).map(User::getLogin).collect(Collectors.toCollection(ArrayList::new)));
        }
        if (milestoneModel != null) {
          createIssue.setMilestone((long)milestoneModel.getNumber());
        }
      }
      makeRestCall(RestProvider.getIssueService(isEnterprise()).createIssue(login,repo,createIssue),issueModel -> {
        if (issueModel != null) {
          sendToView(view -> view.onSuccessSubmission(issueModel));
        }
 else {
          sendToView(view -> view.showMessage(R.string.error,R.string.error_creating_issue));
        }
      }
,false);
    }
 else {
      if (issue != null) {
        issue.setBody(InputHelper.toString(description));
        issue.setTitle(title);
        int number=issue.getNumber();
        if (isCollaborator) {
          if (labels != null) {
            LabelListModel labelModels=new LabelListModel();
            labelModels.addAll(labels);
            issue.setLabels(labelModels);
          }
          if (milestoneModel != null) {
            issue.setMilestone(milestoneModel);
          }
          if (users != null) {
            UsersListModel usersListModel=new UsersListModel();
            usersListModel.addAll(users);
            issue.setAssignees(usersListModel);
          }
        }
        IssueRequestModel requestModel=IssueRequestModel.clone(issue,false);
        makeRestCall(RestProvider.getIssueService(isEnterprise()).editIssue(login,repo,number,requestModel),issueModel -> {
          if (issueModel != null) {
            sendToView(view -> view.onSuccessSubmission(issueModel));
          }
 else {
            sendToView(view -> view.showMessage(R.string.error,R.string.error_creating_issue));
          }
        }
,false);
      }
      if (pullRequestModel != null) {
        int number=pullRequestModel.getNumber();
        pullRequestModel.setBody(InputHelper.toString(description));
        pullRequestModel.setTitle(title);
        if (isCollaborator) {
          if (labels != null) {
            LabelListModel labelModels=new LabelListModel();
            labelModels.addAll(labels);
            pullRequestModel.setLabels(labelModels);
          }
          if (milestoneModel != null) {
            pullRequestModel.setMilestone(milestoneModel);
          }
          if (users != null) {
            UsersListModel usersListModel=new UsersListModel();
            usersListModel.addAll(users);
            pullRequestModel.setAssignees(usersListModel);
          }
        }
        IssueRequestModel requestModel=IssueRequestModel.clone(pullRequestModel,false);
        makeRestCall(RestProvider.getPullRequestService(isEnterprise()).editPullRequest(login,repo,number,requestModel).flatMap(pullRequest1 -> RestProvider.getIssueService(isEnterprise()).getIssue(login,repo,number),(pullRequest1,issueReaction) -> {
          if (issueReaction != null) {
            pullRequest1.setReactions(issueReaction.getReactions());
          }
          return pullRequest1;
        }
),pr -> {
          if (pr != null) {
            sendToView(view -> view.onSuccessSubmission(pr));
          }
 else {
            sendToView(view -> view.showMessage(R.string.error,R.string.error_creating_issue));
          }
        }
,false);
      }
    }
  }
}
