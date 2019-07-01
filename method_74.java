public static InternalLog _XXXXX_(TaggedLogAPIEntity entity,EntityDefinition entityDef) throws Exception {
  final InternalLog log=new InternalLog();
  final Map<String,String> inputTags=entity.getTags();
  final Map<String,String> tags=new TreeMap<String,String>();
  if (inputTags != null) {
    for (    Map.Entry<String,String> entry : inputTags.entrySet()) {
      tags.put(entry.getKey(),entry.getValue());
    }
  }
  log.setTags(tags);
  if (entityDef.isTimeSeries()) {
    log.setTimestamp(entity.getTimestamp());
  }
 else {
    log.setTimestamp(EntityConstants.FIXED_WRITE_TIMESTAMP);
  }
  if (entity.getPrefix() != null && !entity.getPrefix().isEmpty()) {
    log.setPrefix(entity.getPrefix());
  }
 else {
    log.setPrefix(entityDef.getPrefix());
  }
  log.setPartitions(entityDef.getPartitions());
  EntitySerDeserializer des=new EntitySerDeserializer();
  log.setQualifierValues(des.writeValue(entity,entityDef));
  final IndexDefinition[] indexDefs=entityDef.getIndexes();
  if (indexDefs != null) {
    final List<byte[]> indexRowkeys=new ArrayList<byte[]>();
    for (int i=0; i < indexDefs.length; ++i) {
      final IndexDefinition indexDef=indexDefs[i];
      final byte[] indexRowkey=indexDef.generateIndexRowkey(entity);
      indexRowkeys.add(indexRowkey);
    }
    log.setIndexRowkeys(indexRowkeys);
  }
  return log;
}