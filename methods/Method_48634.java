@Override public RelationType[] getSortKey(){
  StandardJanusGraphTx tx=type.tx();
  long[] ids=type.getSortKey();
  RelationType[] keys=new RelationType[ids.length];
  for (int i=0; i < keys.length; i++) {
    keys[i]=tx.getExistingRelationType(ids[i]);
  }
  return keys;
}
