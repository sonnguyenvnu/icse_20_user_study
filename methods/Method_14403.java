static public File getFile(ImportingJob job,String location){
  return new File(job.getRawDataDir(),location);
}
