@OnClick(R.id.submit) public void onClick(){
  getPresenter().onSubmit(InputHelper.toString(title),savedText,login,repoId,issue,pullRequest,labelModels,milestoneModel,users);
}
