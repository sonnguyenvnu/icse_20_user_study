private String printEdgeLabels(boolean calledDirectly){
  StringBuilder sb=new StringBuilder();
  String pattern="%-30s | %-11s | %-11s | %-36s |%n";
  Iterable<EdgeLabel> labels=getRelationTypes(EdgeLabel.class);
  boolean hasResults=false;
  if (calledDirectly) {
    sb.append(FIRSTDASH);
  }
 else {
    sb.append(DASHBREAK);
  }
  sb.append(String.format(pattern,"Edge Label Name","Directed","Unidirected","Multiplicity"));
  sb.append(DASHBREAK);
  for (  EdgeLabel label : labels) {
    hasResults=true;
    sb.append(String.format(pattern,label.name(),label.isDirected(),label.isUnidirected(),label.multiplicity()));
  }
  if (hasResults && calledDirectly) {
    sb.append(DASHBREAK);
  }
  return sb.toString();
}
