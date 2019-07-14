/** 
 * @webref intdict:method
 * @brief Return the internal array being used to store the values
 */
public Iterable<Long> values(){
  return new Iterable<Long>(){
    @Override public Iterator<Long> iterator(){
      return valueIterator();
    }
  }
;
}
