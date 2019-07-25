@Override public void run(){
  try {
    new MultipartUploadRequest(context,"http://posttestserver.com/post.php").setMethod("POST").setNotificationConfig(null).setDelegate(new UploadStatusDelegate(){
      @Override public void onProgress(      Context context,      UploadInfo uploadInfo){
        UploadService.stopUpload(uploadInfo.getUploadId());
      }
      @Override public void onError(      Context context,      UploadInfo uploadInfo,      ServerResponse serverResponse,      Exception exception){
      }
      @Override public void onCompleted(      Context context,      UploadInfo uploadInfo,      ServerResponse serverResponse){
      }
      @Override public void onCancelled(      Context context,      UploadInfo uploadInfo){
      }
    }
).setAutoDeleteFilesAfterSuccessfulUpload(true).addParameter("color","#ffffff").setMaxRetries(2).startUpload();
  }
 catch (  Exception exc) {
    Log.e(getClass().getSimpleName(),"Error",exc);
  }
}
