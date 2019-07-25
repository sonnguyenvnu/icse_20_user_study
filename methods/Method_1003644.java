/** 
 * Sets a response header. <p> Any previously set values for the header will be removed. <p> Shorthand for  {@code getResponse().getHeaders().set(CharSequence, Iterable)}.
 * @param name the name of the header to set
 * @param values the header values
 * @return {@code this}
 * @since 1.4
 * @see MutableHeaders#set(CharSequence,Iterable)
 */
default Context header(CharSequence name,Object... values){
  getResponse().getHeaders().set(name,Arrays.asList(values));
  return this;
}
