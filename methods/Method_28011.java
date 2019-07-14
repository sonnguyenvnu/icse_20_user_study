@Override public void onMenuClicked(int position,@NonNull RepoFile item,View v){
  if (login == null) {
    login=Login.getUser();
  }
  if (refresh.isRefreshing())   return;
  boolean isOwner=login.getLogin().equals(getPresenter().login) || (repoCallback != null && repoCallback.isCollaborator());
  PopupMenu popup=new PopupMenu(getContext(),v);
  MenuInflater inflater=popup.getMenuInflater();
  inflater.inflate(R.menu.download_share_menu,popup.getMenu());
  popup.getMenu().findItem(R.id.download).setVisible(item.getType() == FilesType.file);
  boolean canOpen=canOpen(item);
  popup.getMenu().findItem(R.id.editFile).setVisible(isOwner && item.getType() == FilesType.file && canOpen);
  popup.getMenu().findItem(R.id.deleteFile).setVisible(isOwner && item.getType() == FilesType.file);
  popup.setOnMenuItemClickListener(item1 -> {
switch (item1.getItemId()) {
case R.id.share:
      ActivityHelper.shareUrl(v.getContext(),item.getHtmlUrl());
    break;
case R.id.download:
  if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
    RestProvider.downloadFile(getContext(),item.getDownloadUrl());
  }
break;
case R.id.copy:
AppHelper.copyToClipboard(v.getContext(),!InputHelper.isEmpty(item.getHtmlUrl()) ? item.getHtmlUrl() : item.getUrl());
break;
case R.id.editFile:
if (PrefGetter.isProEnabled() || PrefGetter.isAllFeaturesUnlocked()) {
if (canOpen) {
EditRepoFileModel fileModel=new EditRepoFileModel(getPresenter().login,getPresenter().repoId,item.getPath(),getPresenter().ref,item.getSha(),item.getDownloadUrl(),item.getName(),true);
EditRepoFileActivity.Companion.startForResult(this,fileModel,isEnterprise());
}
}
 else {
PremiumActivity.Companion.startActivity(getContext());
}
break;
case R.id.deleteFile:
if (PrefGetter.isProEnabled() || PrefGetter.isAllFeaturesUnlocked()) {
DeleteFileBottomSheetFragment.Companion.newInstance(position,item.getName()).show(getChildFragmentManager(),DeleteFileBottomSheetFragment.class.getSimpleName());
}
 else {
PremiumActivity.Companion.startActivity(getContext());
}
break;
}
return true;
}
);
popup.show();
}
