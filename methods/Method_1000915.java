/** 
 * Same as calling  {@link #elements}; implemented so that convenience "for-each" loop can be used for looping over elements of JSON Array constructs.
 */
@Override public final Iterator<JsonNode> iterator(){
  return elements();
}
