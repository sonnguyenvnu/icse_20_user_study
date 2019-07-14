/** 
 * ??collection????object
 * @param collection
 * @param object
 * @return
 */
public static <T>boolean isContain(Collection<T> collection,T object){
  return collection != null && collection.contains(object);
}
