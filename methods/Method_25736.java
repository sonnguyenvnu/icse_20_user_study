private static Optional<JCCatch> catchesType(JCTry tryStatement,Type assertionErrorType,VisitorState state){
  return tryStatement.getCatches().stream().filter(catchTree -> {
    Type type=ASTHelpers.getType(catchTree.getParameter());
    return (type.isUnion() ? Streams.stream(((UnionClassType)type).getAlternativeTypes()) : Stream.of(type)).anyMatch(caught -> isSubtype(assertionErrorType,caught,state));
  }
).findFirst();
}
