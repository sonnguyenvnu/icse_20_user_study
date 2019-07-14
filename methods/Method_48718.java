public static boolean acquireLock(CompositeIndexType index,boolean acquireLocksConfig){
  return acquireLocksConfig && index.getConsistencyModifier() == ConsistencyModifier.LOCK && index.getCardinality() != Cardinality.LIST;
}
