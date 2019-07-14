@Nullable public static <E>E pop(@NonNull List<? extends E> list){
  return list.remove(list.size() - 1);
}
