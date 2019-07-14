@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (bundle != null) {
    repoId=bundle.getString(BundleConstant.ID);
    login=bundle.getString(BundleConstant.EXTRA);
    path=Objects.toString(bundle.getString(BundleConstant.EXTRA_TWO),"");
    defaultBranch=Objects.toString(bundle.getString(BundleConstant.EXTRA_THREE),"master");
    boolean forceAppend=bundle.getBoolean(BundleConstant.EXTRA_FOUR);
    if (InputHelper.isEmpty(repoId) || InputHelper.isEmpty(login)) {
      throw new NullPointerException(String.format("error, repoId(%s) or login(%s) is null",repoId,login));
    }
    if (forceAppend && paths.isEmpty()) {
      List<RepoFile> repoFiles=new ArrayList<>();
      if (!InputHelper.isEmpty(path)) {
        Uri uri=Uri.parse(path);
        StringBuilder builder=new StringBuilder();
        if (uri.getPathSegments() != null && !uri.getPathSegments().isEmpty()) {
          List<String> pathSegments=uri.getPathSegments();
          for (int i=0; i < pathSegments.size(); i++) {
            String name=pathSegments.get(i);
            RepoFile file=new RepoFile();
            if (i == 0) {
              builder.append(name);
            }
 else {
              builder.append("/").append(name);
            }
            file.setPath(builder.toString());
            file.setName(name);
            repoFiles.add(file);
          }
        }
        if (!repoFiles.isEmpty()) {
          sendToView(view -> view.onNotifyAdapter(repoFiles,1));
        }
      }
    }
    sendToView(RepoFilePathMvp.View::onSendData);
  }
 else {
    throw new NullPointerException("Bundle is null");
  }
}
