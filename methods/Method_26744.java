List<UType> templateTypes(Iterable<? extends Type> types){
  ImmutableList.Builder<UType> builder=ImmutableList.builder();
  for (  Type ty : types) {
    builder.add(template(ty));
  }
  return builder.build();
}
