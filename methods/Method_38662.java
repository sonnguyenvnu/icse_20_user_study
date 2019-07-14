/** 
 * Returns current  {@link jodd.json.JsonValueContext value context}. It may be <code>null</code> if value is not  {@link #pushValue(Object) pushed} yet.
 */
public JsonValueContext peekValueContext(){
  return lastValueContext;
}
