@Override public void onBranchSelected(@NonNull BranchesModel branch){
  String ref=branch.getName();
  branches.setText(ref);
  getPresenter().onBranchChanged(ref);
}
