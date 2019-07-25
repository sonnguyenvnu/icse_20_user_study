@NotNull public static <T>SerializableClass<T> create(@NotNull Class<T> klass,@NotNull Serializer<? super T> serializer){
  return new SerializableClass<>(klass,serializer);
}
