@Override public void initialize(){
  if (cacheMaxSize > 0) {
    File cacheDir=new File(context.getCacheDir(),"TrackPlayer");
    DatabaseProvider db=new ExoDatabaseProvider(context);
    cache=new SimpleCache(cacheDir,new LeastRecentlyUsedCacheEvictor(cacheMaxSize),db);
  }
 else {
    cache=null;
  }
  super.initialize();
  resetQueue();
}
