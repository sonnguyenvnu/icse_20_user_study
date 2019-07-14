/** 
 * Begins encoding a new array. Each call to this method must be paired with a call to  {@link #endArray}.
 * @return this stringer.
 * @throws JSONException On internal errors. Shouldn't happen.
 */
public JSONStringer array() throws JSONException {
  return open(Scope.EMPTY_ARRAY,"[");
}
