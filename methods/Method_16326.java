@Override public <T>RDBTable<T> getTable(String name){
  RDBTable<T> table=super.getTable(name);
  if (versionChanged == null || latestVersionGetter == null) {
    return table;
  }
  if (table != null) {
    long version=table.getMeta().getProperty("version",-1L).getValue();
    if (version == -1L) {
      return table;
    }
    if (version != latestVersionGetter.apply(table.getMeta())) {
      versionChanged.accept(table.getMeta());
    }
    return super.getTable(name);
  }
  return null;
}
