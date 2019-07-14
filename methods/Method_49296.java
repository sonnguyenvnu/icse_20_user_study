/** 
 * This is essentially an adjusted copy&amp;paste from TinkerPop's ElementHelper class. The reason for copying it is so that we can determine the cardinality of a property key based on JanusGraph's schema which is tied to this particular transaction and not the graph.
 * @param vertex
 * @param propertyKeyValues
 */
public static void attachProperties(final JanusGraphVertex vertex,final Object... propertyKeyValues){
  if (null == vertex)   throw Graph.Exceptions.argumentCanNotBeNull("vertex");
  for (int i=0; i < propertyKeyValues.length; i=i + 2) {
    if (!propertyKeyValues[i].equals(T.id) && !propertyKeyValues[i].equals(T.label))     vertex.property((String)propertyKeyValues[i],propertyKeyValues[i + 1]);
  }
}
