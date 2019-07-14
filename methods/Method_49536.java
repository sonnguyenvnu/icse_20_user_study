public TinkerVertex getOrCreateVertex(final long vertexId,final String label,final TinkerGraph tg){
  TinkerVertex v;
  try {
    v=(TinkerVertex)tg.vertices(vertexId).next();
  }
 catch (  NoSuchElementException e) {
    if (null != label) {
      v=(TinkerVertex)tg.addVertex(T.label,label,T.id,vertexId);
    }
 else {
      v=(TinkerVertex)tg.addVertex(T.id,vertexId);
    }
  }
  return v;
}
