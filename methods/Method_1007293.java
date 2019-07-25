/** 
 * Finds the entry for the given inner class.
 * @param name      the fully-qualified class name separated by dot and $.
 * @return the index or -1 if not found.
 * @since 3.22
 */
public int find(String name){
  int n=tableLength();
  for (int i=0; i < n; i++)   if (name.equals(innerClass(i)))   return i;
  return -1;
}
