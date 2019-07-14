/** 
 * End an object. This method most be called to balance calls to <code>object</code>.
 * @return this
 * @throws JSONException If incorrectly nested.
 */
public JSONWriter endObject() throws JSONException {
  return this.end('k','}');
}
