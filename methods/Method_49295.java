public static void attachProperties(JanusGraphRelation element,Object... keyValues){
  if (keyValues == null || keyValues.length == 0)   return;
  org.apache.tinkerpop.gremlin.structure.util.ElementHelper.legalPropertyKeyValueArray(keyValues);
  if (org.apache.tinkerpop.gremlin.structure.util.ElementHelper.getIdValue(keyValues).isPresent())   throw Edge.Exceptions.userSuppliedIdsNotSupported();
  if (org.apache.tinkerpop.gremlin.structure.util.ElementHelper.getLabelValue(keyValues).isPresent())   throw new IllegalArgumentException("Cannot provide label as argument");
  org.apache.tinkerpop.gremlin.structure.util.ElementHelper.attachProperties(element,keyValues);
}
