@Nullable public static ValidationResult validate(@Nullable MethodSymbol formatMethodSymbol,Collection<? extends ExpressionTree> arguments,final VisitorState state){
  Deque<ExpressionTree> args=new ArrayDeque<>(arguments);
  Stream<String> formatStrings=constValues(args.removeFirst());
  if (formatStrings == null) {
    return null;
  }
  if (args.size() == 1 && (formatMethodSymbol == null || formatMethodSymbol.isVarArgs())) {
    Type type=ASTHelpers.getType(Iterables.getOnlyElement(args));
    if (type instanceof Type.ArrayType && ASTHelpers.isSameType(((Type.ArrayType)type).elemtype,state.getSymtab().objectType,state)) {
      return null;
    }
  }
  Iterable<Object> instances=Iterables.transform(args,(  ExpressionTree input) -> {
    try {
      return getInstance(input,state);
    }
 catch (    Throwable t) {
      return null;
    }
  }
);
  return formatStrings.map(formatString -> validate(formatString,instances)).filter(x -> x != null).findFirst().orElse(null);
}
