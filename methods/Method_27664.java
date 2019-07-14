@Override public void callApi(){
  if (!InputHelper.isEmpty(gistId)) {
    checkStarring(gistId);
    makeRestCall(RestProvider.getGistService(isEnterprise()).getGist(gistId),gistsModel -> {
      this.gist=gistsModel;
      sendToView(GistMvp.View::onSetupDetails);
    }
);
  }
}
