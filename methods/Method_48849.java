@Override public List<SliceQuery> getQueries(){
  if (isGlobalGraphIndex()) {
    return ImmutableList.of(new SliceQuery(BufferUtil.zeroBuffer(1),BufferUtil.oneBuffer(128)));
  }
 else {
    RelationTypeIndexWrapper wrapper=(RelationTypeIndexWrapper)index;
    InternalRelationType wrappedType=wrapper.getWrappedType();
    Direction direction=null;
    for (    Direction dir : Direction.values())     if (wrappedType.isUnidirected(dir))     direction=dir;
    assert direction != null;
    StandardJanusGraphTx tx=(StandardJanusGraphTx)graph.get().buildTransaction().readOnly().start();
    try {
      QueryContainer qc=new QueryContainer(tx);
      qc.addQuery().type(wrappedType).direction(direction).relations();
      return qc.getSliceQueries();
    }
  finally {
      tx.rollback();
    }
  }
}
