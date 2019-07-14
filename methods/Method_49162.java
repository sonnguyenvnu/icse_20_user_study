public JanusGraphVertexProperty addProperty(VertexProperty.Cardinality cardinality,JanusGraphVertex vertex,PropertyKey key,Object value){
  if (key.cardinality().convert() != cardinality && cardinality != VertexProperty.Cardinality.single)   throw new SchemaViolationException("Key is defined for %s cardinality which conflicts with specified: %s",key.cardinality(),cardinality);
  verifyWriteAccess(vertex);
  Preconditions.checkArgument(!(key instanceof ImplicitKey),"Cannot create a property of implicit type: %s",key.name());
  vertex=((InternalVertex)vertex).it();
  Preconditions.checkNotNull(key);
  checkPropertyConstraintForVertexOrCreatePropertyConstraint(vertex,key);
  final Object normalizedValue=verifyAttribute(key,value);
  Cardinality keyCardinality=key.cardinality();
  final List<IndexLockTuple> uniqueIndexTuples=new ArrayList<>();
  for (  CompositeIndexType index : TypeUtil.getUniqueIndexes(key)) {
    IndexSerializer.IndexRecords matches=IndexSerializer.indexMatches(vertex,index,key,normalizedValue);
    for (    Object[] match : matches.getRecordValues())     uniqueIndexTuples.add(new IndexLockTuple(index,match));
  }
  TransactionLock uniqueLock=getUniquenessLock(vertex,(InternalRelationType)key,normalizedValue);
  for (  IndexLockTuple lockTuple : uniqueIndexTuples)   uniqueLock=new CombinerLock(uniqueLock,getLock(lockTuple),times);
  uniqueLock.lock(LOCK_TIMEOUT);
  try {
    if (cardinality == VertexProperty.Cardinality.single || cardinality == VertexProperty.Cardinality.set) {
      Consumer<JanusGraphRelation> propertyRemover;
      if (cardinality == VertexProperty.Cardinality.single)       propertyRemover=JanusGraphElement::remove;
 else       propertyRemover=p -> {
        if (((JanusGraphVertexProperty)p).value().equals(normalizedValue))         p.remove();
      }
;
      if ((!config.hasVerifyUniqueness() || ((InternalRelationType)key).getConsistencyModifier() != ConsistencyModifier.LOCK) && !TypeUtil.hasAnyIndex(key) && cardinality == keyCardinality.convert()) {
        ((InternalVertex)vertex).getAddedRelations(p -> p.getType().equals(key)).forEach(propertyRemover);
      }
 else {
        ((InternalVertex)vertex).query().types(key).properties().forEach(propertyRemover);
      }
    }
    if (config.hasVerifyUniqueness()) {
      for (      IndexLockTuple lockTuple : uniqueIndexTuples) {
        if (!Iterables.isEmpty(IndexHelper.getQueryResults(lockTuple.getIndex(),lockTuple.getAll(),this)))         throw new SchemaViolationException("Adding this property for key [%s] and value [%s] violates a uniqueness constraint [%s]",key.name(),normalizedValue,lockTuple.getIndex());
      }
    }
    StandardVertexProperty prop=new StandardVertexProperty(IDManager.getTemporaryRelationID(temporaryIds.nextID()),key,(InternalVertex)vertex,normalizedValue,ElementLifeCycle.New);
    if (config.hasAssignIDsImmediately())     graph.assignID(prop);
    connectRelation(prop);
    return prop;
  }
  finally {
    uniqueLock.unlock();
  }
}
