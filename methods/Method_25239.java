public static Optional<Nullness> fromAnnotationsOn(@Nullable Symbol sym){
  if (sym != null) {
    return fromAnnotationStream(MoreAnnotations.getDeclarationAndTypeAttributes(sym).map(Object::toString));
  }
  return Optional.empty();
}
