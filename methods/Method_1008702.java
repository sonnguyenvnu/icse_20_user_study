/** 
 * Loads a <code>name</code>d  {@link CategoryContextMapping} instancefrom a map. see  {@link ContextMappings#load(Object,Version)}Acceptable map param: <code>path</code>
 */
protected static CategoryContextMapping load(String name,Map<String,Object> config) throws ElasticsearchParseException {
  CategoryContextMapping.Builder mapping=new CategoryContextMapping.Builder(name);
  Object fieldName=config.get(FIELD_FIELDNAME);
  if (fieldName != null) {
    mapping.field(fieldName.toString());
    config.remove(FIELD_FIELDNAME);
  }
  return mapping.build();
}
