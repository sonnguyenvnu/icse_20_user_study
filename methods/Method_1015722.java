/** 
 * Returns a list of messages in the range [from .. to], including from and to
 * @param from
 * @param to
 * @return A list of messages, or null if none in range [from .. to] was found
 */
public List<T> get(long from,long to){
  if (from > to)   throw new IllegalArgumentException("from (" + from + ") has to be <= to (" + to + ")");
  validate(from);
  List<T> retval=null;
  for (long i=from; i <= to; i++) {
    T element=get(i);
    if (element != null) {
      if (retval == null)       retval=new ArrayList<>();
      retval.add(element);
    }
  }
  return retval;
}
