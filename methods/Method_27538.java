public void onCheckGitHubStatus(){
  manageObservable(RestProvider.gitHubStatus().doOnNext(gitHubStatusModel -> {
    if (!"good".equalsIgnoreCase(gitHubStatusModel.getStatus())) {
      sendToView(v -> v.showErrorMessage("Github Status:\n" + gitHubStatusModel.getBody()));
    }
  }
));
}
