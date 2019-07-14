@Nullable public static <E>E getOrNull(@NonNull List<? extends E> list,int index){
  return index >= 0 && index < list.size() ? list.get(index) : null;
}
