public static <E>boolean endsWith(@NonNull List<? extends E> list1,@NonNull List<? extends E> list2){
  int list1Size=list1.size();
  return list1Size >= list2.size() && FunctionalIterator.everyRemaining(new FunctionalIterator.ReverseIterator<>(list2),(element,index) -> ObjectsCompat.equals(list1.get(list1Size - 1 - index),element));
}
