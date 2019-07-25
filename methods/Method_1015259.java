/** 
 * @param it an iterator
 * @param f a predicate
 * @return an iterator which only yields values that satisfy the predicate
 */
public static OfInt filter(OfInt it,IntPredicate f){
  return new OfInt(){
    private void prime(){
      if (!primed && !done) {
        while (it.hasNext()) {
          next=it.nextInt();
          if (f.test(next)) {
            primed=true;
            return;
          }
        }
        done=true;
      }
    }
    @Override public boolean hasNext(){
      prime();
      return !done;
    }
    @Override public int nextInt(){
      prime();
      if (!primed) {
        throw new NoSuchElementException();
      }
      primed=false;
      return next;
    }
  }
;
}
