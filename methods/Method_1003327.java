@Override public void put(CacheObject rec){
  if (SysProperties.CHECK) {
    int pos=rec.getPos();
    CacheObject old=find(pos);
    if (old != null) {
      DbException.throwInternalError("try to add a record twice at pos " + pos);
    }
  }
  int index=rec.getPos() & mask;
  rec.cacheChained=values[index];
  values[index]=rec;
  recordCount++;
  memory+=rec.getMemory();
  addToFront(rec);
  removeOldIfRequired();
}
