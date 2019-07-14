private static Optional<Boolean> checkEnclosingClasses(MethodSymbol method){
  Symbol enclosingClass=enclosingClass(method);
  while (enclosingClass instanceof ClassSymbol) {
    Optional<Boolean> result=shouldCheckReturnValue(enclosingClass);
    if (result.isPresent()) {
      return result;
    }
    enclosingClass=enclosingClass.owner;
  }
  return Optional.empty();
}
