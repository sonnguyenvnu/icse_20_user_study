/** 
 * <p> Registers the given object. Used by the reflection methods to avoid infinite loops. </p>
 * @param value The object to register.
 */
static void register(Object value){
  if (value != null) {
    Map<Object,Object> m=getRegistry();
    if (m == null) {
      REGISTRY.set(new WeakHashMap<Object,Object>());
    }
    getRegistry().put(value,null);
  }
}
