/** 
 * Read a cookie from the HTTP Headers.
 * @param name The cookie's name.
 * @return The cookie's value if it exists, null otherwise.
 */
public String read(String name){
  return this.cookies.get(name);
}
