@Override public void onLoadContentAsStream(){
  boolean isImage=MarkDownProvider.isImage(url) && !"svg".equalsIgnoreCase(MimeTypeMap.getFileExtensionFromUrl(url));
  if (isImage || MarkDownProvider.isArchive(url)) {
    return;
  }
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getFileAsStream(url),body -> {
    downloadedStream=body;
    sendToView(view -> view.onSetCode(body));
  }
);
}
