/** 
 * Remove speech audio files in object storage.
 * @param audioURL the specified speech audio URL
 */
public void removeAudioFile(final String audioURL){
  try {
    LOGGER.log(Level.INFO,"Removing audio file [" + audioURL + "]");
    if (Symphonys.QN_ENABLED) {
      final Auth auth=Auth.create(Symphonys.UPLOAD_QINIU_AK,Symphonys.UPLOAD_QINIU_SK);
      final BucketManager bucketManager=new BucketManager(auth,new Configuration());
      final String fileKey=StringUtils.replace(audioURL,Symphonys.UPLOAD_QINIU_DOMAIN + "/","");
      bucketManager.delete(Symphonys.UPLOAD_QINIU_BUCKET,fileKey);
    }
 else {
      final String fileName=StringUtils.replace(audioURL,Latkes.getServePath() + "/upload","");
      final File file=new File(Symphonys.UPLOAD_LOCAL_DIR + fileName);
      FileUtils.deleteQuietly(file);
    }
    LOGGER.log(Level.INFO,"Removed audio file [" + audioURL + "]");
  }
 catch (  final Exception e) {
    if (e instanceof QiniuException) {
      try {
        LOGGER.log(Level.ERROR,"Removes audio failed [" + audioURL + "], Qiniu exception body [" + ((QiniuException)e).response.bodyString() + "]");
      }
 catch (      final Exception qe) {
        LOGGER.log(Level.ERROR,"Removes audio and parse result exception",qe);
      }
    }
 else {
      LOGGER.log(Level.ERROR,"Removes audio failed [" + audioURL + "]",e);
    }
  }
}
