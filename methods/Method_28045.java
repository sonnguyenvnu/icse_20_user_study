@Override public void onDownload(@NonNull Release item){
  ArrayList<SimpleUrlsModel> models=new ArrayList<>();
  if (!InputHelper.isEmpty(item.getZipBallUrl())) {
    String url=item.getZipBallUrl();
    if (!url.endsWith(".tar.gz")) {
      url=url + ".tar.gz";
    }
    models.add(new SimpleUrlsModel(getString(R.string.download_as_zip),url));
  }
  if (!InputHelper.isEmpty(item.getTarballUrl())) {
    models.add(new SimpleUrlsModel(getString(R.string.download_as_tar),item.getTarballUrl()));
  }
  if (item.getAssets() != null && !item.getAssets().isEmpty()) {
    ArrayList<SimpleUrlsModel> mapped=Stream.of(item.getAssets()).filter(value -> value != null && value.getBrowserDownloadUrl() != null).map(assetsModel -> new SimpleUrlsModel(String.format("%s (%s)",assetsModel.getName(),assetsModel.getDownloadCount()),assetsModel.getBrowserDownloadUrl())).collect(Collectors.toCollection(ArrayList::new));
    if (mapped != null && !mapped.isEmpty()) {
      models.addAll(mapped);
    }
  }
  ListDialogView<SimpleUrlsModel> dialogView=new ListDialogView<>();
  dialogView.initArguments(getString(R.string.releases),models);
  dialogView.show(getChildFragmentManager(),"ListDialogView");
}
