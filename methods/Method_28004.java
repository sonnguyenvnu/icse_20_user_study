@Override public void onBranchSelected(@NonNull BranchesModel branch){
  ref=branch.getName();
  branches.setText(ref);
  getRepoFilesView().onSetData(getPresenter().getLogin(),getPresenter().getRepoId(),"",ref,true,null);
  onBackClicked();
}
