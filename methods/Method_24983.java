/** 
 * Set a cookie with an expiration date from a month ago, effectively deleting it on the client side.
 * @param name The cookie name.
 */
public void delete(String name){
  set(name,"-delete-",-30);
}
