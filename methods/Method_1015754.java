/** 
 * Dumps the seqnos in the table as a list 
 */
public String dump(){
  lock.lock();
  try {
    return stream(low,hr).filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(", "));
  }
  finally {
    lock.unlock();
  }
}
