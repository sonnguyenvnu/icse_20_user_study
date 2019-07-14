@Override public void onSubmit(@NonNull CreateGistModel model){
  makeRestCall(RestProvider.getGistService(isEnterprise()).createGist(model),gistsModel -> sendToView(view -> view.onSuccessSubmission(gistsModel)),false);
}
