/** 
 * Returns the content of this message as a string encoded in the specified  {@link Charset}.
 */
default String content(Charset charset){
  return content().toString(charset);
}
