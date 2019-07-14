@OnClick(R.id.detailsIcon) void onTitleClick(){
  Repo repoModel=getPresenter().getRepo();
  if (repoModel != null && !InputHelper.isEmpty(repoModel.getDescription())) {
    MessageDialogView.newInstance(repoModel.getFullName(),repoModel.getDescription(),false,true).show(getSupportFragmentManager(),MessageDialogView.TAG);
  }
}
