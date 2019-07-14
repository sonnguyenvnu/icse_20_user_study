@Override public void onSubmit(@NonNull String description,@NonNull HashMap<String,FilesListModel> files,boolean isPublic){
  if (files.isEmpty())   return;
  CreateGistModel createGistModel=new CreateGistModel();
  createGistModel.setDescription(InputHelper.toString(description));
  createGistModel.setPublicGist(isPublic);
  createGistModel.setFiles(files);
  onSubmit(createGistModel);
}
