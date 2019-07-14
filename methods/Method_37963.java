/** 
 * Appends a string.
 */
public StringBand append(String s){
  if (s == null) {
    s=StringPool.NULL;
  }
  if (index >= array.length) {
    expandCapacity();
  }
  array[index++]=s;
  length+=s.length();
  return this;
}
