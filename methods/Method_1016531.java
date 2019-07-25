private Stream<QualifiedName> lookup(String typename){
  if (generatedTypes.containsKey(typename)) {
    return Stream.of(generatedTypes.get(typename));
  }
  return reflect.find(typename).map(TypeInfo::name).map(Stream::of).orElse(Stream.of());
}
