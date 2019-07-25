@Override public void run(){
  try {
    new MultipartUploadRequest(context,"http://posttestserver.com/post.php").setMethod("POST").setNotificationConfig(null).setAutoDeleteFilesAfterSuccessfulUpload(true).addParameter("color","#ffffff").setMaxRetries(2).startUpload();
  }
 catch (  Exception exc) {
    Log.e(getClass().getSimpleName(),"Error",exc);
  }
}
