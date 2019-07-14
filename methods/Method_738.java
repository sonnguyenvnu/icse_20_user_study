/** 
 * Special-case code for containsValue with null argument
 */
private boolean containsNullValue(){
  Entry[] tab=table;
  for (int i=0; i < tab.length; i++)   for (Entry e=tab[i]; e != null; e=e.next)   if (e.value == null)   return true;
  return false;
}
