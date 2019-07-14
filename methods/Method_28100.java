@Override public void onLoadMilestones(@NonNull String login,@NonNull String repo){
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getMilestones(login,repo),response -> {
    if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
      sendToView(view -> view.showMessage(R.string.error,R.string.no_milestones));
      return;
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems()));
  }
);
}
