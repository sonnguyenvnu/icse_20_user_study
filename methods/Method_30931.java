@NonNull public static <E>Set<E> singletonOrEmpty(@Nullable E element){
  return element != null ? Collections.singleton(element) : Collections.emptySet();
}
