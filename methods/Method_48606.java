private String printIndexes(boolean calledDirectly){
  StringBuilder sb=new StringBuilder();
  String pattern="%-30s | %-11s | %-9s | %-14s | %-10s %10s |%n";
  String relationPattern="%-30s | %-11s | %-9s | %-14s | %-8s | %10s |%n";
  Iterable<JanusGraphIndex> vertexIndexes=getGraphIndexes(Vertex.class);
  Iterable<JanusGraphIndex> edgeIndexes=getGraphIndexes(Edge.class);
  Iterable<RelationType> relationTypes=getRelationTypes(RelationType.class);
  LinkedList<RelationTypeIndex> relationIndexes=new LinkedList<>();
  for (  RelationType rt : relationTypes) {
    Iterable<RelationTypeIndex> rti=getRelationIndexes(rt);
    rti.forEach(relationIndexes::add);
  }
  if (calledDirectly) {
    sb.append(FIRSTDASH);
  }
 else {
    sb.append(DASHBREAK);
  }
  sb.append(String.format(pattern,"Vertex Index Name","Type","Unique","Backing","Key:","Status"));
  sb.append(DASHBREAK);
  sb.append(iterateIndexes(pattern,vertexIndexes));
  sb.append(DASHBREAK);
  sb.append(String.format(pattern,"Edge Index (VCI) Name","Type","Unique","Backing","Key:","Status"));
  sb.append(DASHBREAK);
  sb.append(iterateIndexes(pattern,edgeIndexes));
  sb.append(DASHBREAK);
  sb.append(String.format(relationPattern,"Relation Index","Type","Direction","Sort Key","Order","Status"));
  sb.append(DASHBREAK);
  for (  RelationTypeIndex ri : relationIndexes) {
    sb.append(String.format(relationPattern,ri.name(),ri.getType(),ri.getDirection(),ri.getSortKey()[0],ri.getSortOrder(),ri.getIndexStatus().name()));
  }
  if (!relationIndexes.isEmpty()) {
    sb.append(DASHBREAK);
  }
  return sb.toString();
}
