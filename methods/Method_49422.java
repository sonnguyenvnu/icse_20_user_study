/** 
 * Push mapping to ElasticSearch
 * @param store the type in the index
 * @param key the name of the property in the index
 * @param information information of the key
 */
private void pushMapping(String store,String key,KeyInformation information) throws AssertionError, PermanentBackendException, BackendException {
  final Class<?> dataType=information.getDataType();
  Mapping map=Mapping.getMapping(information);
  final Map<String,Object> properties=new HashMap<>();
  if (AttributeUtil.isString(dataType)) {
    if (map == Mapping.DEFAULT)     map=Mapping.TEXT;
    log.debug("Registering string type for {} with mapping {}",key,map);
    final String stringAnalyzer=ParameterType.STRING_ANALYZER.findParameter(information.getParameters(),null);
    final String textAnalyzer=ParameterType.TEXT_ANALYZER.findParameter(information.getParameters(),null);
    final Map<String,Object> stringMapping=stringAnalyzer == null ? compat.createKeywordMapping() : compat.createTextMapping(stringAnalyzer);
switch (map) {
case STRING:
      properties.put(key,stringMapping);
    break;
case TEXT:
  properties.put(key,compat.createTextMapping(textAnalyzer));
break;
case TEXTSTRING:
properties.put(key,compat.createTextMapping(textAnalyzer));
properties.put(getDualMappingName(key),stringMapping);
break;
default :
throw new AssertionError("Unexpected mapping: " + map);
}
}
 else if (dataType == Float.class) {
log.debug("Registering float type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"float"));
}
 else if (dataType == Double.class) {
log.debug("Registering double type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"double"));
}
 else if (dataType == Byte.class) {
log.debug("Registering byte type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"byte"));
}
 else if (dataType == Short.class) {
log.debug("Registering short type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"short"));
}
 else if (dataType == Integer.class) {
log.debug("Registering integer type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"integer"));
}
 else if (dataType == Long.class) {
log.debug("Registering long type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"long"));
}
 else if (dataType == Boolean.class) {
log.debug("Registering boolean type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"boolean"));
}
 else if (dataType == Geoshape.class) {
switch (map) {
case PREFIX_TREE:
final int maxLevels=ParameterType.INDEX_GEO_MAX_LEVELS.findParameter(information.getParameters(),DEFAULT_GEO_MAX_LEVELS);
final double distErrorPct=ParameterType.INDEX_GEO_DIST_ERROR_PCT.findParameter(information.getParameters(),DEFAULT_GEO_DIST_ERROR_PCT);
log.debug("Registering geo_shape type for {} with tree_levels={} and distance_error_pct={}",key,maxLevels,distErrorPct);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"geo_shape","tree","quadtree","tree_levels",maxLevels,"distance_error_pct",distErrorPct));
break;
default :
log.debug("Registering geo_point type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"geo_point"));
}
}
 else if (dataType == Date.class || dataType == Instant.class) {
log.debug("Registering date type for {}",key);
properties.put(key,ImmutableMap.of(ES_TYPE_KEY,"date"));
}
 else if (dataType == UUID.class) {
log.debug("Registering uuid type for {}",key);
properties.put(key,compat.createKeywordMapping());
}
if (useAllField && client.getMajorVersion().getValue() >= 6) {
properties.put(ElasticSearchConstants.CUSTOM_ALL_FIELD,compat.createTextMapping(null));
if (properties.containsKey(key) && dataType != Geoshape.class) {
final Map<String,Object> mapping=new HashMap<>(((Map<String,Object>)properties.get(key)));
mapping.put("copy_to",ElasticSearchConstants.CUSTOM_ALL_FIELD);
properties.put(key,mapping);
}
}
final List<Parameter> customParameters=ParameterType.getCustomParameters(information.getParameters());
if (properties.containsKey(key) && !customParameters.isEmpty()) {
final Map<String,Object> mapping=new HashMap<>(((Map<String,Object>)properties.get(key)));
customParameters.forEach(p -> mapping.put(p.key(),p.value()));
properties.put(key,mapping);
}
final Map<String,Object> mapping=ImmutableMap.of("properties",properties);
try {
client.createMapping(getIndexStoreName(store),store,mapping);
}
 catch (final Exception e) {
throw convert(e);
}
}
