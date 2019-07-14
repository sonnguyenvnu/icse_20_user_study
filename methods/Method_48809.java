private static void attachReferenceElements(TraverserSet<Object> toProcessTraversers,Graph graph){
  toProcessTraversers.forEach(traverser -> {
    Object value=traverser.get();
    if (value instanceof ReferenceVertex) {
      Vertex vertex=((ReferenceVertex)value).attach(Attachable.Method.get(graph));
      traverser.set(vertex);
    }
 else     if (value instanceof ReferenceEdge) {
      Edge edge=((ReferenceEdge)value).attach(Attachable.Method.get(graph));
      traverser.set(edge);
    }
  }
);
}
