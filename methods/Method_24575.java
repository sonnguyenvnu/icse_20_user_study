/** 
 * @return <tt>chars[pos+1]</tt> or '\0' if out-of-bounds.
 */
private char peek(){
  return (pos + 1 >= chars.length) ? 0 : chars[pos + 1];
}
