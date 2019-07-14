public boolean hasCacheOf(int index){
  TabContentCache tabContentCache=mCacheMap.get(index);
  return tabContentCache != null && tabContentCache.cells != null && !tabContentCache.cells.isEmpty();
}
