/** 
 * Returns o.toString() unless it throws an exception (which causes it to be stored in unrenderableClasses) or already was present in unrenderableClasses. If so, the same string is returned as would have been returned by Object.toString(). Arrays get special treatment as they don't have usable toString methods.
 * @param o incoming object to render.
 * @return
 */
public static String render(Object o){
  if (o == null) {
    return String.valueOf(o);
  }
  Class<?> objectClass=o.getClass();
  if (unrenderableClasses.containsKey(objectClass) == false) {
    try {
      if (objectClass.isArray()) {
        return renderArray(o,objectClass).toString();
      }
 else {
        return o.toString();
      }
    }
 catch (    Exception e) {
      Long now=new Long(System.currentTimeMillis());
      System.err.println("Disabling exception throwing class " + objectClass.getName() + ", " + e.getMessage());
      unrenderableClasses.put(objectClass,now);
    }
  }
  String name=o.getClass().getName();
  return name + "@" + Integer.toHexString(o.hashCode());
}
