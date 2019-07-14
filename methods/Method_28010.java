@Override public void onItemClicked(@NonNull RepoFile model){
  if (refresh.isRefreshing())   return;
  if (model.getType() == FilesType.dir) {
    if (getParent() != null) {
      getParent().onAppendPath(model);
    }
  }
 else {
    if (model.getSize() == 0 && InputHelper.isEmpty(model.getDownloadUrl()) && !InputHelper.isEmpty(model.getGitUrl())) {
      RepoFilesActivity.startActivity(getContext(),model.getGitUrl().replace("trees/",""),isEnterprise());
    }
 else {
      String url=InputHelper.isEmpty(model.getDownloadUrl()) ? model.getUrl() : model.getDownloadUrl();
      if (InputHelper.isEmpty(url))       return;
      if (model.getSize() > FileHelper.ONE_MB && !MarkDownProvider.isImage(url)) {
        MessageDialogView.newInstance(getString(R.string.big_file),getString(R.string.big_file_description),false,true,Bundler.start().put(BundleConstant.EXTRA,model.getDownloadUrl()).put(BundleConstant.YES_NO_EXTRA,true).end()).show(getChildFragmentManager(),"MessageDialogView");
      }
 else {
        CodeViewerActivity.startActivity(getContext(),url,model.getHtmlUrl());
      }
    }
  }
}
