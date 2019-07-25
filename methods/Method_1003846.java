/** 
 * A convenience shorthand for  {@link SessionData#remove(Class)}.
 * @param type the type
 * @return the operation for removing the value
 */
default Operation remove(Class<?> type){
  return getData().operation(d -> d.remove(type));
}
