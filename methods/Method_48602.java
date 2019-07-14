private String printVertexLabels(boolean calledDirectly){
  StringBuilder sb=new StringBuilder();
  String pattern="%-30s | %-11s | %-50s |%n";
  Iterable<VertexLabel> labels=getVertexLabels();
  boolean hasResults=false;
  sb.append(FIRSTDASH);
  sb.append(String.format(pattern,"Vertex Label Name","Partitioned","Static"));
  sb.append(DASHBREAK);
  for (  VertexLabel label : labels) {
    hasResults=true;
    sb.append(String.format(pattern,label.name(),label.isPartitioned(),label.isStatic()));
  }
  if (hasResults && calledDirectly) {
    sb.append(DASHBREAK);
  }
  return sb.toString();
}
