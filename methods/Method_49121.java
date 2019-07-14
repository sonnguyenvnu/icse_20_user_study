public static boolean isVertexReturnStep(JanusGraphVertexStep vertexStep){
  return Vertex.class.isAssignableFrom(vertexStep.getReturnClass());
}
