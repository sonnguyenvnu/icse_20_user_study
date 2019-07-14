@NonNull public static <E>List<E> singletonListOrEmpty(@Nullable E element){
  return element != null ? Collections.singletonList(element) : Collections.emptyList();
}
