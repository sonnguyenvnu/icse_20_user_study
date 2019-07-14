/** 
 * Returns the name of the annotation as it is used, eg  {@code java.lang.Override} or {@code Override}.
 */
public String getAnnotationName(){
  return jjtGetChild(0).jjtGetChild(0).getImage();
}
