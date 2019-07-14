@Nullable public static <E>E peek(@NonNull List<? extends E> list){
  return lastOrNull(list);
}
