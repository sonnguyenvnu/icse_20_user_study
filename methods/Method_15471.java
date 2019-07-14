/** 
 * put and return this
 * @param value  must be annotated by {@link MethodAccess}
 * @return {@link #puts(String,Object)}
 */
public JSONObject puts(Object value){
  return puts(null,value);
}
