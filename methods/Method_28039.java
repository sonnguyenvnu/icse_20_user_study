@Override public void onWorkOffline(){
  if (downloadedStream == null) {
    manageDisposable(RxHelper.getObservable(ViewerFile.get(url)).subscribe(fileModel -> {
      if (fileModel != null) {
        isImage=MarkDownProvider.isImage(fileModel.getFullUrl());
        if (isImage) {
          sendToView(view -> view.onSetImageUrl(fileModel.getFullUrl(),false));
        }
 else {
          downloadedStream=fileModel.getContent();
          isRepo=fileModel.isRepo();
          isMarkdown=fileModel.isMarkdown();
          sendToView(view -> {
            if (isRepo || isMarkdown) {
              view.onSetMdText(downloadedStream,fileModel.getFullUrl(),false);
            }
 else {
              view.onSetCode(downloadedStream);
            }
          }
);
        }
      }
    }
,throwable -> sendToView(view -> view.showErrorMessage(throwable.getMessage()))));
  }
}
