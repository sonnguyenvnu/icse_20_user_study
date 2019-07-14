/** 
 * Returns an enumeration of the values in this table.
 * @return an enumeration of the values in this table
 * @see #values()
 */
public Enumeration<V> elements(){
  return new ValueIterator();
}
