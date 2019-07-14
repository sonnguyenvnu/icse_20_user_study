public static <E>List<E> mergeSort(Collection<E> a,Collection<E> b,Comparator<E> comp){
  Iterator<E> iteratorA=a.iterator(), iteratorB=b.iterator();
  E headA=iteratorA.hasNext() ? iteratorA.next() : null;
  E headB=iteratorB.hasNext() ? iteratorB.next() : null;
  List<E> result=new ArrayList<>(a.size() + b.size());
  while (headA != null || headB != null) {
    E next;
    if (headA == null) {
      next=headB;
      headB=null;
    }
 else     if (headB == null) {
      next=headA;
      headA=null;
    }
 else     if (comp.compare(headA,headB) <= 0) {
      next=headA;
      headA=null;
    }
 else {
      next=headB;
      headB=null;
    }
    assert next != null;
    Preconditions.checkArgument(result.isEmpty() || comp.compare(result.get(result.size() - 1),next) <= 0,"The input collections are not sorted");
    result.add(next);
    if (headA == null)     headA=iteratorA.hasNext() ? iteratorA.next() : null;
    if (headB == null)     headB=iteratorB.hasNext() ? iteratorB.next() : null;
  }
  return result;
}
