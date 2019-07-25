/** 
 * @param token Name of token in the sequence.
 * @return {@code true} if token exists and has been found. {@code false} otherwise.
 */
public boolean has(String token){
  return get(token) != null;
}
