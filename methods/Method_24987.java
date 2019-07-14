/** 
 * Sets a cookie.
 * @param name The cookie's name.
 * @param value The cookie's value.
 * @param expires How many days until the cookie expires.
 */
public void set(String name,String value,int expires){
  this.queue.add(new Cookie(name,value,Cookie.getHTTPTime(expires)));
}
