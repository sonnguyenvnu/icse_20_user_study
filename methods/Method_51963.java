@Override public double computeFor(MethodLikeNode node,MetricOptions options){
  MutableInt cyclo=(MutableInt)node.jjtAccept(new CycloVisitor(options,node),new MutableInt(1));
  return (double)cyclo.getValue();
}
