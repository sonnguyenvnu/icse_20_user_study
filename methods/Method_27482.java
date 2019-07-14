public void bind(@NonNull SearchCodeModel codeModel,boolean showRepoName){
  if (showRepoName) {
    title.setText(codeModel.getRepository() != null ? codeModel.getRepository().getFullName() : "N/A");
    details.setText(codeModel.getName());
    commentsNo.setVisibility(View.GONE);
  }
 else {
    title.setText(codeModel.getName());
    details.setText(codeModel.getPath());
    commentsNo.setVisibility(View.GONE);
  }
}
