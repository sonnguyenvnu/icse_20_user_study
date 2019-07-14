/** 
 * Returns true if this is a diamond, that is, the actual type arguments are inferred.
 */
public boolean isDiamond(){
  return jjtGetNumChildren() == 0;
}
