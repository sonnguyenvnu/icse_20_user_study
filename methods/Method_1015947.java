/** 
 * Sets an attribute specified by the given name and value into request.
 * @param name  the given name
 * @param value the given value
 */
public void attr(final String name,final Object value){
  request.setAttribute(name,value);
}
