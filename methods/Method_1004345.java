private BackupQuery deserialize(final String json){
  try {
    return serializer.deSerialize(json,BackupQuery.class);
  }
 catch (  Exception e) {
    LOG.error("Get backup query error.",e);
    return null;
  }
}
