protected static <E>void permute(List<E> prefix,List<E> list,int index,final List<List<E>> permutations){
  if (list.size() <= 1)   permutations.add(combine(prefix,list));
 else {
    List<E> swapped=swap(list,index);
    List<E> first=car(swapped);
    List<E> rest=cdr(swapped);
    for (int i=0; i < rest.size(); i++)     permute(combine(prefix,first),rest,i,permutations);
  }
}
