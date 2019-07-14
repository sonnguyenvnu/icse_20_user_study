public static boolean acquireLock(InternalRelation relation,int pos,boolean acquireLocksConfig){
  InternalRelationType type=(InternalRelationType)relation.getType();
  return acquireLocksConfig && type.getConsistencyModifier() == ConsistencyModifier.LOCK && (type.multiplicity().isUnique(EdgeDirection.fromPosition(pos)) || pos == 0 && type.multiplicity() == Multiplicity.SIMPLE);
}
