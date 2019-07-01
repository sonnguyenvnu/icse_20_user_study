public List<TaggedLogAPIEntity> _XXXXX_(List<String> rowkeys) throws IOException {
  HBaseLogByRowkeyReader reader=new HBaseLogByRowkeyReader(this.table,this.columnFamily,outputAll,outputColumns);
  List<TaggedLogAPIEntity> entities=new ArrayList<TaggedLogAPIEntity>();
  try {
    reader.open();
    for (    String rowkeyString : rowkeys) {
      byte[] rowkey=EagleBase64Wrapper.decode(rowkeyString);
      InternalLog log=reader.get(rowkey);
      TaggedLogAPIEntity entity=entityFactory.create();
      entities.add(entity);
      entity.setTags(log.getTags());
      entity.setTimestamp(log.getTimestamp());
      entity.setEncodedRowkey(log.getEncodedRowkey());
      entity.setPrefix(log.getPrefix());
      Map<String,byte[]> qualifierValues=log.getQualifierValues();
      mapper.populateQualifierValues(entity,qualifierValues);
    }
  }
 catch (  IOException ex) {
    LOG.error("Fail read by rowkey",ex);
    throw ex;
  }
 finally {
    reader.close();
  }
  return entities;
}