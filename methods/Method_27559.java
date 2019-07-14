@Override public void onOpenRepoChooser(@NonNull ArrayList<SimpleUrlsModel> models){
  ListDialogView<SimpleUrlsModel> dialogView=new ListDialogView<>();
  dialogView.initArguments(getString(R.string.repo_chooser),models);
  dialogView.show(getChildFragmentManager(),"ListDialogView");
}
