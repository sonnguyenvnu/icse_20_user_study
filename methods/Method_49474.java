@Override public void configure(Job job) throws IOException {
  Configuration conf=job.getConfiguration();
  FileSystem localFS=FileSystem.getLocal(conf);
  FileSystem jobFS=FileSystem.get(conf);
  for (  Path p : getLocalPaths()) {
    Path stagedPath=uploadFileIfNecessary(localFS,p,jobFS);
    job.addFileToClassPath(stagedPath);
  }
  String mj=getMapredJar();
  if (null != mj)   job.setJar(mj);
}
