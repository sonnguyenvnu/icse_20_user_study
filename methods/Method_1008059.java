/** 
 * Proxy the box method to use valueOf instead to ensure that the modern boxing methods are used.
 */
@Override public void box(org.objectweb.asm.Type type){
  valueOf(type);
}
