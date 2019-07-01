private synchronized Result<Version> _XXXXX_(String key,Value value,Version version){
  Versioned<Value> vv=map.get(key);
  if (vv == null) {
    if (Version.NEW != version) {
      return new Result<Version>(Code.NoKey,null);
    }
    vv=cloneValue(value,version,ALL_FIELDS);
    vv.setVersion(new MetadataVersion(0));
    map.put(key,vv);
    return new Result<Version>(Code.OK,new MetadataVersion(0));
  }
  if (Version.NEW == version) {
    return new Result<Version>(Code.KeyExists,null);
  }
  if (Version.Occurred.CONCURRENTLY != vv.getVersion().compare(version)) {
    return new Result<Version>(Code.BadVersion,null);
  }
  vv.setVersion(((MetadataVersion)vv.getVersion()).incrementVersion());
  vv.setValue(vv.getValue().merge(value));
  return new Result<Version>(Code.OK,new MetadataVersion((MetadataVersion)vv.getVersion()));
}