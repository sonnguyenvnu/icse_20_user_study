protected static <E>List<E> swap(List<E> l,int index){
  List<E> swapped=new ArrayList<>();
  E el=l.get(index);
  swapped.add(el);
  for (int i=0; i < l.size(); i++) {
    el=l.get(i);
    if (i != index)     swapped.add(el);
  }
  return swapped;
}
