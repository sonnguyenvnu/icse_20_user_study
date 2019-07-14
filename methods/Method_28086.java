@Override public void onSubmit(@Nullable String title,@Nullable String dueOn,@Nullable String description,@NonNull String login,@NonNull String repo){
  if (getView() != null) {
    boolean isEmptyTitle=InputHelper.isEmpty(title);
    getView().onShowTitleError(isEmptyTitle);
    if (!isEmptyTitle) {
      CreateMilestoneModel createMilestoneModel=new CreateMilestoneModel();
      createMilestoneModel.setTitle(title);
      if (!InputHelper.isEmpty(dueOn)) {
        Date date=ParseDateFormat.getDateFromString(dueOn);
        if (date != null)         createMilestoneModel.setDueOn(ParseDateFormat.toGithubDate(date));
      }
      if (!InputHelper.isEmpty(description)) {
        createMilestoneModel.setDescription(description);
      }
      makeRestCall(RestProvider.getRepoService(isEnterprise()).createMilestone(login,repo,createMilestoneModel),milestoneModel -> {
        if (milestoneModel != null) {
          sendToView(view -> view.onMilestoneAdded(milestoneModel));
        }
 else {
          sendToView(view -> view.showMessage(R.string.error,R.string.error_creating_milestone));
        }
      }
);
    }
  }
}
