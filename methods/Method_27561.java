@Override public void onOpenCommitChooser(@NonNull List<GitCommitModel> commits){
  ListDialogView<GitCommitModel> dialogView=new ListDialogView<>();
  dialogView.initArguments(getString(R.string.commits),commits);
  dialogView.show(getChildFragmentManager(),"ListDialogView");
}
