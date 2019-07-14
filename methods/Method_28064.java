@Override public void onCallApi(@NonNull String login,@NonNull String repo,boolean isAssignees){
  makeRestCall(isAssignees ? RestProvider.getRepoService(isEnterprise()).getAssignees(login,repo) : RestProvider.getRepoService(isEnterprise()).getCollaborator(login,repo),response -> sendToView(view -> view.onNotifyAdapter(response != null ? response.getItems() : null)));
}
