/** 
 * Returns true if this is an upper type bound, e.g. in  {@code <? extends Integer>}.
 */
public boolean isUpperBound(){
  return jjtGetFirstToken().toString().equals("extends");
}
