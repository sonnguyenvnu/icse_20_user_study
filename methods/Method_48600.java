@Override public String printSchema(){
  StringBuilder sb=new StringBuilder();
  sb.append(printVertexLabels(false));
  sb.append(printEdgeLabels(false));
  sb.append(printPropertyKeys(false));
  sb.append(printIndexes(false));
  return sb.toString();
}
