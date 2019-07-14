/** 
 * Begins encoding a new object. Each call to this method must be paired with a call to  {@link #endObject}.
 * @return this stringer.
 * @throws JSONException On internal errors. Shouldn't happen.
 */
public JSONStringer object() throws JSONException {
  return open(Scope.EMPTY_OBJECT,"{");
}
