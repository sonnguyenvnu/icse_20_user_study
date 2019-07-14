/** 
 * Returns true if this assert statement has a "detail message" expression. In that case,  {@link #getDetailMessageNode()} doesn'treturn null.
 */
public boolean hasDetailMessage(){
  return jjtGetNumChildren() == 2;
}
