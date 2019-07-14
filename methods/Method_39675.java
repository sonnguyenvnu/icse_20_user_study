/** 
 * Returns the int encoded value of this type reference, suitable for use in visit methods related to type annotations, like visitTypeAnnotation.
 * @return the int encoded value of this type reference.
 */
public int getValue(){
  return targetTypeAndInfo;
}
