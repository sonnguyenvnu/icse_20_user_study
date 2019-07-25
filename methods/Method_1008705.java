/** 
 * Loads  {@link ContextMappings} from configurationExpected configuration: List of maps representing  {@link ContextMapping}[{"name": .., "type": .., ..}, {..}]
 */
public static ContextMappings load(Object configuration,Version indexVersionCreated) throws ElasticsearchParseException {
  final List<ContextMapping> contextMappings;
  if (configuration instanceof List) {
    contextMappings=new ArrayList<>();
    List<Object> configurations=(List<Object>)configuration;
    for (    Object contextConfig : configurations) {
      contextMappings.add(load((Map<String,Object>)contextConfig,indexVersionCreated));
    }
    if (contextMappings.size() == 0) {
      throw new ElasticsearchParseException("expected at least one context mapping");
    }
  }
 else   if (configuration instanceof Map) {
    contextMappings=Collections.singletonList(load(((Map<String,Object>)configuration),indexVersionCreated));
  }
 else {
    throw new ElasticsearchParseException("expected a list or an entry of context mapping");
  }
  return new ContextMappings(contextMappings);
}
