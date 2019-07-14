/** 
 * Returns replacement chars for given character reference.
 */
public static char[] lookup(final String name){
  return ENTITY_MAP.get(name);
}
