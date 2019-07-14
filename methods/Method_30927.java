@Nullable public static <E>E popOrNull(@NonNull List<? extends E> list){
  return !list.isEmpty() ? pop(list) : null;
}
