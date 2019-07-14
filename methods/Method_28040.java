@Override public void onWorkOnline(){
  isImage=MarkDownProvider.isImage(url);
  if (isImage) {
    if ("svg".equalsIgnoreCase(MimeTypeMap.getFileExtensionFromUrl(url))) {
      makeRestCall(RestProvider.getRepoService(isEnterprise()).getFileAsStream(url),s -> sendToView(view -> view.onSetImageUrl(s,true)));
      return;
    }
    sendToView(view -> view.onSetImageUrl(url,false));
    return;
  }
  Observable<String> streamObservable=MarkDownProvider.isMarkdown(url) ? RestProvider.getRepoService(isEnterprise()).getFileAsHtmlStream(url) : RestProvider.getRepoService(isEnterprise()).getFileAsStream(url);
  Observable<String> observable=isRepo ? RestProvider.getRepoService(isEnterprise()).getReadmeHtml(url) : streamObservable;
  makeRestCall(observable,content -> {
    downloadedStream=content;
    ViewerFile fileModel=new ViewerFile();
    fileModel.setContent(downloadedStream);
    fileModel.setFullUrl(url);
    fileModel.setRepo(isRepo);
    if (isRepo) {
      fileModel.setMarkdown(true);
      isMarkdown=true;
      isRepo=true;
      sendToView(view -> view.onSetMdText(downloadedStream,htmlUrl == null ? url : htmlUrl,false));
    }
 else {
      isMarkdown=MarkDownProvider.isMarkdown(url);
      if (isMarkdown) {
        MarkdownModel model=new MarkdownModel();
        model.setText(downloadedStream);
        Uri uri=Uri.parse(url);
        StringBuilder baseUrl=new StringBuilder();
        for (        String s : uri.getPathSegments()) {
          if (!s.equalsIgnoreCase(uri.getLastPathSegment())) {
            baseUrl.append("/").append(s);
          }
        }
        model.setContext(baseUrl.toString());
        makeRestCall(RestProvider.getRepoService(isEnterprise()).convertReadmeToHtml(model),string -> {
          isMarkdown=true;
          downloadedStream=string;
          fileModel.setMarkdown(true);
          fileModel.setContent(downloadedStream);
          manageObservable(fileModel.save(fileModel).toObservable());
          sendToView(view -> view.onSetMdText(downloadedStream,htmlUrl == null ? url : htmlUrl,true));
        }
);
        return;
      }
      fileModel.setMarkdown(false);
      sendToView(view -> view.onSetCode(downloadedStream));
    }
    manageObservable(fileModel.save(fileModel).toObservable());
  }
);
}
