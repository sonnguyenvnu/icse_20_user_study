/** 
 * Returns a  {@code Spliterator} filtered by the specified predicate. 
 */
static <T>Spliterator<T> filter(Spliterator<T> fromSpliterator,Predicate<? super T> predicate){
  checkNotNull(fromSpliterator);
  checkNotNull(predicate);
class Splitr implements Spliterator<T>, Consumer<T> {
    @Override public void accept(    T t){
      this.holder=t;
    }
    @Override public boolean tryAdvance(    Consumer<? super T> action){
      while (fromSpliterator.tryAdvance(this)) {
        try {
          if (predicate.test(holder)) {
            action.accept(holder);
            return true;
          }
        }
  finally {
          holder=null;
        }
      }
      return false;
    }
    @Override public Spliterator<T> trySplit(){
      Spliterator<T> fromSplit=fromSpliterator.trySplit();
      return (fromSplit == null) ? null : filter(fromSplit,predicate);
    }
    @Override public long estimateSize(){
      return fromSpliterator.estimateSize() / 2;
    }
    @Override public Comparator<? super T> getComparator(){
      return fromSpliterator.getComparator();
    }
    @Override public int characteristics(){
      return fromSpliterator.characteristics() & (Spliterator.DISTINCT | Spliterator.NONNULL | Spliterator.ORDERED | Spliterator.SORTED);
    }
  }
  return new Splitr();
}
