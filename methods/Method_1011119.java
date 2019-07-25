public DispatchGroup.Error check(){
  DispatchGroup.ClassMethodGroup thisClassGroup=ListSequence.fromList(myGroupsByClass).first();
  Iterable<DispatchGroup.ClassMethodGroup> superClassesGroups=ListSequence.fromList(myGroupsByClass).skip(1);
  Set<SNode> roots=thisClassGroup.getRoots();
  if (ListSequence.fromList(myGroupsByClass).count() == 1) {
    if (SetSequence.fromSet(roots).count() == 1) {
      return null;
    }
    Iterable<SNode> methodsForRoots=thisClassGroup.methodsByDispatchTypes(roots);
    return new DispatchGroup.Error("Dispatch parameter type hierarchy must have a single root",methodsForRoots);
  }
  Set<SNode> badRoots=SetSequence.fromSet(new HashSet<SNode>());
  for (  final SNode root : SetSequence.fromSet(roots)) {
    if (!(Sequence.fromIterable(superClassesGroups).any(new IWhereFilter<DispatchGroup.ClassMethodGroup>(){
      public boolean accept(      DispatchGroup.ClassMethodGroup it){
        return MapSequence.fromMap(it.methods).containsKey(root);
      }
    }
))) {
      SetSequence.fromSet(badRoots).addElement(root);
    }
  }
  if (SetSequence.fromSet(badRoots).isEmpty()) {
    return null;
  }
  Iterable<SNode> methodsForBadRoots=thisClassGroup.methodsByDispatchTypes(badRoots);
  if (SetSequence.fromSet(badRoots).count() == 1) {
    final SNode cls=SetSequence.fromSet(badRoots).first();
    boolean isGlobalRoot=Sequence.fromIterable(superClassesGroups).all(new IWhereFilter<DispatchGroup.ClassMethodGroup>(){
      public boolean accept(      DispatchGroup.ClassMethodGroup it){
        return SetSequence.fromSet(MapSequence.fromMap(it.methods).keySet()).all(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return DispatchUtil.isParent(cls,it);
          }
        }
);
      }
    }
);
    if (!(isGlobalRoot)) {
      return new DispatchGroup.Error("Dispatch type not present in super classes and is not a supertype for other param types",methodsForBadRoots);
    }
  }
 else {
    return new DispatchGroup.Error("Dispatch type not present in super classes",methodsForBadRoots);
  }
  return null;
}
