@Override public boolean hasKey(final CacheKey key){
synchronized (mLock) {
    if (hasKeySync(key)) {
      return true;
    }
    try {
      String resourceId=null;
      List<String> resourceIds=CacheKeyUtil.getResourceIds(key);
      for (int i=0; i < resourceIds.size(); i++) {
        resourceId=resourceIds.get(i);
        if (mStorage.contains(resourceId,key)) {
          mResourceIndex.add(resourceId);
          return true;
        }
      }
      return false;
    }
 catch (    IOException e) {
      return false;
    }
  }
}
