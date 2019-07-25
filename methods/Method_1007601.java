private static <E extends Enum<E>>ArgumentConverter<E> basic(Class<E> enumClass,@Nullable E unknownValue){
  return full(enumClass,e -> ImmutableSet.of(e.name().toLowerCase(Locale.ROOT)),unknownValue);
}
