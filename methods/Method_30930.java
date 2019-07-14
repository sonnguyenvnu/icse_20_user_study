@Nullable public static <E>Set<E> singletonOrNull(@Nullable E element){
  return element != null ? Collections.singleton(element) : null;
}
