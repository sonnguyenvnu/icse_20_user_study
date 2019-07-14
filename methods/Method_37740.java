/** 
 * Joins arrays. Component type is resolved from the array argument.
 */
@SuppressWarnings({"unchecked"}) public static <T>T[] join(T[]... arrays){
  Class<T> componentType=(Class<T>)arrays.getClass().getComponentType().getComponentType();
  return join(componentType,arrays);
}
