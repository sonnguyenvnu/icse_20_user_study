/** 
 * Zips multiple iterables into one iterable that will return iterators to step over rows of the input iterators (columns).  The order of the returned values within each row will match the ordering of the input iterables. The iterators will iterate the length of the longest input iterable, filling other columns with  {@code defaultValue}. The returned iterator is lazy, in that 'rows' are constructed as they are requested.
 * @param iterables Columns to iterate over.
 * @param defaultValue Default fill value when an input iterable is exhausted.
 * @param < T > Type of value being iterated over.
 * @return An iterator that iterates over rows of the input iterables.
 */
public static <T>Iterable<List<T>> zip(final Iterable<Iterable<T>> iterables,final T defaultValue){
  return new Iterable<List<T>>(){
    @Override public Iterator<List<T>> iterator(){
      return new ZippingIterator<T>(iterables,defaultValue);
    }
  }
;
}
