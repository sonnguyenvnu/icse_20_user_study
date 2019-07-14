private static void logRelations(DataOutput out,final Collection<InternalRelation> relations,StandardJanusGraphTx tx){
  VariableLong.writePositive(out,relations.size());
  for (  InternalRelation rel : relations) {
    VariableLong.writePositive(out,rel.getVertex(0).longId());
    org.janusgraph.diskstorage.Entry entry=tx.getEdgeSerializer().writeRelation(rel,0,tx);
    BufferUtil.writeEntry(out,entry);
  }
}
