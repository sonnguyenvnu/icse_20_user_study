public SMethod<T> build(List<SParameter> paramTypes){
  SMethodTrimmedId methodId=SMethodTrimmedId.create("",myModifiers.isVirtual() ? null : myConcept,myId64);
  final BehaviorRegistry registry=myRegistry != null ? myRegistry : ConceptRegistry.getInstance().getBehaviorRegistry();
  return SMethodImpl.create(myName,myModifiers,myReturnType,myConcept,methodId,registry,paramTypes);
}
