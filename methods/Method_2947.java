public static <E>void fill(List<E> list,E value){
  if (list == null)   return;
  ListIterator<E> listIterator=list.listIterator();
  while (listIterator.hasNext())   listIterator.set(value);
}
