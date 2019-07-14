public static <I extends Iterable<T>,T>int findIndex(I iterable,TriPredicate<T,Integer,I> predicate){
  return FunctionalIterator.findIndexRemaining(iterable.iterator(),(t,index,iterator) -> predicate.test(t,index,iterable));
}
