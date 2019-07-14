@Override public void onCallApi(@Nullable RepoFile toAppend){
  if (repoId == null || login == null)   return;
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getRepoFiles(login,repoId,path,ref).flatMap(response -> {
    if (response != null && response.getItems() != null) {
      return Observable.fromIterable(response.getItems()).filter(repoFile -> repoFile.getType() != null).sorted((repoFile,repoFile2) -> repoFile2.getType().compareTo(repoFile.getType()));
    }
    return Observable.empty();
  }
).toList().toObservable(),response -> {
    files.clear();
    if (response != null) {
      manageObservable(RepoFile.save(response,login,repoId));
      pathsModel.setFiles(ref,path,response);
      files.addAll(response);
    }
    sendToView(view -> {
      view.onNotifyAdapter();
      view.onUpdateTab(toAppend);
    }
);
  }
);
}
