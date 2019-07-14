@Override public double computeFor(MethodLikeNode node,MetricOptions options){
  return (Integer)node.jjtAccept(NpathBaseVisitor.INSTANCE,null);
}
