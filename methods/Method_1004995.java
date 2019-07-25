@Override public Iterable<Element> _apply(final Element element){
  final List<Element> elements=new ArrayList<>();
  for (  final Entry<String,Object> entry : element.getProperties().entrySet()) {
    if (element instanceof Edge) {
      final Edge edge=(Edge)element;
      elements.add(new Entity.Builder().group(entry.getKey() + "|" + "Edge").vertex(entry.getValue().toString()).property("source",edge.getSource()).property("destination",edge.getDestination()).property("directed",edge.isDirected()).build());
    }
 else {
      final Entity entity=(Entity)element;
      elements.add(new Entity.Builder().group(entry.getKey() + "|" + "Entity").vertex(entry.getValue().toString()).property("vertex",entity.getVertex()).build());
    }
  }
  return elements;
}
