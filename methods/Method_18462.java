static synchronized ResourceCache getLatest(Configuration configuration){
  if (latest == null || !latest.mConfiguration.equals(configuration)) {
    latest=new LruResourceCache(new Configuration(configuration));
  }
  return latest;
}
