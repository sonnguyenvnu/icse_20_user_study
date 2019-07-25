public Document putlongs(MappingDeclaration declaration,Collection<Long> longs){
  if (!isInts(declaration))   return this;
  this.put(declaration.getMapping().name(),longs);
  return this;
}
