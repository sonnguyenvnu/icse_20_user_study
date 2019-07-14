private static <E>void difference(@NonNull Set<? extends E> set1,@NonNull Set<? extends E> set2,@NonNull Set<E> result){
  for (  E element : set1) {
    if (!set2.contains(element)) {
      result.add(element);
    }
  }
}
