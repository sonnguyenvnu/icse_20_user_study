private DataBinderMapper[] getCache(){
synchronized (mMappers) {
    if (mCache == null) {
      mCache=mMappers.toArray(new DataBinderMapper[mMappers.size()]);
    }
    return mCache;
  }
}
