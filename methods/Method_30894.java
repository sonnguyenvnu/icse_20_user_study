private void downloadWithPermission(){
  WebViewUtils.download(mDownloadInfo.mUrl,mDownloadInfo.mUserAgent,mDownloadInfo.mContentDisposition,mDownloadInfo.mMimeType,this);
  mDownloadInfo=null;
}
