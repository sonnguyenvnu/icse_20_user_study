/** 
 * ????
 */
private void downloadApp(){
  showProgressDialog("????...");
  runThread(TAG + "downloadApp",new Runnable(){
    @Override public void run(){
      final File file=DownloadUtil.downLoadFile(context,"APIJSONTest",".apk","http://files.cnblogs.com/files/tommylemon/APIJSONTest.apk");
      runUiThread(new Runnable(){
        @Override public void run(){
          dismissProgressDialog();
          DownloadUtil.openFile(context,file);
        }
      }
);
    }
  }
);
}
