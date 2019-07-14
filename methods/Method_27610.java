@Override public void onSubmitUpdate(@NonNull String id,@NonNull String description,@NonNull HashMap<String,FilesListModel> files){
  boolean isEmptyDesc=InputHelper.isEmpty(description);
  if (getView() != null) {
    getView().onDescriptionError(isEmptyDesc);
  }
  if (isEmptyDesc)   return;
  CreateGistModel createGistModel=new CreateGistModel();
  createGistModel.setDescription(InputHelper.toString(description));
  createGistModel.setFiles(files);
  makeRestCall(RestProvider.getGistService(isEnterprise()).editGist(createGistModel,id),gistsModel -> sendToView(view -> view.onSuccessSubmission(gistsModel)),false);
}
