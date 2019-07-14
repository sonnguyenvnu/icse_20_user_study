public Iterable<InternalRelationType> getRelationIndexes(){
  return Iterables.concat(ImmutableList.of(this),Iterables.transform(getRelated(TypeDefinitionCategory.RELATIONTYPE_INDEX,Direction.OUT),new Function<Entry,InternalRelationType>(){
    @Nullable @Override public InternalRelationType apply(    @Nullable Entry entry){
      assert entry.getSchemaType() instanceof InternalRelationType;
      return (InternalRelationType)entry.getSchemaType();
    }
  }
));
}
