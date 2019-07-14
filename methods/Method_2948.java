public static <E>E pop_back(List<E> list){
  E back=list.get(list.size() - 1);
  list.remove(list.size() - 1);
  return back;
}
