/** 
 * @webref stringdict:method
 * @brief Return the internal array being used to store the values
 */
public Iterable<String> values(){
  return new Iterable<String>(){
    @Override public Iterator<String> iterator(){
      return valueIterator();
    }
  }
;
}
