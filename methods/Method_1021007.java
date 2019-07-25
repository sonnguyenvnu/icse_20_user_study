@Override public boolean update(){
  if (_hasColumn(column_modified)) {
    set(column_modified,new Date());
  }
  boolean success=isAutoCopyModel() ? copyModel().superUpdate() : this.superUpdate();
  if (success && idCacheEnable) {
    deleteIdCache();
  }
  return success;
}
