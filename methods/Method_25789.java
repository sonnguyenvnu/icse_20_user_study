private static Optional<Boolean> shouldCheckReturnValue(Symbol sym){
  if (hasDirectAnnotationWithSimpleName(sym,CAN_IGNORE_RETURN_VALUE)) {
    return Optional.of(false);
  }
  if (hasDirectAnnotationWithSimpleName(sym,CHECK_RETURN_VALUE)) {
    return Optional.of(true);
  }
  return Optional.empty();
}
