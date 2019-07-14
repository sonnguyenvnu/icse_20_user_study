static boolean validJanusGraphOrder(OrderGlobalStep orderGlobalStep,Traversal rootTraversal,boolean isVertexOrder){
  final List<Pair<Traversal.Admin,Object>> comparators=orderGlobalStep.getComparators();
  for (  final Pair<Traversal.Admin,Object> comp : comparators) {
    final String key;
    if (comp.getValue0() instanceof ElementValueTraversal && comp.getValue1() instanceof Order) {
      key=((ElementValueTraversal)comp.getValue0()).getPropertyKey();
    }
 else     if (comp.getValue1() instanceof ElementValueComparator) {
      final ElementValueComparator evc=(ElementValueComparator)comp.getValue1();
      if (!(evc.getValueComparator() instanceof Order))       return false;
      key=evc.getPropertyKey();
    }
 else {
      return false;
    }
    final JanusGraphTransaction tx=JanusGraphTraversalUtil.getTx(rootTraversal.asAdmin());
    final PropertyKey pKey=tx.getPropertyKey(key);
    if (pKey == null || !(Comparable.class.isAssignableFrom(pKey.dataType())) || (isVertexOrder && pKey.cardinality() != Cardinality.SINGLE)) {
      return false;
    }
  }
  return true;
}
