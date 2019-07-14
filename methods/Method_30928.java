public static <E>boolean startsWith(@NonNull List<? extends E> list1,@NonNull List<? extends E> list2){
  return list1.size() >= list2.size() && Functional.every(list2,(element,index) -> ObjectsCompat.equals(list1.get(index),element));
}
