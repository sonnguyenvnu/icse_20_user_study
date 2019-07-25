public static <E>List<E> combine(List<E> l1,List<E> l2){
  ArrayList<E> retval=new ArrayList<>();
  if (l1 != null)   retval.addAll(l1);
  if (l2 != null)   retval.addAll(l2);
  return retval;
}
