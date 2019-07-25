@SuppressFBWarnings(value="BC_UNCONFIRMED_CAST",justification="If an element is not an Entity it must be an Edge") static ElementKey create(final Element element,final Set<String> groupBy){
  if (null == element) {
    throw new IllegalArgumentException("Element is required");
  }
  final Map<String,Object> groupByProperties;
  if (null == groupBy || groupBy.isEmpty()) {
    groupByProperties=Collections.emptyMap();
  }
 else {
    groupByProperties=new HashMap<>(groupBy.size());
    for (    final String prop : groupBy) {
      groupByProperties.put(prop,element.getProperty(prop));
    }
  }
  if (element instanceof Entity) {
    return new EntityKey((Entity)element,groupByProperties);
  }
  return new EdgeKey((Edge)element,groupByProperties);
}
