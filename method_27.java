private List<String> _XXXXX_(String appId,FileSystem hdfs,Set<String> inprogressSet) throws IOException {
  List<String> attempts=new ArrayList<String>();
  SparkApplication app=null;
  if (null == app) {
    int attemptId=0;
    boolean exists=true;
    while (exists) {
      String attemptIdString=Integer.toString(attemptId);
      String appAttemptLogName=this.getAppAttemptLogName(appId,attemptIdString);
      LOG.info("Attempt ID: {}, App Attempt Log: {}",attemptIdString,appAttemptLogName);
      String extension="";
      Path attemptFile=getFilePath(appAttemptLogName,extension);
      extension=".inprogress";
      Path inprogressFile=getFilePath(appAttemptLogName,extension);
      Path logFile=null;
      if (hdfs.exists(attemptFile)) {
        logFile=attemptFile;
      }
 else       if (hdfs.exists(inprogressFile)) {
        logFile=inprogressFile;
        inprogressSet.add(appAttemptLogName);
      }
 else       if (attemptId > 0) {
        exists=false;
      }
      if (logFile != null) {
        attempts.add(appAttemptLogName);
      }
      attemptId++;
    }
  }
  return attempts;
}