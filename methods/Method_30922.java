@Nullable public static <E>E lastOrNull(@NonNull List<? extends E> list){
  return getOrNull(list,list.size() - 1);
}
