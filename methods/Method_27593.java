@Override public void onStart(@NonNull String login,@NonNull String repoId){
  Observable<Pageable<MilestoneModel>> observable=RestProvider.getRepoService(isEnterprise()).getLabels(login,repoId).flatMap(labelModelPageable -> {
    if (labelModelPageable != null && labelModelPageable.getItems() != null) {
      labels.clear();
      labels.addAll(labelModelPageable.getItems());
    }
    return RestProvider.getRepoService(isEnterprise()).getAssignees(login,repoId);
  }
).flatMap(userPageable -> {
    if (userPageable != null && userPageable.getItems() != null) {
      assignees.clear();
      assignees.addAll(userPageable.getItems());
    }
    return RestProvider.getRepoService(isEnterprise()).getMilestones(login,repoId);
  }
);
  makeRestCall(observable,response -> {
    if (response != null && response.getItems() != null) {
      milestones.clear();
      milestones.addAll(response.getItems());
    }
    sendToView(BaseMvp.FAView::hideProgress);
  }
);
}
