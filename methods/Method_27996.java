@Override public void onAppendPath(@NonNull RepoFile model){
  getRepoFilesView().onSetData(getPresenter().getLogin(),getPresenter().getRepoId(),Objects.toString(model.getPath(),""),ref,false,model);
}
