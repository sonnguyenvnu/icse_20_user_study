/** 
 * Shortcut for <code>getComponentType(type.getGenericSuperclass())</code>.
 * @see #getComponentType(java.lang.reflect.Type,int)
 */
public static Class getGenericSupertype(final Class type,final int index){
  return getComponentType(type.getGenericSuperclass(),index);
}
