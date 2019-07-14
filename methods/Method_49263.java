public static boolean hasSimpleInternalVertexKeyIndex(JanusGraphRelation rel){
  return rel instanceof JanusGraphVertexProperty && hasSimpleInternalVertexKeyIndex((JanusGraphVertexProperty)rel);
}
