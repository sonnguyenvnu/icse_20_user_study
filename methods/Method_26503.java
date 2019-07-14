private static boolean typesEqual(List<Type> typesA,List<Type> typesB,VisitorState state){
  return Streams.zip(typesA.stream(),typesB.stream(),(a,b) -> ASTHelpers.isSameType(a,b,state)).allMatch(x -> x);
}
