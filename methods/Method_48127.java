public static IllegalStateException removedException(JanusGraphElement element){
  Class elementClass=Vertex.class.isAssignableFrom(element.getClass()) ? Vertex.class : (Edge.class.isAssignableFrom(element.getClass()) ? Edge.class : VertexProperty.class);
  return new IllegalStateException(String.format("%s with id %s was removed.",elementClass.getSimpleName(),element.id()));
}
