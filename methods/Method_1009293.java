@NotNull @Override public CanonicallyNamed[] modulars(){
  return getChildrenByType(ModuleStubElementTypes.MODULE,new ArrayFactory<CanonicallyNamed>(){
    @NotNull @Override public CanonicallyNamed[] create(    int count){
      return new CanonicallyNamed[count];
    }
  }
);
}
