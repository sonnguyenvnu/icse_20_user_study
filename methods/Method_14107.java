/** 
 * Registers a keyer under a code name.
 */
public static void put(String name,Keyer keyer){
  _keyers.put(name,keyer);
  _keyerNames.add(name);
}
