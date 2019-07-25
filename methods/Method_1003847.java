/** 
 * A convenience shorthand for  {@link SessionData#clear()}.
 * @return the operation for clearing the session
 */
default Operation clear(){
  return getData().operation(SessionData::clear);
}
