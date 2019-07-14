/** 
 * ????
 */
private void downloadApp(){
  showProgressDialog("????...");
  runThread(TAG + "downloadApp",new Runnable(){
    @Override public void run(){
      File file=DownloadUtil.downLoadFile(context,"APIJSONApp",".apk",Constant.APP_DOWNLOAD_WEBSITE);
      dismissProgressDialog();
      DownloadUtil.openFile(context,file);
    }
  }
);
}
