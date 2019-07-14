/** 
 * Returns the descriptor corresponding to this Java type.
 * @return the descriptor corresponding to this Java type.
 */
String getDescriptor(){
  return new String(this.buf,off,len);
}
