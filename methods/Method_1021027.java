/** 
 * ????
 * @param clazz
 * @param < T >
 * @return
 */
public static <T>T singleton(Class<T> clazz){
  Object object=singletons.get(clazz);
  if (object == null) {
synchronized (clazz) {
      object=singletons.get(clazz);
      if (object == null) {
        object=newInstance(clazz);
        if (object != null) {
          singletons.put(clazz,object);
        }
 else {
          Log.getLog(clazz).error("cannot new newInstance!!!!");
        }
      }
    }
  }
  return (T)object;
}
