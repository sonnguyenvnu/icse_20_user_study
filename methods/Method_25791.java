private static Optional<Boolean> checkPackage(MethodSymbol method){
  return shouldCheckReturnValue(enclosingPackage(method));
}
