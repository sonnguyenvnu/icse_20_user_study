@NotNull public CanonicallyNamed[] modulars(){
  return (CanonicallyNamed[])getStub().getChildrenByType(MODULE,new ModuleImpl[1]);
}
