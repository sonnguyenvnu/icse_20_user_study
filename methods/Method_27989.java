@OnClick(R.id.addFile) void onAddFile(){
  if (PrefGetter.isProEnabled() || PrefGetter.isAllFeaturesUnlocked()) {
    RepoFile repoFile=!adapter.isEmpty() ? adapter.getItem(adapter.getItemCount() - 1) : null;
    EditRepoFileModel fileModel=new EditRepoFileModel(getPresenter().login,getPresenter().repoId,repoFile != null ? repoFile.getPath() : "",ref,repoFile != null ? repoFile.getSha() : "",null,null,false);
    EditRepoFileActivity.Companion.startForResult(this,fileModel,isEnterprise());
  }
 else {
    PremiumActivity.Companion.startActivity(getContext());
  }
}
