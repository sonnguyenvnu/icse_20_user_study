/** 
 * ??
 * @param attribute
 * @return
 */
public static Attribute clone(Attribute attribute){
  return new Attribute(attribute.getName(),attribute.getValue());
}
