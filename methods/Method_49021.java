public JanusGraphVertexProperty findProperty(JanusGraphTransaction tx){
  JanusGraphRelation r=findRelation(tx);
  if (r == null)   return null;
 else   if (r instanceof JanusGraphVertexProperty)   return (JanusGraphVertexProperty)r;
 else   throw new UnsupportedOperationException("Referenced relation is a edge not a property");
}
