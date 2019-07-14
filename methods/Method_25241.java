private static Optional<Nullness> fromAnnotationList(List<?> annotations){
  return fromAnnotationStream(annotations.stream().map(Object::toString));
}
